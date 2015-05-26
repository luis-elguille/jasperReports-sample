
package org.jasperreportssample;

import java.awt.EventQueue;


public class Run
{
	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		openReportFrame();
	}

	private static void openReportFrame()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportFrame frame = new ReportFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
