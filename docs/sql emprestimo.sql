CREATE TABLE IF NOT EXISTS emprestimo (idemprestimo SERIAL NOT NULL PRIMARY KEY, idcliente int NOT NULL references cliente (idcliente), tipoemprestimo varchar (20) NOT NULL, valor numeric NOT NULL);

INSERT INTO emprestimo (idcliente, tipoemprestimo, valor) VALUES (?, ?, ?);

SELECT idemprestimo, idcliente, tipoemprestimo, valor FROM emprestimo e INNER JOIN cliente clt ON  e.idcliente = clt.idcliente AND clt.cpfcnpj = ?

SELECT idemprestimo, idcliente, tipoemprestimo, valor FROM emprestimo e INNER JOIN cliente clt ON  e.idcliente = clt.idcliente AND clt.nome = ?

UPDATE emprestimo SET idcliente = ?, tipoemprestimo = ?, valor = ? WHERE idemprestimo IN (SELECT e.idemprestimo FROM emprestimo e INNER JOIN cliente c ON e.idcliente = c.idcliente WHERE c.cpfcnpj = ?)

DELETE FROM emprestimo WHERE idemprestimo IN (SELECT e.idemprestimo FROM emprestimo e INNER JOIN cliente c ON e.idcliente = c.idcliente WHERE c.cpfcnpj = ?)