package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RootController implements Initializable{
	public Stage primaryStage;
	@FXML private Button btnJoin;
	@FXML private Button btnClose;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnJoin.setOnAction((e)->{handleBtnJoinAction();});
		btnClose.setOnAction((e)->{handleBtnCloseAction();});
	}

private void handleBtnJoinAction() {
	FXMLLoader loader = null;
	Parent root;
	try {
		Stage mainStage = new Stage();
		loader = new FXMLLoader(getClass().getResource("../View/main.fxml"));
		root = loader.load();
		MainController mainController = (MainController) loader.getController();
		mainController.mainStage = mainStage;
		Scene scene = new Scene(root);
		
		mainStage.setScene(scene);
	/*	primaryStage.close();*/
		mainStage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}

}

private void handleBtnCloseAction() {
	Platform.exit();
}


	  private void callAlert(String contentText) {
	      Alert alert=new Alert(AlertType.INFORMATION);
	      alert.setTitle("°æ°í!");
	      alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
	      alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));

	      
	      alert.showAndWait();
	   }
}
