/**
 * 
 */
package org.dimigo.pay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author dexterastin
 *
 */
public class ShopController {

	@FXML
	private TextField txtProdCode;
	@FXML
	private TableView<Product> tbv = new TableView<Product>();

	@FXML
	Button buy, charge, addprod;

	ShopMain shopmain = ShopMain.shopmain;

	// private Product prod;
	private String code;

	/**
	* 
	*/

	public ShopController() {

		File file = new File("product.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String str;
			String[] $arr;

			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				$arr = str.split("/");
				shopmain.ProductList.put($arr[0], $arr);
			}

			System.out.println(shopmain.ProductList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Order(ActionEvent event) {

		String tmpCode;
		String tmpName;

		int tmpPrice;

		code = txtProdCode.getText();

		if (!CheckProductList(code, shopmain.ProductList)) {
			System.out.println("없는물건");
		} else {
			tmpCode = shopmain.ProductList.get(code)[0];
			tmpName = shopmain.ProductList.get(code)[1];
			tmpPrice = Integer.parseInt(shopmain.ProductList.get(code)[2]);

			if (CheckCodeMap(shopmain.ProductMap, code))
				AddProduct(shopmain.ProductMap, code, tmpPrice); // 물건 개수 추가
			else
				shopmain.ProductMap.put(code, new Product(code, tmpPrice)); // 물건추가
		}
		System.out.println(shopmain.ProductMap);

	}

	public boolean CheckCodeMap(HashMap<String, Product> ProductMap, String code) {
		boolean tmp = false;

		if (ProductMap.containsKey(code)) {
			tmp = true;
		}

		return tmp;
	}

	public void AddProduct(HashMap<String, Product> ProductMap, String code, int tmpPrice) {
		int cnt = ProductMap.get(code).getCnt().getValue();
		int price = ProductMap.get(code).getPrice().getValue();

		ProductMap.get(code).setCnt(new SimpleIntegerProperty(cnt + 1));
		ProductMap.get(code).setPrice(new SimpleIntegerProperty(price + tmpPrice));
	}

	public void CancleProduct(HashMap<String, Product> ProductMap, String code, int tmpPrice) {
		int cnt = ProductMap.get(code).getCnt().getValue();
		int price = ProductMap.get(code).getPrice().getValue();

		ProductMap.get(code).setCnt(new SimpleIntegerProperty(cnt - 1));
		ProductMap.get(code).setPrice(new SimpleIntegerProperty(price - tmpPrice));

	}

	public boolean CheckProductList(String code, HashMap<String, String[]> ProductList) {
		boolean tmp = false;

		if (ProductList.containsKey(code)) {
			tmp = true;
		}

		return tmp;
	}

	public void TableSet() {

	}

	public void ScenceHandler(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		if (event.getSource() == buy) {
			stage = (Stage) buy.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Buy.fxml"));
		} else {
			stage = (Stage) charge.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Charge.fxml"));
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
