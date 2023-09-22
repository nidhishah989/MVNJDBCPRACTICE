package org.NNS.DAO;

import org.NNS.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    private Connection dbconn;

    public EmployeeDAO(){

    }
    public int createNewEmployee() throws SQLException {
        dbconn = DatabaseConnector.getConnectedtoDB();
        String sqlQuery = "INSERT INTO employees (officeCode,firstName,lastName,email,extension,reportsTo,VacationHours,employeeNumber,jobTitle) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = dbconn.prepareStatement(sqlQuery);
        prepStmt.setInt(1, 6);
        prepStmt.setString(2, "Jamil");
        prepStmt.setString(3, "fink");
        prepStmt.setString(4, "JJ@gmail.com");
        prepStmt.setString(5, "2759");
        prepStmt.setInt(6, 1143);
        prepStmt.setInt(7, 9);
        prepStmt.setInt(8, 0003);
        prepStmt.setString(9, "Manager");
        int affectedRows = prepStmt.executeUpdate();
        System.out.println(affectedRows + " row(s) affected !!");
        System.out.println("Employee data has generated successfully for Employee ID-3. ");
        System.out.println("-----------------------------------------");

        return affectedRows;
    }

    public void getEmployeeByEmployeeNumber() throws SQLException {
        dbconn = DatabaseConnector.getConnectedtoDB();
        PreparedStatement prepStmt = null;
        try{
            String sqlQuery = "select * from employees where employeeNumber = ? ";
            prepStmt = dbconn.prepareStatement(sqlQuery);
            prepStmt.setInt(1, 0003);
            // execute select query

            ResultSet rs = prepStmt.executeQuery();
            // Display function to show the Resultset
            while (rs.next()) {
                System.out.println("Employee details whose employee id is 3.");
                System.out.println("FirstName: " + rs.getString("firstName"));
                System.out.println("LastName: " +rs.getString("lastname"));
                System.out.println("Email"+ rs.getString("email"));
                System.out.println("OfficeCode: "+ rs.getString("officeCode"));
                System.out.println("-----------------------------------------");
            }
       } catch (SQLException e){
              e.printStackTrace();
          }
       try {
            prepStmt.close();
            dbconn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateEmployeeData(){
    dbconn = DatabaseConnector.getConnectedtoDB();
    PreparedStatement prepStmt = null;
    try{
        String sqlQuery = "update employees set firstName=? , lastName=? where employeeNumber = ?";
        prepStmt = dbconn.prepareStatement(sqlQuery);
        prepStmt.setString(1, "Gary");
        prepStmt.setString(2, "Larson");
        prepStmt.setLong  (3, 0003);

        // execute update query
        int rowsAffected = prepStmt.executeUpdate();
        if(rowsAffected ==1){
            System.out.println("Employee data has updated for Employee ID -3 ");
            System.out.println("NEW DATA: ");
            getEmployeeByEmployeeNumber();
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    try {
        prepStmt.close();
        dbconn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
