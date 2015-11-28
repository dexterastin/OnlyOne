/**
 * 
 */
package org.dimigo.pay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

		shopmain.ProductList.clear();
		shopmain.ProductMap.clear();

		File file = new File("product.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String str;
			String[] $arr;

			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				$arr = str.split("/");
				shopmain.ProductList.put($arr[0], $arr);
			}

			// System.out.println(shopmain.ProductList);

			txtProdCode.setOnKeyPressed(new EventHandler<KeyEvent>() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see javafx.event.EventHandler#handle(javafx.event.Event)
				 */
				@Override
				public void handle(KeyEvent t) {
					// TODO Auto-generated method stub
					if (t.getCode() == KeyCode.ENTER) {
						AddProductList();
					} else {
					}
				}
			});

		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public void AddProductList() {

		String tmpCode;
		String tmpName;

		int nowPrice;

		code = txtProdCode.getText();

		if (!CheckProductList(code, shopmain.ProductList)) {
			System.out.println("없는물건");
		} else {
			tmpCode = shopmain.ProductList.get(code)[0];
			tmpName = shopmain.ProductList.get(code)[1];
			nowPrice = Integer.parseInt(shopmain.ProductList.get(code)[2]);

			if (CheckCodeMap(shopmain.ProductMap, code))
				AddProduct(shopmain.ProductMap, code); // 물건 개수 추가
			else
				shopmain.ProductMap.put(code, new Product(code, nowPrice, 1)); // 물건추가
		}
		System.out.println(shopmain.ProductMap);

		txtProdCode.clear();

		tbv.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {
					System.out.println("fuck");
					CancleProduct(shopmain.ProductMap, shopmain.ProductList.get(code)[0]);
				}
			}
		});

		setTable();

	}

	public void setTable() {

		ObservableList<Product> data = FXCollections.observableArrayList(new ArrayList(shopmain.ProductMap.values()));

		System.out.println(new ArrayList(shopmain.ProductMap.values()));

		tbv.setItems(data);

		TableColumn nameCol = tbv.getColumns().get(0);
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

		TableColumn cntCol = tbv.getColumns().get(1);
		cntCol.setCellValueFactory(new PropertyValueFactory<Product, String>("cnt"));

		TableColumn priceCol = tbv.getColumns().get(2);
		priceCol.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));

	}

	public boolean CheckCodeMap(HashMap<String, Product> ProductMap, String code) {
		boolean tmp = false;

		if (ProductMap.containsKey(code)) {
			tmp = true;
		}

		return tmp;
	}

	public void AddProduct(HashMap<String, Product> ProductMap, String code) {
		int cnt = ProductMap.get(code).getCnt();
		int price = ProductMap.get(code).getPrice();

		System.out.println(code);

		if (cnt == 0)
			++cnt;

		ProductMap.get(code).setCnt(cnt + 1);
		ProductMap.get(code).setPrice((price / cnt) * (cnt + 1));

	}

	public void CancleProduct(HashMap<String, Product> ProductMap, String code) {
		int cnt = ProductMap.get(code).getCnt();
		int price = ProductMap.get(code).getPrice();

		if (cnt == 0)
			++cnt;

		ProductMap.get(code).setCnt(cnt - 1);
		ProductMap.get(code).setPrice((price / cnt) * (cnt - 1));

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
