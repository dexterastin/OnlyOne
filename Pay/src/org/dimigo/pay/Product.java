/**
 * 
 */
package org.dimigo.pay;

/**
 * @author dexterastin
 *
 */

// 물건 객체
public class Product {
	private String code; // 물건 상품코드
	private String name; // 물건 이름
	private int price; // 물건 가격
	private int cnt; // 물건 개수

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
		// 파싱한 리스트중 같은 코드 값을 가지고 있는 물건의 이름을 가져온다.
		this.name = ShopMain.shopmain.ProductList.get(code)[1];
		this.code = code;
		this.price = price;
		this.cnt = cnt;
	}

}
