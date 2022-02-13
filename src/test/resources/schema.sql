CREATE TABLE MOVIMENTS (id int(30) primary key,id_conto int(30) foreign key,tipo varchar(30),importo int(30));
INSERT INTO MOVIMENTS VALUES(1,1,'deposito',500);