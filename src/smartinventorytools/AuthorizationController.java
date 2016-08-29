/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartinventorytools;



import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Dima
 */
public class AuthorizationController implements Initializable{
    
    private String loginAdmin;
    private String passwordAdmin;

    public AuthorizationController() {
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private TextField txtUserName;
    
    @FXML
    private PasswordField txtPassword;
    
    
//  метод для авторизации 
   @FXML
    public void btnSignIn(ActionEvent actionEvent) throws IOException {
        

        String className = "com.mysql.jdbc.Driver";
        String nameDataBase = "admin_inventory";
        String url =  "jdbc:mysql://127.0.0.1:3306/";
        String name = "root";
        String password = "root";
        Connection connection;
        
        try{
            Class.forName(className);
            url += nameDataBase;
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String sql ="SELECT password_admin FROM admin_data WHERE login_admin = '"+txtUserName.getText()+"';";
            ResultSet resultSet = statement.executeQuery(sql);
            
            
            while(resultSet.next()){
              passwordAdmin = resultSet.getString("password_admin");
              
            }
            
            if (passwordAdmin != null && passwordAdmin.equals(txtPassword.getText())){
                 
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide(); //закрываем окно авторизации
            Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("smart inventory tools");
            stage.show();
            
            }else{
                lblMessage.setText("Username or Password invalid!");
            }
    
        }catch(ClassNotFoundException e){
            
            e.printStackTrace();
        }catch(SQLException e){
            
            e.printStackTrace();
        }
        
    }
    
   
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
}
