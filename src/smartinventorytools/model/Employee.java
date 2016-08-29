/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartinventorytools.model;

import java.util.Objects;
import javafx.beans.property.*;






/**
 *
 * @author Dima
 */
public class Employee {
    
    private final IntegerProperty user_id;
    private final StringProperty firstName;
    private final StringProperty secondName;
    private final StringProperty lastName;
    private final StringProperty jobPosition;
    private final StringProperty startDate;
    private final StringProperty firedDate;


    /**
     *
     */
    
    public Employee() {
        this.user_id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.secondName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.jobPosition = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
        this.firedDate = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return "Employee{" + "user_id=" + user_id + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName + ", jobPosition=" + jobPosition + ", startDate=" + startDate + ", firedDate=" + firedDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.user_id);
        hash = 83 * hash + Objects.hashCode(this.firstName);
        hash = 83 * hash + Objects.hashCode(this.secondName);
        hash = 83 * hash + Objects.hashCode(this.lastName);
        hash = 83 * hash + Objects.hashCode(this.jobPosition);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.firedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.jobPosition, other.jobPosition)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.firedDate, other.firedDate)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    

    public int getUser_id() {
        return user_id.get();
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }
    
    public IntegerProperty user_idProperty(){
        return user_id;
    }
    
///////////////////////////////

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty(){
        return firstName;
    }
  /////////////////////////////  

    public String getSecondName() {
        return secondName.get();
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }
    
    public StringProperty secondNameProperty(){
        return secondName;
    }
    
    /////////////////////////////

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }

    //////////////////////////////
    
    public String getJobPosition() {
        return jobPosition.get();
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition.set(jobPosition);
    }
    
    public StringProperty jobPositionProperty(){
        return jobPosition;
    }
    
    ///////////////////////////////

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }
    
    public StringProperty startDateProperty(){
        return startDate;
    }
    
    ///////////////////////////////

    public String getFiredDate() {
        return firedDate.get();
    }

    public void setFiredDate(String firedDate) {
        this.firedDate.set(firedDate);
    }
    
    public StringProperty firedDateProperty(){
        return firedDate;
    }

   


    
    
    
    
    
}
