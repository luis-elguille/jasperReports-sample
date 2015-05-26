
package org.jasperreportssample.differentqueries;

import java.util.List;


public class SalesXMonthController
{
	private static String month = "january";


	public static List<SalesXMonth> loadJBCollection( Object connDataSource )
	{
		switch( month )
		{
			case "january" : return( SalesXMonthLogic.calculateSales( "1", connDataSource  ) );

			case "february" : return( SalesXMonthLogic.calculateSales( "2", connDataSource  ) );

			default : return null;
		}
	}

	public static void setMonth( String pMonth ) {
		month = pMonth;
	}
}
