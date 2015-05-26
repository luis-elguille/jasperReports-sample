
package org.jasperreportssample.collectionJBDataSource;


public class Product
{
	private int		id;
	private String	referencia;
	private int		stock;
	private boolean state;

	public Product() {}

	public Product( int id, String referencia, int stock, boolean state )
	{
		this.id			= id;
		this.referencia	= referencia;
		this.stock		= stock;
		this.state		= state;
	}

	public int getId() {
		return this.id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia( String referencia ) {
		this.referencia = referencia;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	
}
