// Core/Evaluator.cs
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.IO;
using SpreadsheetApp.Models;

namespace SpreadsheetApp.Core
{
    public class EvalValue
    {
        public bool IsBool { get; }
        public double Number { get; }
        public bool Bool { get; }
        public EvalValue(double v) { IsBool = false; Number = v; Bool = v != 0.0; }
        public EvalValue(bool b) { IsBool = true; Bool = b; Number = b ? 1.0 : 0.0; }
        public override string ToString() => IsBool ? Bool.ToString() : Number.ToString();
    }

    public class EvalException : Exception { public EvalException(string m):base(m){} }

    public static class SpreadsheetEvaluator
    {
        public static EvalValue EvaluateExpression(string exprText, SpreadsheetModel model, int curRow, int curCol)
        {
            if (string.IsNullOrWhiteSpace(exprText)) return new EvalValue(0.0);
            var input = new AntlrInputStream(exprText);
            var lexer = new LabSpreadsheetLexer(input);
            var tokens = new CommonTokenStream(lexer);
            var parser = new LabSpreadsheetParser(tokens);

            parser.RemoveErrorListeners();
            parser.AddErrorListener(new ThrowExceptionErrorListener());

            var tree = parser.compileUnit();
            var visitor = new Visitor(model);
            return visitor.Visit(tree);
        }
    }

    // Error listener — реалізуємо всі варіанти сигнатур, що зустрічаються у різних версіях runtime
    public class ThrowExceptionErrorListener : IAntlrErrorListener<IToken>, IAntlrErrorListener<int>
    {
        // Variant: IAntlrErrorListener<IToken>.SyntaxError(IRecognizer, IToken, int, int, string, RecognitionException)
        public void SyntaxError(IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            throw new EvalException($"Синтаксична помилка: {msg}");
        }

        // Variant: IAntlrErrorListener<IToken>.SyntaxError(TextWriter, IRecognizer, IToken, int, int, string, RecognitionException)
        public void SyntaxError(TextWriter output, IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            throw new EvalException($"Синтаксична помилка: {msg}");
        }

        // Variant: IAntlrErrorListener<int>.SyntaxError(IRecognizer, int, int, int, string, RecognitionException)
        public void SyntaxError(IRecognizer recognizer, int offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            throw new EvalException($"Синтаксична помилка: {msg}");
        }

        // Variant: IAntlrErrorListener<int>.SyntaxError(TextWriter, IRecognizer, int, int, int, string, RecognitionException)
        public void SyntaxError(TextWriter output, IRecognizer recognizer, int offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            throw new EvalException($"Синтаксична помилка: {msg}");
        }
    }

    public class Visitor : LabSpreadsheetBaseVisitor<EvalValue>
    {
        private SpreadsheetModel model;
        internal HashSet<string> callStack = new HashSet<string>();

        public Visitor(SpreadsheetModel model)
        {
            this.model = model;
        }

        private int ColNameToIndex(string colName)
        {
            int idx = 0;
            for (int i = 0; i < colName.Length; i++)
            {
                idx = idx * 26 + (char.ToUpperInvariant(colName[i]) - 'A' + 1);
            }
            return idx - 1;
        }

        private EvalValue EvalCell(string cellToken)
        {
            int i = 0; while (i < cellToken.Length && char.IsLetter(cellToken[i])) i++;
            var colPart = cellToken.Substring(0, i).ToUpperInvariant();
            var rowPart = cellToken.Substring(i);
            if (!int.TryParse(rowPart, out int rowNum) || rowNum <= 0) throw new EvalException($"Неправильна адреса клітинки {cellToken}");
            int r = rowNum - 1;
            int c = ColNameToIndex(colPart);
            if (r < 0 || c < 0 || r >= model.Rows || c >= model.Cols) throw new EvalException($"Адреса поза діапазоном: {cellToken}");

            string key = $"{c}_{r}";
            if (callStack.Contains(key)) throw new EvalException($"Циклічне посилання в клітинці {cellToken}");
            callStack.Add(key);

            var expr = model.GetCell(r, c);
            EvalValue res;
            if (string.IsNullOrWhiteSpace(expr))
                res = new EvalValue(0.0);
            else
            {
                var input = new AntlrInputStream(expr);
                var lexer = new LabSpreadsheetLexer(input);
                var tokens = new CommonTokenStream(lexer);
                var parser = new LabSpreadsheetParser(tokens);
                parser.RemoveErrorListeners();
                parser.AddErrorListener(new ThrowExceptionErrorListener());
                var tree = parser.compileUnit();
                var nestedVisitor = new Visitor(model);
                nestedVisitor.callStack = this.callStack;
                res = nestedVisitor.Visit(tree);
            }

            callStack.Remove(key);
            return res;
        }

