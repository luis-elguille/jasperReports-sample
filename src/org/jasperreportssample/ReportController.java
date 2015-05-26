
package org.jasperreportssample;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;

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


public class ReportController implements ActionListener
{
	private ReportFrame				frame;
	private ConnectionDataSource	cds = null;

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
		simpleReport			= new JReport( "sales", "sales", simpleReportFile );

		File salesTemplateFile	= new File( this.getClass().getResource( "/reports/salesTemplate.jasper" ).getPath() );
		salesTemplate			= new JReport( "sales", "sales", salesTemplateFile );

		File parameterizedFile	= new File( this.getClass().getResource( "/reports/salesParameterized.jasper" ).getPath() );
		parameterized		 	= new JReport( "sales", "sales", parameterizedFile );

		File pieChartFile	= new File( this.getClass().getResource( "/reports/pieChart.jasper" ).getPath() );
		pieChart			= new JReport( "Pie Chart", "sales", pieChartFile );

		File barChartFile	= new File( this.getClass().getResource( "/reports/barChart.jasper" ).getPath() );
		barChart			= new JReport( "Bar Chart", "sales", barChartFile );

		File lineChartFile	= new File( this.getClass().getResource( "/reports/lineChart.jasper" ).getPath() );
		lineChart			= new JReport( "Line Chart", "sales", lineChartFile );

		File inventoryFile		= new File( this.getClass().getResource( "/reports/inventario.jasper" ).getPath() );
		inventory				= new JReport( "inventory", "inventory", inventoryFile );

		File collectionJavaBeansFile	= new File( this.getClass().getResource( "/reports/productCollection.jasper" ).getPath() );
		collectionJavaBeans				= new JReport( "Collection JavaBeanas", "inventory", collectionJavaBeansFile );

		File differentQueriesFile	= new File( this.getClass().getResource( "/reports/DifferentQueries.jasper" ).getPath() );
		differentQueriesReport		= new JReport( "sales", "sales", differentQueriesFile );
	}

	public void actionPerformed( ActionEvent event )
	{
		if( event.getSource().equals( frame.getComboBox() ) ) {
			eventInComboBox();
		}
		else
		{
			JReport report = chooseReportToGenerate();

			if( event.getSource().equals( frame.getBtnOpen() ) ) {		
				report.showViewer();
			}
			else if( event.getSource().equals( frame.getBtnEmbedded() ) ) {
				openReportEmbbebed( report );
			}
			else if( event.getSource().equals( frame.getBtnExportToPDF() ) )
			{
				report.exportToPDF( "C:\\reports\\report.pdf" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a PDF con exito" );
			}
			else if( event.getSource().equals( frame.getBtnExportExcel() ) )
			{
				report.exportToEXCEL( "C:\\reports\\report.xls" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a Excel con exito" );
			}			
			else if( event.getSource().equals( frame.getBtnExportHtml() ) )
			{
				report.exportToHTML( "C:\\reports\\report.html" );
				JOptionPane.showMessageDialog( null, "Reporte exportado a HTML con exito" );
			}
		}
	}

	private void eventInComboBox()
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

	private JReport chooseReportToGenerate()
	{
		switch( frame.getComboBox().getSelectedIndex() )
		{
			case 0:
				return( generateReport( simpleReport ) );

			case 1:
				return( generateReport( salesTemplate ) );

			case 2:
				Integer parameter = Integer.valueOf( frame.getTxtProductId().getText() );
				parameterized.setParameter( "prtId", parameter ); 
				return( generateReport( parameterized ) );

			case 3:
				return( generateReport( pieChart ) );				

			case 4:
				return( generateReport( barChart ) );

			case 5:
				return( generateReport( lineChart ) );

			case 6:
				File cvsFile = new File( this.getClass().getResource( "/reports/dataSources/Inventario.csv" ).getPath() );
				inventory.generateReportFromCSVFile( cvsFile );
				return( inventory );

			case 7:
				return( generateReportFromJBCollection() );

			case 8:
				return( generateReportDifferentQueries() );

			default:
				return( null );
		}
	}

	private JReport generateReport( JReport report )
	{
		connectToDataSource();

		report.generateReportFromFile( cds.getConnection() );
		cds.disconnect();

		return report;
	}

	private JReport generateReportFromJBCollection()
	{
		collectionJavaBeans.generateReportFromFile( ProductController.loadProductCollection() );

		return collectionJavaBeans;
	}

	private JReport generateReportDifferentQueries()
	{
		connectToDataSource();
		setSelectedMonth();

		differentQueriesReport.generateReportFromFile( SalesXMonthController.loadJBCollection( cds ) );
		cds.disconnect();

		return differentQueriesReport;
	}

	private void connectToDataSource()
	{
		DataSource ds = new DataSource( 0, "localhost", "sales" );
		cds = new ConnectionDataSource( "root", "123", ds );
		cds.connect();
	}

	private void setSelectedMonth()
	{
		if( frame.getCbxMonth().getSelectedIndex() == 0 )
			SalesXMonthController.setMonth( "january" );
		else
			SalesXMonthController.setMonth( "february" );
	}

	private void openReportEmbbebed( JReport report )
	{
		frame.getEmbeddedPanel().removeAll();
		JPanel viewer = report.showEmbbebedViewer();
		viewer.setBounds( 5, 5, 850, 569 );

		frame.getEmbeddedPanel().add( viewer );
	}

}
