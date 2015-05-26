
package org.jasperreportssample.differentqueries;

import java.util.Date;

public class SalesXMonth
{
	private Date	date;
	private String	reference;
	private int		quantity;
	private Long	amount;

	public SalesXMonth() {}

	public SalesXMonth(Date date, String reference, int quantity, Long amount)
	{
		super();
		this.date		= date;
		this.reference	= reference;
		this.quantity	= quantity;
		this.amount		= amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	
	
}
