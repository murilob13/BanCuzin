SELECT idagencia, numconta, c.idcliente, plano, saldo, limite FROM conta c INNER JOIN cliente clt ON  c.idcliente = clt.idcliente AND clt.nome = 'Ned Stark'

INSERT INTO conta (idagencia, numconta, idcliente, tipoconta, plano, saldo, limite) VALUES ('3', '666', '2', 'CC', 'G', '2.000', '4.000');

UPDATE conta SET idagencia = ?, numconta = ?, tipoconta = ?, plano = ?, saldo = ?, limite = ? FROM conta c INNER JOIN cliente clt ON c.idcliente = clt.idcliente WHERE c.numconta = ? and clt.nome = ?;

DELETE FROM conta
WHERE idconta IN
(
  SELECT B.idconta
  FROM   conta  B
    INNER JOIN cliente C 
    ON   B.idcliente = C.idcliente
  WHERE  C.nome = 'Ned Stark' 
  AND    B.numconta = '6'
)