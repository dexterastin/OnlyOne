/**
 * 
 */
package org.dimigo.pay;

import java.io.IOException;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author dexterastin
 *
 */
// Buy.fxml을 제어하기 위한 컨트롤러

public class BuyController {

	ShopMain shopmain = ShopMain.shopmain;

	@FXML
	private Label totalMoneyLabel; // 마지막 결과를 띄워주는 Label

	@FXML
	private TextField cardcode; // 학생증 코드를 입력받는 필드

	@FXML
	private Button close; // 닫는 버튼

	private int totalMoney;// 남은 총 금액
	private User user; // 유저 객체

	public BuyController() {

		// 총금액 계산
		for (Entry<String, Product> product : shopmain.ProductMap.entrySet()) {
			totalMoney += shopmain.ProductMap.get(product.getKey()).getPrice();
			// System.out.println(totalMoney);
		}

		// 엔터입력시 입력된 문자열로 데이터가져오도록 이벤트 설정
		try {
			cardcode.setOnKeyPressed(new EventHandler<KeyEvent>() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see javafx.event.EventHandler#handle(javafx.event.Event)
				 */
				@Override
				public void handle(KeyEvent t) {
					// TODO Auto-generated method stub
					if (t.getCode() == KeyCode.ENTER)
						SubmitPay();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 계산하는 함수
	public void SubmitPay() {

		if (totalMoney > 0) {
			String code = cardcode.getText();

			Connect userConnect = new Connect();

			userConnect.Connet(code);
			user = userConnect.getUser();

			if (user.getMoney() >= totalMoney) {
				// System.out.println("ok");
				EditMoney em = new EditMoney();
				em.editMoney(code, -totalMoney);

				userConnect.Connet(code);
				user = userConnect.getUser();

				totalMoneyLabel.setText("결제를 성공했습니다. " + String.format("%,d", user.getMoney()) + "원" + " 남았습니다.");

			} else {
				// System.out.println("fail");
				totalMoneyLabel.setText("잔액이 부족합니다. 충전해주세요. 현재잔액 : " + String.format("%,d", user.getMoney()) + "원");
			}
		} else {
			totalMoneyLabel.setText("이놈! 어디서 장난질이냐! 어서 물건이나 가져와라!");
		}

	}

	// 메인으로 씬 병경
	public void ChangeMainScence(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;

		stage = (Stage) close.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ShopMain.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
