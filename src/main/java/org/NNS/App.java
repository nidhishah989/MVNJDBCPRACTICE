package org.NNS;

import org.NNS.DAO.EmployeeDAO;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {


        EmployeeDAO employee = new EmployeeDAO();
        System.out.println(employee.createNewEmployee());
        employee.getEmployeeByEmployeeNumber();
        employee.updateEmployeeData();
    }


}
