grammar RatLang;

prog: (expr ';')+;

expr: expr op=('*'|'/') expr         # mul
    | expr op=('+'|'-') expr         # add
    | INT                            # decNumber
    | OCT                            # octNumber
    | HEXA                           # hexNumber
    | '(' expr ')'                   # parentheses
    ;

ID : [a-zA-Z]+ ;
INT : [1-9][0-9]* ;
OCT : '0'[0-7]* ;
HEXA : '0x'[0-9a-fA-F]+ ;
WS : [ \t\r\n]+ -> skip ;