/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file is an adaptation of Presto's presto-parser/src/main/antlr4/com/facebook/presto/sql/parser/SqlBase.g4 grammar.
 */
grammar MySqlBase;

@members {
  /**
   * Verify whether current token is a valid decimal token (which contains dot).
   * Returns true if the character that follows the token is not a digit or letter or underscore.
   *
   * For example:
   * For char stream "2.3", "2." is not a valid decimal token, because it is followed by digit '3'.
   * For char stream "2.3_", "2.3" is not a valid decimal token, because it is followed by '_'.
   * For char stream "2.3W", "2.3" is not a valid decimal token, because it is followed by 'W'.
   * For char stream "12.0D 34.E2+0.12 "  12.0D is a valid decimal token because it is folllowed
   * by a space. 34.E2 is a valid decimal token because it is followed by symbol '+'
   * which is not a digit or letter or underscore.
   */
     /**
      * When false, INTERSECT is given the greater precedence over the other set
      * operations (UNION, EXCEPT and MINUS) as per the SQL standard.
      */
     public boolean legacy_setops_precedence_enabled = false;

     /**
      * When false, a literal with an exponent would be converted into
      * double type rather than decimal type.
      */
     public boolean legacy_exponent_literal_as_decimal_enabled = false;

     /**
      * When true, the behavior of keywords follows ANSI SQL standard.
      */
     public boolean SQL_standard_keyword_behavior = false;

  public boolean isValidDecimal() {
    int nextChar = _input.LA(1);
    if (nextChar >= 'A' && nextChar <= 'Z' || nextChar >= '0' && nextChar <= '9' ||
      nextChar == '_') {
      return false;
    } else {
      return true;
    }
  }
}

tokens {
    DELIMITER
}

singleStatement
    : statement ';'* EOF
    ;

// If you add keywords here that should not be reserved, add them to 'nonReserved' list.
statement
    : COMPACT TABLE target=tableIdentifier partitionSpec?
       (INTO fileNum=INTEGER_VALUE FILES)?                           #compactTable
    | .*?                                                            #passThrough
    ;

tableIdentifier
    : (db=errorCapturingIdentifier DOT)? table=errorCapturingIdentifier
    ;

partitionSpecLocation
    : partitionSpec locationSpec?
    ;

locationSpec
    : LOCATION STRING
    ;

partitionSpec
    : PARTITION LEFT_PAREN partitionVal (COMMA partitionVal)* RIGHT_PAREN
    ;

partitionVal
    : identifier (EQ constant)?
    | identifier EQ DEFAULT
    ;
errorCapturingIdentifier
    : identifier errorCapturingIdentifierExtra
    ;

constant
    : NULL                                                                                     #nullLiteral
    | interval                                                                                 #intervalLiteral
    | identifier STRING                                                                        #typeConstructor
    | number                                                                                   #numericLiteral
    | booleanValue                                                                             #booleanLiteral
    | STRING+                                                                                  #stringLiteral
    ;

booleanValue
    : TRUE | FALSE
    ;


number
    : {!legacy_exponent_literal_as_decimal_enabled}? MINUS? EXPONENT_VALUE #exponentLiteral
    | {!legacy_exponent_literal_as_decimal_enabled}? MINUS? DECIMAL_VALUE  #decimalLiteral
    | {legacy_exponent_literal_as_decimal_enabled}? MINUS? (EXPONENT_VALUE | DECIMAL_VALUE) #legacyDecimalLiteral
    | MINUS? INTEGER_VALUE            #integerLiteral
    | MINUS? BIGINT_LITERAL           #bigIntLiteral
    | MINUS? SMALLINT_LITERAL         #smallIntLiteral
    | MINUS? TINYINT_LITERAL          #tinyIntLiteral
    | MINUS? DOUBLE_LITERAL           #doubleLiteral
    | MINUS? BIGDECIMAL_LITERAL       #bigDecimalLiteral
    ;

EXPONENT_VALUE
    : DIGIT+ EXPONENT
    | DECIMAL_DIGITS EXPONENT {isValidDecimal()}?
    ;

interval
    : INTERVAL (errorCapturingMultiUnitsInterval | errorCapturingUnitToUnitInterval)?
    ;

errorCapturingMultiUnitsInterval
    : body=multiUnitsInterval unitToUnitInterval?
    ;

multiUnitsInterval
    : (intervalValue unit+=identifier)+
    ;

errorCapturingUnitToUnitInterval
    : body=unitToUnitInterval (error1=multiUnitsInterval | error2=unitToUnitInterval)?
    ;

unitToUnitInterval
    : value=intervalValue from=identifier TO to=identifier
    ;

