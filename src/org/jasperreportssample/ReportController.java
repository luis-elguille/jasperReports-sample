
package org.jasperreportssample;

import java.lang.Runnable;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.utilities.dataaccesslayer.ConnectionDataSource;
import org.utilities.dataaccesslayer.DataSource;
import org.utilities.reports.JReport;

import org.jasperreportssample.differentqueries.SalesXMonthController;
import org.jasperreportssample.collectionJBDataSource.ProductController;


public class ReportController
{
	private ReportFrame				frame;
	private ConnectionDataSource	cds = null;

	private JReport report;
	private JReport	simpleReport;
	private JReport	salesTemplate;
	private JReport parameterized;
	private JReport pieChart;
	private JReport barChart;
	private JReport lineChart;
	private JReport inventory;
	private JReport collectionJavaBeans;
	private JReport	differentQueriesReport;


	public ReportController( ReportFrame frame )
	{
		this.frame = frame;

		createReports();
	}


	private void createReports()
	{
		File simpleReportFile	= new File( this.getClass().getResource( "/reports/sales.jasper" ).getPath() );
		simpleReport			= new JReport( "salesXMonth", "sales", JReport.FILEORIGIN, simpleReportFile );

		File salesTemplateFile	= new File( this.getClass().getResource( "/reports/salesTemplate.jasper" ).getPath() );
		salesTemplate			= new JReport( "salesXMonth", "sales", JReport.FILEORIGIN, salesTemplateFile );

		File parameterizedFile	= new File( this.getClass().getResource( "/reports/salesParameterized.jasper" ).getPath() );
		parameterized		 	= new JReport( "salesXMonth", "sales", JReport.FILEORIGIN, parameterizedFile );

		File pieChartFile	= new File( this.getClass().getResource( "/reports/pieChart.jasper" ).getPath() );
		pieChart			= new JReport( "Pie Chart", "sales", JReport.FILEORIGIN, pieChartFile );

		File barChartFile	= new File( this.getClass().getResource( "/reports/barChart.jasper" ).getPath() );
		barChart			= new JReport( "Bar Chart", "sales", JReport.FILEORIGIN, barChartFile );

		File lineChartFile	= new File( this.getClass().getResource( "/reports/lineChart.jasper" ).getPath() );
		lineChart			= new JReport( "Line Chart", "sales", JReport.FILEORIGIN, lineChartFile );

		File inventoryFile		= new File( this.getClass().getResource( "/reports/inventario.jasper" ).getPath() );
		inventory				= new JReport( "inventoryXMonth", "inventory", JReport.FILEORIGIN, inventoryFile );

		File collectionJavaBeansFile	= new File( this.getClass().getResource( "/reports/productCollection.jasper" ).getPath() );
		collectionJavaBeans				= new JReport( "Collection JavaBeans", "inventory", JReport.FILEORIGIN, collectionJavaBeansFile );

		File differentQueriesFile	= new File( this.getClass().getResource( "/reports/DifferentQueries.jasper" ).getPath() );
		differentQueriesReport		= new JReport( "salesXMonth", "sales", JReport.FILEORIGIN, differentQueriesFile );
	}
// ---------------------------------------------------------

	public void eventInComboBox( ActionEvent event )
	{
		if( frame.getComboBox().getSelectedIndex() == 8 )
		{
			frame.getLblMes().setVisible( true );
			frame.getCbxMonth().setVisible( true );
		}
		else
		{
			frame.getLblMes().setVisible( false );
			frame.getCbxMonth().setVisible( false );
		}

		if( frame.getComboBox().getSelectedIndex() == 2 )
		{
			frame.getLblProductId().setVisible( true );
			frame.getTxtProductId().setVisible( true );
			frame.getLblRange().setVisible( true );
		}
		else
		{
			frame.getLblProductId().setVisible( false );
			frame.getTxtProductId().setVisible( false );
			frame.getLblRange().setVisible( false );
		}
	}
// ---------------------------------------------------------

	private void setUpReport( Runnable strategy )
	{
		report = chooseReportToGenerate();
		chooseDataSource( report );

		strategy.run();
	}

	public void eventInBtnOpen( ActionEvent event ) {
		setUpReport( () -> report.openViewer() );
	}

	public void eventInBtnEmbedded( ActionEvent event )
	{
		setUpReport( () -> {
				frame.getEmbeddedPanel().removeAll();
				JPanel viewer = (JPanel) report.openEmbeddedViewer();
				viewer.setBounds( 5, 5, 850, 569 );
		
				frame.getEmbeddedPanel().add( viewer );
		} );
	}

	public void eventInBtnExportToPDF( ActionEvent event )
	{
		setUpReport( () -> {
				report.exportToPDF( "C:\\reports\\report" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a PDF con exito" );
		} );
	}

	public void eventInBtnExportExcel( ActionEvent event )
	{
		setUpReport( () -> {
				report.exportToEXCEL( "C:\\reports\\report" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a Excel con exito" );
		} );
	}

	public void eventInBtnExportHtml( ActionEvent event )
	{
		setUpReport( () -> {
				report.exportToHTML( "C:\\reports\\report" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a HTML con exito" );
		} );
	}
// ---------------------------------------------------------

	private JReport chooseReportToGenerate()
	{
		switch( frame.getComboBox().getSelectedIndex() )
		{
			case 0:
				return simpleReport;

			case 1:
				return salesTemplate;

			case 2:
				Integer parameter = Integer.valueOf( frame.getTxtProductId().getText() );
				parameterized.setParameter( "prtId", parameter ); 
				return parameterized;

			case 3:
				return pieChart;				

			case 4:
				return barChart;

			case 5:
				return lineChart;

			case 6:
				return inventory ;

			case 7:
				return collectionJavaBeans;

			case 8:
				setSelectedMonth();
				return differentQueriesReport;

			default:
				return null;
		}
	}

	private void chooseDataSource( JReport report )
	{
		int sel = frame.getComboBox().getSelectedIndex();

		if( sel == 6 )
		{
			File csvFile = new File( this.getClass().getResource( "/reports/dataSources/Inventario.csv" ).getPath() );
			inventory.setCSVFile( csvFile );
		}
		else if( sel == 7 ) {
			report.setCollection( ProductController.loadProductCollection() );
		}
		else if ( sel == 8 )
		{
			connectToDataSource();
			report.setCollection( SalesXMonthController.loadJBCollection( cds ) );
		}
		else {
			report.setConnection( connectToDataSource() );
		}
	}

	private Connection connectToDataSource()
	{
		DataSource ds = new DataSource( 0, "localhost", "sales" );
		cds = new ConnectionDataSource( "root", "123", ds );

		return cds.connect();
	}

	private void setSelectedMonth()
	{
		if( frame.getCbxMonth().getSelectedIndex() == 0 )
			SalesXMonthController.setMonth( "january" );
		else
			SalesXMonthController.setMonth( "february" );
	}
}
