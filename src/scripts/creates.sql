
CREATE DATABASE sales;

USE sales;

CREATE TABLE Product
(
	id				INT
	, reference		VARCHAR(80)
	, stock			SMALLINT
	, state			BOOLEAN
	, PRIMARY KEY( id )
);

CREATE TABLE Sale
(
	id			INT
	, saleDate	DATE
	, PRIMARY KEY( id )
);

CREATE TABLE saleXProduct
(
	sale_id			INT
	, product_id	INT
	, quantity		SMALLINT
	, amount		INT
	, PRIMARY KEY( sale_id, product_id )
	, FOREIGN KEY( sale_id ) REFERENCES Sale( id )
	, FOREIGN KEY( product_id ) REFERENCES Product( id )
);

