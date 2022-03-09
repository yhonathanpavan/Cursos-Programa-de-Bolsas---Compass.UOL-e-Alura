
SELECT * FROM tbcliente;

SELECT * FROM tbcliente
WHERE IDADE = 22;

SELECT * FROM tbcliente
WHERE IDADE > 22;

SELECT * FROM tbcliente
WHERE IDADE <= 22;

SELECT * FROM tbcliente
WHERE IDADE <> 22; -- Símbolo de diferente <>

-- COM textos:
SELECT * FROM tbcliente
WHERE NOME > 'Fernando Cavalcante'; -- A ordem respeita a primeira letra, seguindo ordem alfabética

SELECT * FROM tbcliente
WHERE NOME <> 'Fernando Cavalcante';


-- Com float

SELECT * FROM tbproduto
WHERE PRECO_LISTA = 16.008; -- Não retorna nada, pq o ponto flutuante não da o retorno exato

SELECT * FROM tbproduto -- Nesse caso, funciona
WHERE PRECO_LISTA > 16.008; 

SELECT * FROM tbproduto -- Nesse caso, funciona
WHERE PRECO_LISTA < 16.008; 

SELECT * FROM tbproduto -- Nesse caso, NÃO funciona
WHERE PRECO_LISTA <> 16.008; 

SELECT * FROM tbproduto -- Nesse caso, funciona
WHERE PRECO_LISTA BETWEEN 16.007 AND 16.009; 