# Analisador Léxico e Sintático Difcomp

Projeto realizado no período 2022.1 para a disciplina de Compiladores e Linguagens de Programação sob a supervisão da professora Lisieux Andrade.

Foi proposta a definição de uma linguagem que conte-se características de duas ou mais linguagens de programação existentes, valorizando a diversidade. Sendo então realizada a implementação dos analisadores léxico e sintático para a linguagem idealizada.

Na primeira etapa foi apresentado a definição da linguagem segundo no formato BNF/EBNF, assim como seu analisador léxico. Sobre a linguagem foi exigida a presença de elementos de construção de escopo, palavras chaves, identificadores, operadores, expressões, delimitadores, atribuições e tipos de dados, além de comentários de linha e bloco. Enquanto para o analisador léxico foi cobrada o reconhecimento dos tokens assim como a detecção de erros como comentários de bloco não fechados e a notificação de erros quanto a símbolos não pertenceres a linguagem.

Para a segunda etapa foi exigida a normalização da gramatica segundo o tipo de analisador escolhido (dentre recursivo, LL tabular e SLR), e a implementação do analisador sintático fazendo uso do analisador léxico implementado na etapa anterior. O analisador sintático deveria ser capaz de realizar o reconhecimento da sequência de tokens e informar sobre a presença de erros sintáticos.

## Recursos

- Analisador léxico
  - Baseado em expressões regulares
  - Suporte a supressão de comentários
  - Flexível, bastando definir os tipos de tokens e expressões regulares
  - Gera o fluxo de tokens sob demanda a partir de um Reader
  - Tokens contém informação sobre tipo, lexema e localização no código fonte
- Representação de gramática
  - Gramatica definida por meio de DSL
  - Detecção de erros comuns, como produções inalcançáveis ou indefinidas
- Analisador sintático ascendente SLR
  - Integrado a fonte de tokens do analisador léxico ou em memória
  - Geração da tabela SLR a partir definição da gramatica
  - Detecção de ambiguidades na tabela SLR
  - Capaz realizar analise mesmo usando tabelas parcialmente ambíguas

## Amostras e Execução

A gramatica da linguagem de referencia desse projeto esta disponível em [grammars/rustlike.bnf](grammars/rustlike.bnf) e um código fonte de exemplo em [samples/rustlike.txt](samples/rustlike.txt).

O projeto também oferece uma interface de linha de comando para permitir a visualização do processo de analise léxica e sintática com um arquivo do usuário.

```
> difcomp
usage: <command> <path>
where <command> is "lexer" or "lexerdebug" or "parser"
and <path> is the source file
```
