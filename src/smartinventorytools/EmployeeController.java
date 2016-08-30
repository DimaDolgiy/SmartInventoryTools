/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartinventorytools;

import java.io.IOException;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import smartinventorytools.model.Employee;

/**
 *
 * @author Dima
 */
public class EmployeeController implements Initializable {

    private ObservableList<Employee> employeeData;
    
    @FXML
    private TextField filterField;

    @FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> secondNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> jobPositionColumn;

    @FXML
    private TableColumn<Employee, String> startDateColumn;

    @FXML
    private TableColumn<Employee, String> firedDateColumn;

//  модальное окно для занесения данных о сотруднике
    @FXML
    public void btnAddEmployee(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("view/InsertEmployee.fxml"));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();

    }

//    обновляем таблицу 
    @FXML
    public void btnRefreshTable() throws SQLException {

        String className = "com.mysql.jdbc.Driver";
        String nameDataBase = "admin_inventory";
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String name = "root";
        String password = "root";
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(className);
            url += nameDataBase;
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();

            String viewEmployee = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(viewEmployee);

            employeeData = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setUser_id(Integer.parseInt(resultSet.getString("user_id")));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setSecondName(resultSet.getString("secondName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setJobPosition(resultSet.getString("jobPosition"));
                employee.setStartDate(resultSet.getString("startDate"));
                employee.setFiredDate(resultSet.getString("firedDate"));
                employeeData.add(employee);
            }

            // устанавливаем тип и значение которое должно хранится в колонке
            employeeIdColumn.setCellValueFactory(cellData -> cellData.getValue().user_idProperty().asObject());
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
            secondNameColumn.setCellValueFactory(celldata -> celldata.getValue().secondNameProperty());
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
            jobPositionColumn.setCellValueFactory(cellData -> cellData.getValue().jobPositionProperty());
            startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
            firedDateColumn.setCellValueFactory(cellData -> cellData.getValue().firedDateProperty());

            tableEmployee.setItems(employeeData);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @FXML
    public void btnDelete() throws SQLException {

        
        Employee selected = tableEmployee.getSelectionModel().getSelectedItem();

        if (selected != null) {
            int employeeId = tableEmployee.getSelectionModel().getSelectedItem().getUser_id();
            int selectedIndex = tableEmployee.getSelectionModel().getSelectedIndex();

            String className = "com.mysql.jdbc.Driver";
            String nameDataBase = "admin_inventory";
            String url = "jdbc:mysql://127.0.0.1:3306/";
            String name = "root";
            String password = "root";
            Connection connection = null;
            Statement statement = null;

            try {

                Class.forName(className);
                url += nameDataBase;
                connection = DriverManager.getConnection(url, name, password);
                statement = connection.createStatement();

                String deleteEmpfromDB = "DELETE FROM employee WHERE user_id = " + employeeId + "";
                statement.executeUpdate(deleteEmpfromDB);

                tableEmployee.getItems().remove(selectedIndex);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            } catch (SQLException e) {

                e.printStackTrace();

            } finally {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }

        } else {
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Employee Selected !!!");
            alert.setContentText("Please select a employee from the table and try again ");
            alert.showAndWait();

        }

    }

//    вызываем окно для редактирования пользователя с таблицы 
    public void showEmployeeEditDialog(Employee employee) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getResource("view/EmployeeEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage stage = new Stage();

        stage.setTitle("Edit Person");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

//        передаем адресата в контроллер
        EmployeeEditDialogController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.setEmployee(employee);

        stage.show();

    }

    /**
     * вызывается, когда пользователь кликает по кнопке Редактировать 
     * открывает диалоговое окно для изменения выбраного адресата
     * @throws IOException 
     */
    @FXML
    public void btnEditEmp() throws IOException {

        Employee selectedEmployee = tableEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {

            showEmployeeEditDialog(selectedEmployee);

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Employee Selected !!!");
            alert.setContentText("Please select a employee from the table and try again ");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            btnRefreshTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        FilteredList<Employee> filteredData = new FilteredList<>(employeeData, e -> true);
        filterField.setOnKeyReleased(e -> {
            filterField.textProperty().addListener((observable, oldValue,newValue) -> {
                filteredData.setPredicate((Predicate<? super Employee>) employee -> {
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (employee.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }else if (employee.getSecondName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    
                    return false;
                });
            });
            
            SortedList<Employee> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(tableEmployee.comparatorProperty());
            tableEmployee.setItems(sortedList);
        });

    }

}
