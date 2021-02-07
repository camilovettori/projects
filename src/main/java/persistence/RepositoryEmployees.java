package persistence;


import model.Employees;
import util.DBUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class RepositoryEmployees {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public RepositoryEmployees() {
        connection = DBUtil.getDBConnection();
    }

    public void saveEmployee(Employees emp) {
        String sql = "INSERT INTO employees (firstName, lastName, dateOfBirth, phoneNumber, email, salary)" +
                " VALUES (?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, emp.getFirstName());
            pst.setString(2, emp.getLastName());
            pst.setDate(3, Date.valueOf(emp.getDob()));
            pst.setString(4, emp.getPhone());
            pst.setString(5, emp.getEmail());
            pst.setInt(6, emp.getSalary());
            int result = pst.executeUpdate();
            if(result > 0) {
                System.out.println("Employee saved on database.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateEmployee(Employees emp) {
        String sql = "UPDATE employees SET firstName = ?, lastName = ?, dateOfBirth = ?, phoneNumber = ?, email = ?, salary = ?" +
                " WHERE employeeId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, emp.getFirstName());
            pst.setString(2, emp.getLastName());
            pst.setDate(3, Date.valueOf(emp.getDob()));
            pst.setString(4, emp.getPhone());
            pst.setString(5, emp.getEmail());
            pst.setInt(6, emp.getSalary());
            pst.setInt(7, emp.getEmployeeId());
            int result = pst.executeUpdate();
            if(result > 0) {
                System.out.println("Employee updated on database.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteEmployee(Employees emp) {
        String sql = "DELETE FROM employees WHERE employeeId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, emp.getEmployeeId());

            int result = pst.executeUpdate();
            if(result > 0) {
                System.out.println("Employee deleted on database.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Employees findEmployeeById(int empId) {
        Employees empResult = null;
        String sql = "SELECT * FROM employees WHERE employeeId = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, empId);

            rs = pst.executeQuery();
            if (rs.next()) {
                empResult = new Employees();
                String firstName = rs.getString("firstName");
                empResult.setFirstName(firstName);
                empResult.setLastName(rs.getString("lastName"));
                Date dt = rs.getDate("dateOfBirth");
                empResult.setDob( dt.toString());
                empResult.setEmail(rs.getString("email"));
                empResult.setPhone(rs.getString("phoneNumber"));
                empResult.setSalary(rs.getInt("salary"));
                empResult.setEmployeeId(rs.getInt("employeeId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empResult;
    }

    public Employees findEmployeeByFirstName(String firstName) {
        return null;
    }

    public List<Employees> findAllEmployees() {
        List<Employees> listE = new ArrayList<>();
        Employees empResult = null;

        String sql = "SELECT * FROM employees";
        try {
            pst = connection.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                empResult = new Employees();
                empResult.setFirstName( rs.getString("firstName"));
                empResult.setLastName(rs.getString("lastName"));
                Date dt = rs.getDate("dateOfBirth");
                empResult.setDob( dt.toString());
                empResult.setEmail(rs.getString("email"));
                empResult.setPhone(rs.getString("phoneNumber"));
                empResult.setSalary(rs.getInt("salary"));
                empResult.setEmployeeId(rs.getInt("employeeId"));
                listE.add(empResult);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listE;
    }
public Employees listEmployeesWithDepartments () {
        String  sql = " SELECT e.employeeId as emPOd,  e.firstName as fName, e.email as  email, d.name as name" +
                "FROM employees e" +
                "INNER join department d ON d.departmentId = d.departmentID";
        return null;

        }
}
