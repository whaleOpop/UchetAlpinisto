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
import sample.Model.Group;
import sample.Model.Mouns;

public class Groups {


    @FXML
    private TableView<Group> Table;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Group, Integer> id;

    @FXML
    private TextField kolvo;

    @FXML
    private TableColumn<Group, Integer> kolvos;

    @FXML
    private TableColumn<Group, String> login;
    int random_number1;
    @FXML
    private TableColumn<Group, String> name;

    @FXML
    private TextField namel;

    @FXML
    private TableColumn<Group, ?> names;
    ObservableList<Group> observableList = FXCollections.observableArrayList();
    String ids;

    @FXML
    void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            Integer i = Table.getSelectionModel().getSelectedItem().getCount_p();
            kolvo.setText(i.toString());
            namel.setText(Table.getSelectionModel().getSelectedItem().getName_g().toString());
            Integer k = Table.getSelectionModel().getSelectedItem().getId();
            ids = k.toString();
        }
    }

    @FXML
    void initialize() {
        updateTable();
        add.setVisible( LoginM.tf);
        edit.setVisible( LoginM.tf);
        delete.setVisible( LoginM.tf);
        add.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            PreparedStatement prstUcheb;

            if(namel.getText().equals("")||kolvo.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/prak?serverTimezone=UTC", "root", "77322850nN%");

                    prst = cons.prepareStatement("INSERT INTO `prak`.`grup` (`name_g`, `count_p`, `Login`) VALUES ('" + namel.getText() + "', '" + kolvo.getText() + "', '" + LogGid.Logins + "')");
                    prst.executeUpdate();
                    String text = kolvo.getText();

                    for (int i = 0; i < Integer.parseInt(kolvo.getText()); i++) {
                        int a = 0;
                        int b = 9999;
                        random_number1 = (a + (int) (Math.random() * b)) + i;
                        prstUcheb = cons.prepareStatement("INSERT INTO `prak`.`mountaineer` (`Login`, `fio`, `date_birth`, `adress`, `title`, `gender`, `id_group`, `Password`) VALUES ('" + namel.getText() + random_number1 + "','Фамилия', '2003-12-01', 'Адрес', 'Звание', 'пол', (SELECT max(id) FROM prak.grup),'Пароль')");

                        prstUcheb.executeUpdate();
                    }
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
            if(name.getText()==null||kolvo.getText()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `prak`.`grup` SET `name_g` = '"+namel.getText()+"', `count_p` = '"+kolvo.getText()+"' WHERE (`id` = '"+ids+"')");

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
            if(name.getText()==null||kolvo.getText()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("DELETE FROM `prak`.`grup` WHERE (`id` = '"+ids+"')");
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
    }

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM prak.grup");
            while (rs.next()) {
                observableList.add(new Group(rs.getInt("id"), rs.getString("name_g"), rs.getInt("count_p"), rs.getString("Login")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        login.setCellValueFactory(new PropertyValueFactory<>("Login"));
        kolvos.setCellValueFactory(new PropertyValueFactory<>("count_p"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name_g"));

        Table.setItems(observableList);
    }
}