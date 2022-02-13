
CREATE TABLE IF NOT EXISTS movimenti(
id int  ,
id_conto int not null,
tipo varchar(50)  ,
importo int not null
);


INSERT INTO movimenti (id, id_conto, tipo, importo) VALUES(null,1,null,500);