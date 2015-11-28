/**
 * 
 */
package org.dimigo.pay;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author dexterastin
 *
 */
//Charge.fxml을 제어하기 위한 컨트롤러
public class ChargeController {

	@FXML
	private TextField txtCode; // 이름은 입력하는 필드
	@FXML
	private TextField txtMoney; // 충전할 금액을 입력하는 필드
	@FXML
	private Button submit; // 확정 버튼
	@FXML
	private Button close; // 닫기 버튼
	@FXML
	private Label result; // 결과를 띄워주는 레이블
	
	// 충전을 하는 함수
	public void Charge(ActionEvent event) {

		String code = txtCode.getText();

		EditMoney em = new EditMoney();
		int ChargeMoney = Integer.parseInt(txtMoney.getText());

		em.editMoney(code, ChargeMoney);

		Connect cn = new Connect();

		cn.Connet(code);

		result.setText(ChargeMoney + "원을 충전하여 " + cn.getUser().getMoney() + "원이 되었습니다.");

	}
	
	// 메인 씬으로 변경
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
