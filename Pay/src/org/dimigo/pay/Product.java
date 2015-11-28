/**
 * 
 */
package org.dimigo.pay;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author dexterastin
 *
 */
public class Product {
	private SimpleStringProperty code;
	private SimpleIntegerProperty price;
	private SimpleIntegerProperty cnt;

	public Product(String code, int price) {
		this.code = new SimpleStringProperty(code);
		this.price = new SimpleIntegerProperty(price);
		this.cnt = new SimpleIntegerProperty(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [code=" + code + ", price=" + price + ", cnt=" + cnt + "]";
	}

	/**
	 * @return the code
	 */
	public SimpleStringProperty getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(SimpleStringProperty code) {
		this.code = code;
	}

	/**
	 * @return the price
	 */
	public SimpleIntegerProperty getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(SimpleIntegerProperty price) {
		this.price = price;
	}

	/**
	 * @return the cnt
	 */
	public SimpleIntegerProperty getCnt() {
		return cnt;
	}

	/**
	 * @param cnt
	 *            the cnt to set
	 */
	public void setCnt(SimpleIntegerProperty cnt) {
		this.cnt = cnt;
	}

}
