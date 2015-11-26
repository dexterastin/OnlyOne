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

	protected HashMap<String, Product> ProductMap = new HashMap<String, Product>();
	protected HashMap<String, String[]> ProductList = new HashMap<String, String[]>();

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
