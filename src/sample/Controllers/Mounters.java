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
import sample.Model.Mounter;

public class Mounters {


    @FXML
    private TableColumn<Mounter, String> Login;
    @FXML
    private TableView<Mounter> Table;

    @FXML
    private Button add;

    @FXML
    private TableColumn<Mounter, String> adress;

    @FXML
    private TextField adressl;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Mounter, Date> data;
    @FXML
    private TableColumn<Mounter, String> password;
    @FXML
    private TextField datal;
    @FXML
    private TextField loginll;

    @FXML
    private TextField passl;
    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Mounter, String> fio;

    @FXML
    private TextField fiol;

    @FXML
    private TableColumn<Mounter, String> gender;

    @FXML
    private TableColumn<Mounter, String> group;

    @FXML
    private TextField groupl;

    @FXML
    private TableColumn<Mounter, Integer> id;

    @FXML
    private TextField poll;

    @FXML
    private TableColumn<Mounter, String> title;

    @FXML
    private TextField titlel;

    String ids;
    ObservableList<Mounter> observableList = FXCollections.observableArrayList();

    @FXML
    void clickItem(MouseEvent event) {

        if (event.getClickCount() == 2) //Checking double cwsslick
        {
            loginll.setText(Table.getSelectionModel().getSelectedItem().getLogin().toString());
            passl.setText(Table.getSelectionModel().getSelectedItem().getPassword().toString());
            fiol.setText(Table.getSelectionModel().getSelectedItem().getFio().toString());
            Integer i = Table.getSelectionModel().getSelectedItem().getId_group();
            groupl.setText(i.toString());
            datal.setText(Table.getSelectionModel().getSelectedItem().getDate_birth().toString());
            adressl.setText(Table.getSelectionModel().getSelectedItem().getAdress().toString());
            titlel.setText(Table.getSelectionModel().getSelectedItem().getTitle().toString());
            poll.setText(Table.getSelectionModel().getSelectedItem().getGender().toString());



        }
    }

    @FXML
    void initialize() {
        updateTable();
        add.setVisible( LoginM.tf);
        edit.setVisible( LoginM.tf);
        delete.setVisible( LoginM.tf);
        delete.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if(ids==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("DELETE FROM `prak`.`mountaineer` WHERE (`Login` = '"+loginll.getText()+"')");

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
            if(fiol.getText().equals("")||datal.getText().equals("")||adressl.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `prak`.`mountaineer` SET `Login` = '"+loginll.getText()+"', `fio` = '"+fiol.getText()+"', `date_birth` = '"+datal.getText()+"', `adress` = '"+adressl.getText()+"', `title` = '"+titlel.getText()+"', `gender` = '"+poll.getText()+"', `id_group` = '"+groupl.getText()+"', `Password` = '"+passl.getText()+"' WHERE (`Login` = '"+Table.getSelectionModel().getSelectedItem().getLogin()+"')");

                            prst.executeUpdate();
                    Table.getItems().clear();
                    updateTable();


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
        add.setOnAction(event -> {
            Connection cons;

            PreparedStatement prstUcheb;
            if(loginll.getText().equals("")||passl.getText().equals("")||poll.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните пустые поля");
                alert.showAndWait();
            }else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cons = DriverManager.getConnection("jdbc:mysql://localhost/prak?serverTimezone=UTC", "root", "77322850nN%");


                prstUcheb = cons.prepareStatement("INSERT INTO `prak`.`mountaineer` (`Login`, `fio`, `date_birth`, `adress`, `title`, `gender`, `id_group`, `Password`)  VALUES ('"+loginll.getText()+"','"+fiol.getText()+"', '"+datal.getText()+"', '"+adressl.getText()+"', '"+titlel.getText()+"', '"+poll.getText()+"', "+groupl.getText()+","+passl.getText()+")");
                prstUcheb.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Table.getItems().clear();
            updateTable();
            }
        });
    }

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM prak.mountaineer");
            while (rs.next()) {
                observableList.add(new Mounter(rs.getString("Login"), rs.getString("fio"),
                        rs.getDate("date_birth"),
                        rs.getString("adress"),
                        rs.getString("title"),
                        rs.getString("gender"),
                        rs.getInt("id_group"),rs.getString("Password")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Login.setCellValueFactory(new PropertyValueFactory<>("Login"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        data.setCellValueFactory(new PropertyValueFactory<>("date_birth"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        group.setCellValueFactory(new PropertyValueFactory<>("id_group"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        Table.setItems(observableList);
    }

}