intervalValue
    : (PLUS | MINUS)? (INTEGER_VALUE | DECIMAL_VALUE | STRING)
    ;


// extra left-factoring grammar
errorCapturingIdentifierExtra
    : (MINUS identifier)+    #errorIdent
    |                        #realIdent
    ;

identifier
    : strictIdentifier
    | {!SQL_standard_keyword_behavior}?
    ;

strictIdentifier
    : IDENTIFIER              #unquotedIdentifier
    | quotedIdentifier        #quotedIdentifierAlternative
    | {SQL_standard_keyword_behavior}?       #unquotedIdentifier
    | {!SQL_standard_keyword_behavior}?      #unquotedIdentifier
    ;

quotedIdentifier
    : BACKQUOTED_IDENTIFIER
    ;

// Define how the keywords above should appear in a user's SQL statement.
SEMICOLON: ';';

LEFT_PAREN: '(';
RIGHT_PAREN: ')';
COMMA: ',';
DOT: '.';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';

INTERVAL: 'INTERVAL';
ADD: 'ADD';
ALTER: 'ALTER';
AS: 'AS';
BY: 'BY';
CHECK: 'CHECK';
COMMENT: 'COMMENT';
CONSTRAINT: 'CONSTRAINT';
CONVERT: 'CONVERT';
DELTA: 'DELTA';
DESC: 'DESC';
DESCRIBE: 'DESCRIBE';
DETAIL: 'DETAIL';
DROP: 'DROP';
EXISTS: 'EXISTS';
GENERATE: 'GENERATE';
DRY: 'DRY';
HISTORY: 'HISTORY';
HOURS: 'HOURS';
IF: 'IF';
LIMIT: 'LIMIT';
MINUS: '-';
PLUS: '+';
NOT: 'NOT' | '!';
NULL: 'NULL';
OF: 'OF';
OPTIMIZE: 'OPTIMIZE';
FOR: 'FOR';
TABLE: 'TABLE';
INTO: 'INTO';
COMPACT: 'COMPACT';
PARTITION: 'PARTITION';
PARTITIONED: 'PARTITIONED';
PARTITIONS: 'PARTITIONS';
RESTORE: 'RESTORE';
RETAIN: 'RETAIN';
RUN: 'RUN';
SYSTEM_TIME: 'SYSTEM_TIME';
SYSTEM_VERSION: 'SYSTEM_VERSION';
TO: 'TO';
TIMESTAMP: 'TIMESTAMP';
VACUUM: 'VACUUM';
WHERE: 'WHERE';
VERSION: 'VERSION';
FILES: 'files' | 'FILES';
DEFAULT: 'DEFAULT';
DEFINED: 'DEFINED';
TRUE: 'TRUE';
FALSE: 'FALSE';

// Multi-character operator tokens need to be defined even though we don't explicitly reference
// them so that they can be recognized as single tokens when parsing. If we split them up and
// end up with expression text like 'a ! = b', Spark won't be able to parse '! =' back into the
// != operator.
EQ  : '=' | '==';
NSEQ: '<=>';
NEQ : '<>';
NEQJ: '!=';
LTE : '<=' | '!>';
GTE : '>=' | '!<';
CONCAT_PIPE: '||';

LOCATION: 'LOCATION';

STRING
    : '\'' ( ~('\''|'\\') | ('\\' .) )* '\''
    | '"' ( ~('"'|'\\') | ('\\' .) )* '"'
    ;

BIGINT_LITERAL
    : DIGIT+ 'L'
    ;

SMALLINT_LITERAL
    : DIGIT+ 'S'
    ;

TINYINT_LITERAL
    : DIGIT+ 'Y'
    ;

INTEGER_VALUE
    : DIGIT+
    ;

DECIMAL_VALUE
    : DIGIT+ EXPONENT
    | DECIMAL_DIGITS EXPONENT? {isValidDecimal()}?
    ;

DOUBLE_LITERAL
    : DIGIT+ EXPONENT? 'D'
    | DECIMAL_DIGITS EXPONENT? 'D' {isValidDecimal()}?
    ;

BIGDECIMAL_LITERAL
    : DIGIT+ EXPONENT? 'BD'
    | DECIMAL_DIGITS EXPONENT? 'BD' {isValidDecimal()}?
    ;

IDENTIFIER
    : (LETTER | DIGIT | '_')+
    ;

BACKQUOTED_IDENTIFIER
    : '`' ( ~'`' | '``' )* '`'
    ;

fragment DECIMAL_DIGITS
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

fragment EXPONENT
    : 'E' [+-]? DIGIT+
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    ;

SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;

BRACKETED_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

WS  : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;