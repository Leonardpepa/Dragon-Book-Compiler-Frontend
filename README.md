# Dragon Book Compiler Frontend

# Link for Dragon Book from stanford: [Dragon Book](https://suif.stanford.edu/dragonbook/)

## This is a educational project on my journey to learn about programming languages and compiler design.
    The code belongs to the owners of the book, i just rewrite it to test it and understand it better.

## Frontend Compiler Components
    * Lexer (Tokenizer), (if you dont provide an argument when creating the new instance the input is given from stdin)
    * Parser (Creates the ast tree with recursice descent)
    * Inter (Contains classes that generate fake intermediate code)
    * Symbols (Classes for symbol table and type checking)

## Grammar 
* This is the context free grammar of this toy lenguage (this is not LL ready but the modifications are made before the parser is written)
 ```terminal
  program ::= block
  block ::= { decls stmts }
  decls ::= decls decl | E
  decl ::= type id ;
  type ::= type [ num ] | basic
  stmts ::= stmts stmt | E
  stmt ::= loc = bool ;
      |   if ( bool ) stmt
      |    if ( bool ) stmt else stmt
      |    while ( bool ) stmt
      |    do stmt while ( bool )
      |    break ;
      |    block
  loc ::= loc [ bool ] | id
  bool ::= bool | | join | join
  join ::= join && equality | equality
  equality ::= equality == rei | equality ! = rei | rei
  rei ::= expr < expr | expr <= expr | expr >= expr
  expr > expr | expr
  expr ::= expr + term | expr - term | term
  term ::= term * unary | term / unary | unary
  unary ::= ! unary | - unary | factor
  factor ::= ( bool ) | loc | num | real | true | false
   ```

## Test
```terminal
    {
    int i;
    float[200] v;
    int[5] array;
    int j;
    v[0] = 15;
    i=0;

    if(i > 0){
        i=1;
    }

    j = 0;

    while(j <= 5){
        array[j] = j;
        j = j + 1;
    }

    while(i <= 100){
        v[i] = 0;
        i = i + 1;
    }
    i = 0;
    while(true){
        i = i + 5;
        if(i > 100){
            break;
        }
    }
 }
```
## Generated intermediate code from test
```terminal
    L1:
	t1 = 0 * 8
	v [ t1 ]  = 15
L3:
	i = 0
L4:
	iffalse i > 0 goto L5
L6:
	i = 1
L5:
	j = 0
L7:
	iffalse j <= 5 goto L8
L9:
	t2 = j * 4
	array [ t2 ]  = j
L10:
	j = j + 1
	goto L7
L8:
	iffalse i <= 100 goto L11
L12:
	t3 = i * 8
	v [ t3 ]  = 0
L13:
	i = i + 1
	goto L8
L11:
	i = 0
L14:
L15:
	i = i + 5
L16:
	iffalse i > 100 goto L14
L17:
	goto L2
	goto L14
L2:
```

## Run Locally
* clone the repository
* open with intellij
* run main.java

## Tests
the tests are in the resources/tests folder.

## TODO
create documentation of the implementation so i can myself understand it better