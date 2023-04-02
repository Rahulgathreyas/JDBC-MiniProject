package jdbc_project;

import java.util.*;
public class Main {
	public static void main(String[] args) {
		EmployeeIntrf dao=new EmployeeImpl();		
		Scanner sc=new Scanner(System.in);
		User_validation u=new User_validation();
		int flag=0;
		System.out.println("Enter the username");
		String username=sc.next();
		flag=u.check_username(username);
		if(flag==1) {
			System.out.println("Enter password");
			String password=sc.next();
			flag=u.check_password(password);
		}
		if(flag==1) {
			System.out.println("Welcome "+username);
			System.out.println("Welcome to Employee Managnement Application\n");
			do {
				System.out.println("\nEnter 1.Add an employee\t2.Show all employees\t3.Show Employee based on ID\n4.Update an employee\t5.Delete an employee\t6:Tax regime\t7:Exit");
				System.out.println("Enter the choice");
				int choice=sc.nextInt();
				switch(choice) {
					case 1: Employee emp=new Employee();
							System.out.println("Enter ID:");
							int id=sc.nextInt();
							System.out.println("Enter Name:");
							String name=sc.next();
							System.out.println("Enter Salary:");
							double salary=sc.nextDouble();
							System.out.println("Enter Branch:");
							String branch=sc.next();
							System.out.println("Enter Deductions:");
							double deductions=sc.nextDouble();
							System.out.println("Enter Regime:");
							String regime=sc.next();
							emp.setEmployee_id(id);
							emp.setEmployee_name(name);
							emp.setEmployee_salary(salary);
							emp.setEmployee_branch(branch);
							emp.setEmployee_deductions(deductions);
							emp.setEmployee_regime(regime);
							dao.createEmployee(emp);
							break;
					case 2: dao.showAllEmployee();
							break;
					case 3: System.out.println("Enter the id of the employee");
							int ID=sc.nextInt();
							dao.showEmployeeBasedOnID(ID);
							break;
					case 4: System.out.println("Enter the id to update the name of the employee:");
							int I=sc.nextInt();
							System.out.println("Enter the new name to update:");
							String N=sc.next();
							dao.updateID(I, N);
							break;
					case 5: System.out.println("Enter the id to delete");
							int i=sc.nextInt();
							dao.deleteEmployee(i);
							break;
					case 6: System.out.println("Enter the ID of the Employee whose tax is to be evaluated");
					        id=sc.nextInt();
					        dao.taxRegime(id);
					        break;
					case 7: System.out.println("Thank you for using the application");
							sc.close();
							System.exit(0);
					default:System.out.println("Enter valid choice");
				}
			}while(true);
		}		
	}
}