/**
 * 
 */
package org.dimigo.pay;

import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author dexterastin
 *
 */
public class BuyController {
	
	ShopMain shopmain = ShopMain.shopmain;

	@FXML
	private Label totalMoneyLabel;
	
	@FXML private TextField cardcode;
	
	private int totalMoney;

	public BuyController() {
		for (Entry<String, Product> product : shopmain.ProductMap.entrySet()) {
			totalMoney += shopmain.ProductMap.get(product.getKey()).getPrice().getValue();
			System.out.println(totalMoney);
		}
	}

	public void InputInformation(ActionEvent event) {
		System.out.println(cardcode.getText());
	}
}
