grammar LabSpreadsheet;

compileUnit : expr EOF ;

expr
    // parentheses / highest precedence
    : '(' expr ')'                                    #ParenExpr
    // unary not
    | 'not' expr                                      #NotExpr
    // exponent (right-associative)
    | expr '^' expr                                   #ExpExpr
    // multiplication / division
    | expr ('*'|'/') expr                             #MulDivExpr
    // addition / subtraction
    | expr ('+'|'-') expr                             #AddSubExpr
    // function calls like mmax(...), mmin(...)
    | IDENT '(' argList? ')'                          #FuncCallExpr
    // comparison (we allow comparisons between numeric subexpr)
    | expr ('=' | '<' | '>') expr                     #CompareExpr
    // number
    | NUMBER                                          #NumberExpr
    // cell reference like A1, B12, AA3
    | CELL                                            #CellExpr
    ;

argList : expr (',' expr)* ;

IDENT : [a-zA-Z]+ ;
CELL  : [A-Z]+ [1-9] [0-9]* ;
NUMBER: [0-9]+ ;
WS    : [ \t\r\n]+ -> skip ;
