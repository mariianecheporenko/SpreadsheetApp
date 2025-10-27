grammar LabSpreadsheet;

compileUnit : expr EOF ;

expr
    : '(' expr ')'                                    #ParenExpr
    | 'not' expr                                      #NotExpr
    | expr '^' expr                                   #ExpExpr
    | expr ('*'|'/') expr                             #MulDivExpr
    | expr ('+'|'-') expr                             #AddSubExpr
    | IDENT '(' argList? ')'                          #FuncCallExpr
    // (we allow comparisons between numeric subexpr)
    | expr ('=' | '<' | '>') expr                     #CompareExpr
    | NUMBER                                          #NumberExpr
    | CELL                                            #CellExpr
    ;

argList : expr (',' expr)* ;

IDENT : [a-zA-Z]+ ;
CELL  : [A-Z]+ [1-9] [0-9]* ;
NUMBER: [0-9]+ ;
WS    : [ \t\r\n]+ -> skip ;
