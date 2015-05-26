
SELECT	Sale.saleDate
		, Product.reference
		, SaleXProduct.quantity
		, SaleXProduct.amount
FROM	SaleXProduct
		, Sale
		, Product
WHERE	SaleXProduct.sale_id = Sale.id
	AND	SaleXProduct.product_id = Product.id
	AND MONTH( Sale.saleDate ) = '1'
ORDER BY Sale.saleDate;

SELECT	Sale.saleDate
		, Product.reference
		, SaleXProduct.quantity
		, SaleXProduct.amount
FROM	SaleXProduct
		, Sale
		, Product
WHERE	SaleXProduct.sale_id = Sale.id
	AND	SaleXProduct.product_id = Product.id
	AND MONTH( Sale.saleDate ) = '2'
ORDER BY Sale.saleDate;