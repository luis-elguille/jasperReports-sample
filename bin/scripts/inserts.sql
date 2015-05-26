
INSERT INTO Sale 
( id, saleDate ) VALUES
( 1, "2015-01-03" )
, ( 2, "2015-01-15" )
, ( 3, "2015-01-21" )
, ( 4, "2015-02-05" )
, ( 5, "2015-02-13" )
, ( 6, "2015-02-21" )
, ( 7, "2015-02-28" )
;

INSERT INTO Product
( id, reference, stock, state ) VALUES
( 1, "Toshiba Satellite", 20, true )
, ( 2, "MacBook", 14, true )
, ( 3, "MacBook Air", 8, true )
, ( 4, "MacBook Pro", 3, true )
, ( 5, "Dell XPS", 2, true )
;

INSERT INTO SaleXProduct
( sale_id, product_id, quantity, amount ) VALUES
( 1, 3, 2, 2000 )
, ( 1, 5, 1, 750 )
, ( 2, 2, 3, 2800 )
, ( 2, 5, 2, 1500 )
, ( 2, 4, 1, 1500 )
, ( 3, 1, 5, 2200 )
, ( 3, 4, 1, 1500 )
, ( 4, 2, 3, 3000 )
, ( 4, 4, 1, 150 )
, ( 5, 3, 3, 3500 )
, ( 6, 1, 10, 3000 )
, ( 6, 4, 1, 1500 )
, ( 7, 5, 3, 2300 )
;