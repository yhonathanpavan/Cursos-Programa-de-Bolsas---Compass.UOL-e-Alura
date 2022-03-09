SELECT CPF, NOME FROM tbcliente LIMIT 5;

SELECT CPF AS CPF_CLIENTE, NOME AS NOME_CLIENTE FROM tbcliente;

SELECT * FROM tbcliente
WHERE CIDADE = 'Rio de Janeiro';


SELECT * FROM tbproduto
WHERE SABOR = 'Limão';

UPDATE tbproduto SET SABOR = "Cítricos"
WHERE SABOR = 'Limão';

SELECT * FROM tbproduto
WHERE SABOR = 'Cítricos';


