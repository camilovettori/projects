package main;

import model.Employees;
import persistence.RepositoryEmployees;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
   Employees emp1 = new Employees();
        emp1.setFirstName("Indrek");
        emp1.setLastName("Surname");
        emp1.setDob("1992-02-01");
        emp1.setPhone("6669999");
        emp1.setEmail("indrek@gmail.com");
        emp1.setSalary(5000);
//        emp1.setEmployeeId(7);


        RepositoryEmployees repo = new RepositoryEmployees();
        // repo.saveEmployee(emp1);
        //repo.updateEmployee(emp1);
        //repo.deleteEmployee(emp1);
//        Employees resultE = repo.findEmployeeById(4);
//        System.out.println(resultE.toString());
        //List<Employees> result = repo.findAllEmployees();
        //for (Employees emp: result) {
            System.out.println(emp1.toString());
        }
    }