        public override EvalValue VisitNumberExpr([NotNull] LabSpreadsheetParser.NumberExprContext context)
        {
            double v = double.Parse(context.GetText());
            return new EvalValue(v);
        }

        public override EvalValue VisitCellExpr([NotNull] LabSpreadsheetParser.CellExprContext context)
        {
            return EvalCell(context.GetText());
        }

        public override EvalValue VisitParenExpr([NotNull] LabSpreadsheetParser.ParenExprContext context)
        {
            return Visit(context.expr());
        }

        public override EvalValue VisitNotExpr([NotNull] LabSpreadsheetParser.NotExprContext context)
        {
            var v = Visit(context.expr());
            return new EvalValue(!v.Bool);
        }

        public override EvalValue VisitExpExpr([NotNull] LabSpreadsheetParser.ExpExprContext context)
        {
            var L = Visit(context.expr(0));
            var R = Visit(context.expr(1));
            double res = Math.Pow(L.Number, R.Number);
            return new EvalValue(res);
        }

        public override EvalValue VisitMulDivExpr([NotNull] LabSpreadsheetParser.MulDivExprContext context)
        {
            var L = Visit(context.expr(0));
            var R = Visit(context.expr(1));
            string op = context.GetChild(1).GetText();
            if (op == "*") return new EvalValue(L.Number * R.Number);
            if (R.Number == 0) throw new EvalException("Ділення на нуль");
            return new EvalValue(L.Number / R.Number);
        }

        public override EvalValue VisitAddSubExpr([NotNull] LabSpreadsheetParser.AddSubExprContext context)
        {
            var L = Visit(context.expr(0));
            var R = Visit(context.expr(1));
            string op = context.GetChild(1).GetText();
            if (op == "+") return new EvalValue(L.Number + R.Number);
            else return new EvalValue(L.Number - R.Number);
        }

        public override EvalValue VisitCompareExpr([NotNull] LabSpreadsheetParser.CompareExprContext context)
        {
            var L = Visit(context.expr(0));
            var R = Visit(context.expr(1));
            string op = context.GetChild(1).GetText();
            bool result = false;
            if (op == "=") result = Math.Abs(L.Number - R.Number) < 1e-9;
            else if (op == "<") result = L.Number < R.Number;
            else if (op == ">") result = L.Number > R.Number;
            else throw new EvalException($"Невідомий оператор порівняння {op}");
            return new EvalValue(result);
        }

        public override EvalValue VisitFuncCallExpr([NotNull] LabSpreadsheetParser.FuncCallExprContext context)
        {
            var name = context.IDENT().GetText().ToLowerInvariant();
            var argsNode = context.argList();
            List<EvalValue> args = new List<EvalValue>();
            if (argsNode != null)
            {
                foreach (var ex in argsNode.expr())
                    args.Add(Visit(ex));
            }

            if (name == "mmax")
            {
                if (args.Count == 0) throw new EvalException("mmax потребує >=1 аргументів");
                double mx = double.NegativeInfinity;
                foreach (var a in args) mx = Math.Max(mx, a.Number);
                return new EvalValue(mx);
            }
            else if (name == "mmin")
            {
                if (args.Count == 0) throw new EvalException("mmin потребує >=1 аргументів");
                double mn = double.PositiveInfinity;
                foreach (var a in args) mn = Math.Min(mn, a.Number);
                return new EvalValue(mn);
            }
            else
            {
                throw new EvalException($"Невідома функція {name}");
            }
        }
    }
}
