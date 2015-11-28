/**
 * 
 */
package org.dimigo.pay;

/**
 * @author dexterastin
 *
 */
public class Product {
	private String code;
	private String name;
	private int price;
	private int cnt;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the cnt
	 */
	public int getCnt() {
		return cnt;
	}

	/**
	 * @param cnt
	 *            the cnt to set
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Product(String code, int price, int cnt) {
		super();
		this.name = ShopMain.shopmain.ProductList.get(code)[1];
		this.code = code;
		this.price = price;
		this.cnt = cnt;
	}

}
