
package org.jasperreportssample;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;

import java.util.Map;
import java.util.HashMap;
import java.io.File;

import org.jasperreportssample.differentqueries.SalesXMonthController;
import org.utilities.reports.AbstractJasperReports;
import org.utilities.dataaccesslayer.DataSource;
import org.utilities.dataaccesslayer.ConnectionDataSource;


public class ReportFrame extends JFrame
{
	private ReportController controller;

	private JTabbedPane			tabbedPane;
	private JPanel				embeddedPanel;
	private JPanel				mainPanel;
	private JComboBox<String>	comboBox;
	private JLabel				lblMes;
	private JComboBox			cbxMonth;
	private JLabel				lblProductId;
	private JTextField			txtProductId;
	private JLabel				lblRange; 
	private JButton				btnOpen;
	private JButton				btnEmbedded;
	private JButton				btnExportToPDF;
	private JButton				btnExportExcel;
	private JButton				btnExportHtml;


	public ReportFrame()
	{
		controller	= new ReportController( this );

		initComponents();
	}

	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 609);
		setTitle( "JasperReports Samples" );

		tabbedPane = new JTabbedPane();
		getContentPane().add( tabbedPane );

		mainPanel = new JPanel();
		mainPanel.setLayout( null );
		tabbedPane.addTab( "Reports", mainPanel );

		embeddedPanel = new JPanel();
		embeddedPanel.setLayout( null );
		tabbedPane.addTab( "Reporte Embebido", embeddedPanel );

		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		controlsPanel.setLayout( null );
		controlsPanel.setBounds( 104, 46, 448, 344 );
		mainPanel.add( controlsPanel );
		
		JLabel lblType = new JLabel("Tipo de Reporte");
		lblType.setBounds(35, 32, 105, 29);
		controlsPanel.add(lblType);

		comboBox = new JComboBox<String>();
		comboBox.addItem( "Reporte Sencillo" );
		comboBox.addItem( "Reporte con Template" );
		comboBox.addItem( "Reporte Parametrizado" );
		comboBox.addItem( "Reporte de Grafico de Tortas" );
		comboBox.addItem( "Reporte de Grafico de Barras" );
		comboBox.addItem( "Reporte de Grafico de Lineas" );
		comboBox.addItem( "Reporte creado con una Hoja de Excel" );
		comboBox.addItem( "Reporte creado con Collecion de JavaBeans" );
		comboBox.addItem( "Reporte creado con Diferentes Queries" );
		comboBox.setBounds(130, 29, 292, 34);
		comboBox.addActionListener( controller );
		controlsPanel.add(comboBox);
		
		lblMes = new JLabel();
		lblMes.setText("Mes");
		lblMes.setBounds(35, 88, 39, 29);
		controlsPanel.add(lblMes);
		lblMes.setVisible( false );

		cbxMonth = new JComboBox();
		cbxMonth.addItem( "January" );
		cbxMonth.addItem( "February" );
		cbxMonth.setBounds(84, 88, 159, 29);
		controlsPanel.add( cbxMonth );
		cbxMonth.setVisible( false );

		lblProductId = new JLabel();
		lblProductId.setText( "Id" );
		lblProductId.setBounds(35, 88, 39, 29);
		controlsPanel.add( lblProductId );
		lblProductId.setVisible( false );

		txtProductId = new JTextField();
		txtProductId.setBounds(84, 88, 100, 29);
		controlsPanel.add(txtProductId);
		txtProductId.setColumns(10);
		txtProductId.setVisible( false );

		lblRange = new JLabel();
		lblRange.setText( "choose id between 1, 2, 3, 4, 5" );
		lblRange.setBounds( 186, 88, 250, 29 );
		controlsPanel.add( lblRange );
		lblRange.setVisible( false );

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonsPanel.setLayout( null );
		buttonsPanel.setBounds( 639, 46, 207, 342 );
		mainPanel.add( buttonsPanel );
		
		btnOpen = new JButton("Open");
		btnOpen.setBounds(54, 11, 110, 46);
		btnOpen.addActionListener( controller );
		buttonsPanel.add(btnOpen);
								
		btnEmbedded = new JButton("Embedded");
		btnEmbedded.setBounds(54, 72, 110, 46);
		btnEmbedded.addActionListener( controller );
		buttonsPanel.add(btnEmbedded);
								
		btnExportToPDF = new JButton("Exportar PDF");
		btnExportToPDF.setBounds(54, 140, 110, 46);
		btnExportToPDF.addActionListener( controller );
		buttonsPanel.add(btnExportToPDF);
								
		btnExportExcel = new JButton("Export Excel");
		btnExportExcel.setBounds(54, 206, 110, 46);
		btnExportExcel.addActionListener( controller );
		buttonsPanel.add(btnExportExcel);
										
		btnExportHtml = new JButton("Export HTML");
		btnExportHtml.setBounds(54, 272, 110, 46);
		btnExportHtml.addActionListener( controller );
		buttonsPanel.add(btnExportHtml);
		
		setVisible( true );
	}

	public JPanel getEmbeddedPanel() {
		return this.embeddedPanel;
	}

	public JComboBox getComboBox() {
		return this.comboBox;
	}

	public JLabel getLblMes() {
		return this.lblMes;
	}

	public JComboBox getCbxMonth() {
		return this.cbxMonth;
	}

	public JLabel getLblProductId() {
		return this.lblProductId;
	}

	public JTextField getTxtProductId() {
		return this.txtProductId;
	}

	public JLabel getLblRange() {
		return this.lblRange;
	}

	public JButton getBtnOpen() {
		return this.btnOpen;
	}

	public JButton getBtnEmbedded() {
		return btnEmbedded; 
	}

	public JButton getBtnExportToPDF() {
		return this.btnExportToPDF;
	}

	public JButton getBtnExportExcel() {
		return btnExportExcel;
	}

	public JButton getBtnExportHtml() {
		return btnExportHtml;
	}	
}
