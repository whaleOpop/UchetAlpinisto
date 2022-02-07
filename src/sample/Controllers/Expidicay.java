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
import sample.Model.Chronicle;
import sample.Model.Group;

public class Expidicay {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Chronicle> Table;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Chronicle, Date> data_end;

    @FXML
    private TableColumn<Chronicle, Date> data_start;

    @FXML
    private TextField datael;

    @FXML
    private TextField datanl;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Chronicle, Integer> group;

    @FXML
    private TextField groupl;

    @FXML
    private TableColumn<Chronicle, Integer> id;

    @FXML
    private TextField mounl;

    @FXML
    private TableColumn<Chronicle, Integer> mountain;

    @FXML
    private TableColumn<Chronicle, String> uspex;

    @FXML
    private TextField uspexl;

    ObservableList<Chronicle> observableList = FXCollections.observableArrayList();
    String ids;

    @FXML
    void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            datanl.setText(Table.getSelectionModel().getSelectedItem().getDate_start().toString());
            datael.setText(Table.getSelectionModel().getSelectedItem().getDate_end().toString());
            Integer gr=Table.getSelectionModel().getSelectedItem().getId_group();
            groupl.setText(gr.toString());
            Integer mo=Table.getSelectionModel().getSelectedItem().getId_mountain();
            mounl.setText(mo.toString());
            Integer k = Table.getSelectionModel().getSelectedItem().getId();
            uspexl.setText(Table.getSelectionModel().getSelectedItem().getSuccess().toString());
            ids = k.toString();
        }
    }

    @FXML
    void initialize() {
        updateTable();
        add.setVisible(LoginM.tf);
        edit.setVisible(LoginM.tf);
        delete.setVisible(LoginM.tf);
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/MainGid.fxml"));
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

        add.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            PreparedStatement prstUcheb;

            if (groupl.getText().equals("") || mounl.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/prak?serverTimezone=UTC", "root", "77322850nN%");

                    prst = cons.prepareStatement("INSERT INTO `prak`.`chronicle` (`date_start`, `date_end`, `id_group`, `id_mountain`, `success`) VALUES ('" + datanl.getText() + "', '" + datael.getText() + "', '" + groupl.getText() + "', '" + mounl.getText() + "', '" + uspexl.getText() + "')");
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

        edit.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if (groupl.getText() == null || mounl.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `prak`.`chronicle` SET `date_start` = '" + datanl.getText() + "', `date_end` = '" + datael.getText() + "', `id_group` = '" + groupl.getText() + "', `id_mountain` = '" + mounl.getText() + "', `success` = '" + uspexl.getText() + "' WHERE (`id` = '" + ids + "')");

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
        delete.setOnAction(event -> {

            Connection cons;
            PreparedStatement prst;
            if (ids.equals(null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("DELETE FROM `prak`.`chronicle` WHERE (`id` = '"+ids+"')");
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
    }
    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM prak.chronicle");
            while (rs.next()) {
                observableList.add(new Chronicle(rs.getInt("id"), rs.getDate("date_start"), rs.getDate("date_end"), rs.getInt("id_group"), rs.getInt("id_mountain"), rs.getString("success")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        data_start.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        data_end.setCellValueFactory(new PropertyValueFactory<>("date_end"));
        group.setCellValueFactory(new PropertyValueFactory<>("id_group"));
        mountain.setCellValueFactory(new PropertyValueFactory<>("id_mountain"));
        uspex.setCellValueFactory(new PropertyValueFactory<>("success"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Table.setItems(observableList);
    }

}