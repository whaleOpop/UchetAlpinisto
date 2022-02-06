package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.Mouns;

public class Moun {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Mouns> Table;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    @FXML
    private TextField country;
    @FXML
    private TableColumn<Mouns, String> countrys;

    @FXML
    private TextField heigh;

    @FXML
    private TableColumn<Mouns, Integer> hiegh;

    @FXML
    private TableColumn<Mouns, Integer> id;
    @FXML
    private TableColumn<Mouns, String> rayons;
    @FXML
    private TextField name;
    @FXML
    private TableColumn<Mouns, String> names;
    @FXML
    private TextField rayon;

    ObservableList<Mouns> observableList = FXCollections.observableArrayList();
    String ids;

    @FXML
    void initialize() {
        updateTable();
        add.setVisible(LoginM.tf);
        edit.setVisible(LoginM.tf);
        delete.setVisible(LoginM.tf);
        delete.setOnAction(event -> {

            Connection cons;
            PreparedStatement prst;
            if (name.getText() == null || heigh.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement(" DELETE FROM `prak`.`mountain`  WHERE (`id`='" + ids + "')");
                    prst.executeUpdate();
                    Table.getItems().clear();
                    updateTable();
                    System.out.println(ids);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });
        edit.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if (name.getText().equals("") || heigh.getText().equals("") || country.getText().equals("") || rayon.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `prak`.`mountain` SET `Height` = '" + heigh.getText() + "', `NameM` = '" + name.getText() + "', `Country` = '" + country.getText() + "', `District` = '" + rayon.getText() + "' WHERE (`id` = '" + ids + "')");

                    prst.executeUpdate();
                    Table.getItems().clear();
                    updateTable();
                    System.out.println(ids);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        add.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if (heigh.getText().equals("") || name.getText().equals("") || country.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else{
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("INSERT INTO `prak`.`mountain` (`Height`, `NameM`, `Country`, `District`) VALUES ('" + heigh.getText() + "', '" + name.getText() + "', '" + country.getText() + "', '" + rayon.getText() + "')");
                    prst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Table.getItems().clear();
                updateTable();
            }
        });
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/MainGid.fxml"));
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
            stage.setTitle("");
            stage.show();
        });
    }

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM prak.mountain");
            while (rs.next()) {
                observableList.add(new Mouns(rs.getInt("id"), rs.getInt("Height"), rs.getString("NameM"), rs.getString("Country"), rs.getString("District")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        countrys.setCellValueFactory(new PropertyValueFactory<>("Country"));
        hiegh.setCellValueFactory(new PropertyValueFactory<>("Height"));
        rayons.setCellValueFactory(new PropertyValueFactory<>("District"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        names.setCellValueFactory(new PropertyValueFactory<>("NameM"));
        Table.setItems(observableList);
    }

    @FXML
    public void clickItem(MouseEvent event) {

        if (event.getClickCount() == 2) //Checking double click
        {
            Integer i = Table.getSelectionModel().getSelectedItem().getHeight();
            heigh.setText(i.toString());
            name.setText(Table.getSelectionModel().getSelectedItem().getNameM().toString());
            rayon.setText(Table.getSelectionModel().getSelectedItem().getDistrict().toString());
            country.setText(Table.getSelectionModel().getSelectedItem().getNameM().toString());
            Integer k = Table.getSelectionModel().getSelectedItem().getId();
            ids = k.toString();
        }
    }
}

