grammar RatLang;

start
           : statement+
           ;

statement
           : TYPE ID (COMMA ID)* SEMI                          # variableDeclaration
           | READ ID (COMMA ID)* SEMI                          # read
           | WRITE expression (COMMA expression)* SEMI         # write
           | expression SEMI                                   # expressionEval
           | '{' expression (COMMA expression)* '}'            # block
           | IF '(' expression ')' statement (ELSE statement)? # if
           | WHILE '(' expression ')' statement                # while
           | SEMI                                              # nop
           ;

expression
           : '-' expression                                   # unary
           | '!' expression                                   # not
           | expression op=('*'|'/'|'%') expression           # mul
           | expression op=('+'|'-'|'.') expression           # add
           | expression op=('<'|'>') expression               # relation
           | expression op=('=='|'!=') expression             # comparison
           | expression '&&' expression                       # and
           | expression '||' expression                       # or
           | <assoc=right> ID '=' expression                  # assignment
           | DEC                                              # decValue
           | OCT                                              # octValue
           | HEXA                                             # hexValue
           | FLOAT_VALUE                                      # floatValue
           | BOOL_VALUE                                       # boolValue
           | STRING_VALUE                                     # stringValue
           | '(' expression ')'                               # parentheses
           ;

// Keywords
INT : 'int';
FLOAT : 'float';
BOOL : 'bool';
STRING : 'string';
READ : 'read';
WRITE : 'write';
IF : 'if';
ELSE: 'else';
WHILE : 'while';
TRUE : 'true';
FALSE : 'false';
TYPE : INT | FLOAT | BOOL | STRING;

// Literals
ID : [a-zA-Z]+ ;
DEC : [1-9][0-9]* ;
OCT : '0'[0-7]* ;
HEXA : '0x'[0-9a-fA-F]+ ;
FLOAT_VALUE : [0-9]+'.'[0-9]+ ;
BOOL_VALUE : TRUE | FALSE;
STRING_VALUE : '"' ( ~[\\"\n\r] | '\\' [\\"] )* '"';
WS : [ \t\r\n]+ -> skip ;

SEMI: ';';
COMMA: ',';
