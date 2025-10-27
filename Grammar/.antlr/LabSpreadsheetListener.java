// Generated from d:/uni/code/vs/github/Lab1oop/SpreadsheetApp/Grammar/LabSpreadsheet.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LabSpreadsheetParser}.
 */
public interface LabSpreadsheetListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LabSpreadsheetParser#compileUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompileUnit(LabSpreadsheetParser.CompileUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link LabSpreadsheetParser#compileUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompileUnit(LabSpreadsheetParser.CompileUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(LabSpreadsheetParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(LabSpreadsheetParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(LabSpreadsheetParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(LabSpreadsheetParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CellExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCellExpr(LabSpreadsheetParser.CellExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CellExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCellExpr(LabSpreadsheetParser.CellExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(LabSpreadsheetParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(LabSpreadsheetParser.CompareExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(LabSpreadsheetParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(LabSpreadsheetParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(LabSpreadsheetParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(LabSpreadsheetParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpExpr(LabSpreadsheetParser.ExpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpExpr(LabSpreadsheetParser.ExpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(LabSpreadsheetParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(LabSpreadsheetParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCallExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallExpr(LabSpreadsheetParser.FuncCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCallExpr}
	 * labeled alternative in {@link LabSpreadsheetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallExpr(LabSpreadsheetParser.FuncCallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LabSpreadsheetParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(LabSpreadsheetParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LabSpreadsheetParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(LabSpreadsheetParser.ArgListContext ctx);
}