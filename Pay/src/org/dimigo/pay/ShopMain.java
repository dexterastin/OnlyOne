/**
 * 
 */
package org.dimigo.pay;

import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author dexterastin
 *
 */
public class ShopMain extends Application {

	protected HashMap<String, Product> ProductMap = new HashMap<String, Product>(); // 사용자가 구매하려는 물건들
	protected HashMap<String, String[]> ProductList = new HashMap<String, String[]>();// 현재 상점에 있는 물건들

	static ShopMain shopmain = new ShopMain();

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ShopMain.fxml"));
		stage.setScene(new Scene(root));

		stage.show();

	}
}
