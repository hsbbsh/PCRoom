package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserAppMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/user_login.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
