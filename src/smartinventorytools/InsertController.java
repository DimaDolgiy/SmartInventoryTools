/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartinventorytools;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Dima
 */
public class InsertController implements Initializable {

    EmployeeController controller;

    //    поля для занесения данных с формы
    @FXML
    private TextField txtfirstName;
    @FXML
    private TextField txtsecondName;

    @FXML
    private TextField txtlastName;

    @FXML
    private TextField txtjobPosition;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpFiredDate;

    //    insert information employee to database
    @FXML
    public void btnInsertEmp(ActionEvent actionEvent) throws SQLException {

        String className = "com.mysql.jdbc.Driver";
        String nameDataBase = "admin_inventory";
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String name = "root";
        String password = "root";
        Connection connection;
        Statement statement;

        try {
            Class.forName(className);
            url += nameDataBase;
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();

            String insertEmployee = "INSERT INTO employee (firstName, secondName, lastName, jobPosition, startDate, firedDate) VALUES  ('" + txtfirstName.getText() + "',"
                    + "'" + txtsecondName.getText() + "', '" + txtlastName.getText() + "', '" + txtjobPosition.getText() + "', '" + dpStartDate.getEditor().getText() + "', '" + dpFiredDate.getEditor().getText() + "');";
            statement.executeUpdate(insertEmployee);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        actionClose(actionEvent);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Employee inserted");
        alert.setContentText("First Name: " + txtfirstName.getText() + "\n"
                + "Second Name: " + txtsecondName.getText());
        alert.showAndWait();

    }

//    кнопка закрывает окно 
    public void actionClose(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
