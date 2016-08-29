/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartinventorytools;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import smartinventorytools.model.Employee;


/**
 * окно для изменения информации об сотруднике 
 *
 * @author Dima
 */
public class EmployeeEditDialogController implements Initializable {
    
    private Stage dialogStage;
    private Employee employee; 
    
    
    
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField jobPositionField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField firedDateField;
    
    
  
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Employee getEmployee() {
        return employee;
    }

//    задает адресата, информацию о котором будем менять 
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
        
        firstNameField.setText(employee.getFirstName());
        secondNameField.setText(employee.getSecondName());
        lastNameField.setText(employee.getLastName());
        jobPositionField.setText(employee.getJobPosition());
        startDateField.setText(employee.getStartDate());
        firedDateField.setText(employee.getFiredDate());
    }
    
    
    
    
    

    
    
}
