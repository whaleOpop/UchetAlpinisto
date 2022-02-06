package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogGid {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Login;

    @FXML
    private Button back;
    @FXML
    private Button reg;
    @FXML
    private TextField log;
    static String Logins;
    @FXML
    private TextField pass;

    @FXML
    void initialize() {
        reg.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/RegGid.fxml"));
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
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/sample.fxml"));
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
        Login.setOnAction(event -> {
            Connection con;
            PreparedStatement prst;
            ResultSet rs;
            if (log.getText().isEmpty() || pass.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {
                try {
                    System.out.println("запрос бд");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/prak?serverTimezone=UTC", "root", "77322850nN%");
                    prst = con.prepareStatement("SELECT * FROM prak.gid where Login=? and Password=?");
                    prst.setString(1, log.getText());
                    prst.setString(2, pass.getText());
                    rs = prst.executeQuery();
                    System.out.println(log.getText());
                    System.out.println(pass.getText());
                    if (rs.next()) {
                        Logins = log.getText();
                        LoginM.tf = true;
                        //  JOptionPane.showMessageDialog(null, "Тип топ ");
                        System.out.println("ok");
                        log.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/view/MainGid.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));

                        stage.show();
                        stage.setResizable(false);

                    } else {
                        // JOptionPane.showMessageDialog(null, "ты ебобо");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Упс");
                        alert.setHeaderText("Ошибка");
                        alert.setContentText("Такого пользователя нет");
                        alert.showAndWait();

                    }


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}