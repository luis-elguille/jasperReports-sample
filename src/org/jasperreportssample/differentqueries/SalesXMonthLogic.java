
package org.jasperreportssample.differentqueries;

import java.util.List;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.utilities.dataaccesslayer.ConnectionDataSource;


public class SalesXMonthLogic
{
	private static List<SalesXMonth> salesXMonthList;

	private static Statement	sts;
	private static ResultSet 	rs;
	private static String		query = "";


	public static List<SalesXMonth> calculateSales( String pMonth, Object connDataSource )
	{
		buildQuery( pMonth );
		executeQuery( connDataSource );

		return mappingResult();
	}

	private static void buildQuery( String pMonth )
	{
		query = "SELECT Sale.saleDate, Product.reference, SaleXProduct.quantity, SaleXProduct.amount"
				+ " FROM SaleXProduct, Sale, Product"
				+ " WHERE SaleXProduct.sale_id = Sale.id"
				+ " AND SaleXProduct.product_id = Product.id"
				+ " AND MONTH( Sale.saleDate ) = " + pMonth
				+ " ORDER BY Sale.saleDate";
	}

	private static void executeQuery( Object connDataSource )
	{
		ConnectionDataSource cds = (ConnectionDataSource) connDataSource;
		
		try{
			sts = cds.getConnection().createStatement();
			rs = sts.executeQuery( query );
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}

	private static List<SalesXMonth> mappingResult()
	{
		salesXMonthList = new ArrayList<>();
		SalesXMonth salesXMonth = new SalesXMonth();
		try {
			while( rs.next() )
			{
				salesXMonth.setDate(		rs.getDate( "saleDate" ) );
				salesXMonth.setReference(	rs.getString( "reference" ) );
				salesXMonth.setQuantity(	rs.getInt( "quantity" ) );
				salesXMonth.setAmount(		rs.getLong( "amount" ) );

				salesXMonthList.add( salesXMonth );
				salesXMonth = new SalesXMonth();
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}

		return salesXMonthList;
	}
}
