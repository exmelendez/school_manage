package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //    TODO Explain new loginModel object further
    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    //Using "option" from Enum Class, below.
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected");
            this.dbstatus.setTextFill(Color.web("#006400"));
            this.dbstatus.setStyle("-fx-font-weight: bold");
        } else {
            this.dbstatus.setText("Not Connected");
            this.dbstatus.setTextFill(Color.web("#FF0000"));
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));

    }

    @FXML
    public void Login(ActionEvent event) {
        try {

//            TODO Explain Comobobox parameter below
            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option) this.combobox.getValue()).toString())) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();

//                TODO explain parameter in SwitchCase
                switch (((option) this.combobox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }

            } else {
                this.loginStatus.setText("Incorrect Username or Password");
            }

        } catch (Exception localException) {

        }
    }

    public void studentLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());

//          TODO  "Why sometimes "new" object and other times cast?"
            StudentsController studentsController = (StudentsController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adminLogin() {

        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
