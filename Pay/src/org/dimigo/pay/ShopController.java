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
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.PromptData;
import javafx.stage.Stage;

/**
 * @author dexterastin
 *
 */
// Shop.fxml을 제어하기 위한 컨트롤러
public class ShopController {

	@FXML
	private TextField txtProdCode; // 물건 바코드를 입력 받기 위한 필드

	@FXML
	private TableView<Product> tbv = new TableView<Product>(); // 입력한 물건들을 띄우기
																// 위한 테이블
	
	@FXML private Label totalmoney, error;

	@FXML
	Button buy, charge; // 확정, 충전 버튼

	ShopMain shopmain = ShopMain.shopmain; // 정적 변수를 편하게 쓰기위해 선언

	// private Product prod;
	private String code; // 입력된 물건 바코드

	private ObservableList<Product> data = FXCollections.observableArrayList(); // 테이블을
																				// 위한
																				// 변수
	// 총금액
	private int totmoney = 0;

	/**
	* 
	*/

	public ShopController() {

		shopmain.ProductList.clear();
		shopmain.ProductMap.clear();

		/////// 물건 리스트 텍스트를 파싱하여 정보등록///////
		File file = new File("product.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String str;
			String[] $arr;

			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				$arr = str.split("/");
				shopmain.ProductList.put($arr[0], $arr);
			}

			//////////////////////////////////////////////////

			// System.out.println(shopmain.ProductList);

			// KeyPress 이벤트 설정.

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
						Order();
					} else {
					}
				}
			});
			////////////////////////////////////////////////////
		} catch (Exception e) {
			// e.printStackTrace();

		}

	}

	// 물건 관리 함수
	public void Order() {

		String tmpCode;
		String tmpName;

		int nowPrice;

		code = txtProdCode.getText();

		if (!CheckProductList(code, shopmain.ProductList)) {
			System.out.println("없는물건");
			error.setText("없는 물건입니다. 물건을 추가해주세요.");
		} else {
			error.setText("정상작동중");
			tmpCode = shopmain.ProductList.get(code)[0];
			tmpName = shopmain.ProductList.get(code)[1];
			nowPrice = Integer.parseInt(shopmain.ProductList.get(code)[2]);

			if (CheckCodeMap(shopmain.ProductMap, code))
				AddProduct(shopmain.ProductMap, code); // 물건 개수 추가
			else
				shopmain.ProductMap.put(code, new Product(code, nowPrice, 1)); // 물건추가
		}

		// System.out.println(shopmain.ProductMap);ㄴ

		/*
		 * tbv.setOnMouseClicked(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) {
		 * 
		 * if (event.getClickCount() == 2) { // System.out.println("ss");
		 * 
		 * System.out.println(event.getTarget().getClass().toString());
		 * CancleProduct(shopmain.ProductMap,
		 * shopmain.ProductList.get(code)[0]); } } });
		 */

		setTable();
		
		for (Entry<String, Product> product : shopmain.ProductMap.entrySet()) {
			totmoney += shopmain.ProductMap.get(product.getKey()).getPrice();
		}
		
		totalmoney.setText(String.format("%,d", totmoney) + "원");

		txtProdCode.clear();

	}

	// 테이블 세팅 함수
	public void setTable() {
		data.clear();

		data.addAll(new ArrayList(shopmain.ProductMap.values()));

		// System.out.println(new ArrayList(shopmain.ProductMap.values()));

		tbv.setItems(data);

		TableColumn nameCol = tbv.getColumns().get(0);
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

		TableColumn cntCol = tbv.getColumns().get(1);
		cntCol.setCellValueFactory(new PropertyValueFactory<Product, String>("cnt"));

		TableColumn priceCol = tbv.getColumns().get(2);
		priceCol.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));

	}

	// 상품이 등록되어 있는지 없는지 확인
	public boolean CheckCodeMap(HashMap<String, Product> ProductMap, String code) {
		boolean tmp = false;

		if (ProductMap.containsKey(code)) {
			tmp = true;
		}

		return tmp;
	}

	// 물건 추가
	public void AddProduct(HashMap<String, Product> ProductMap, String code) {
		int cnt = ProductMap.get(code).getCnt();
		int price = ProductMap.get(code).getPrice();

		System.out.println(code);

		if (cnt == 0)
			++cnt;

		ProductMap.get(code).setCnt(cnt + 1);
		ProductMap.get(code).setPrice((price / cnt) * (cnt + 1));

	}

	// 물건 취소
	/*
	 * public void CancleProduct(HashMap<String, Product> ProductMap, String
	 * code) { int cnt = ProductMap.get(code).getCnt(); int price =
	 * ProductMap.get(code).getPrice();
	 * 
	 * if (cnt == 0) { System.out.println(ProductMap.remove(code)); } else {
	 * ProductMap.get(code).setPrice((price / cnt) * (cnt - 1));
	 * ProductMap.get(code).setCnt(cnt - 1); }
	 * 
	 * setTable();
	 * 
	 * }
	 */

	// 물건이 있는지 없는지 확인
	public boolean CheckProductList(String code, HashMap<String, String[]> ProductList) {
		boolean tmp = false;

		if (ProductList.containsKey(code)) {
			tmp = true;
		}

		return tmp;
	}

	// 씬 전환 메소드
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
