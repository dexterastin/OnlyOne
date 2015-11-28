/**
 * 
 */
package org.dimigo.pay;

/**
 * <pre>
 * org.dimigo.shop
 *	|_ User
 *
 * 1. 개요 : 
 * 2.작성일 : 2015. 11. 25.
 * </pre>
 *
 * @author	: 석종규
 * @version : 1.0
 */
public class User {
	private int money = 0;
	private String code;
	
	public User(){
		
	}
	
	public User(int money, String code) {
		super();
		this.money = money;
		this.code = code;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [money=" + money + ", code=" + code + "]";
	}
	/**
	 * @param money
	 * @param code
	 */
}
