package org.jasperreportssample.collectionJBDataSource;

import java.util.ArrayList;
import java.util.List;


public class ProductCollection
{
	private List<Product> productList = new ArrayList<>();


	public void addProduct( Product objProduct )
	{
		productList.add( objProduct );
	}

	public List<Product> getListProducts()
	{
		return productList;
	}
}
