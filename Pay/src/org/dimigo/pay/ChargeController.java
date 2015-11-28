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
public class ChargeController {

	@FXML private TextField txtCode;
	@FXML private TextField txtMoney;
	@FXML private Button submit;
	@FXML private Button close;
	@FXML private Label result;

	public void Charge(ActionEvent event) {
		
		String code = txtCode.getText();
		
		EditMoney em = new EditMoney();
		int ChargeMoney = Integer.parseInt(txtMoney.getText());

		em.editMoney(code, ChargeMoney);
		
		Connect cn = new Connect();
		
		cn.Connet(code);
		
		result.setText(ChargeMoney + "원을 충전하여 " + cn.getUser().getMoney() + "원이 되었습니다.");
		
	}
	
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
