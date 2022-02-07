package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegGid {



    @FXML
    private Button back;

    @FXML
    private TextField fio;

    @FXML
    private TextField kolvo;

    @FXML
    private TextField log;

    @FXML
    private TextField obraz;

    @FXML
    private TextField pass;

    @FXML
    private Button reg;
    @FXML
    private TextField data;
    @FXML
    private TextField stag;

    @FXML
    void initialize() {

        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/LoginGid.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            back.getScene().getWindow().hide();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));

            stage.show();
        });
        reg.setOnAction(event -> {
            if(log.getText().equals("")||pass.getText().equals("")||kolvo.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {


                Connection cons;
                PreparedStatement prst;
                PreparedStatement prstUcheb;


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");

                    prst = cons.prepareStatement("INSERT INTO `prak`.`gid` (`Login`, `fio`, `date_birth`, `count_v`, `experience`, `education`, `Password`) VALUES ('" + log.getText() + "', '" + fio.getText() + "', '" + data.getText() + "', '" + kolvo.getText() + "', '" + stag.getText() + "', '" + obraz.getText() + "', '" + pass.getText() + "')");


                    prst.executeUpdate();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/View/LoginGid.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    reg.getScene().getWindow().hide();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));

                    stage.show();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    }