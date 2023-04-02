package jdbc_project;

public interface EmployeeIntrf {
	
		//create an employee
		public void createEmployee(Employee emp);
		//show all employee
		
		public void showAllEmployee();
		//show employee based on id
		
		public void showEmployeeBasedOnID(int ID);
		//update the employee
		public void updateID(int id,String name);
		//delete employee
		public void deleteEmployee(int id);
		//tax regime
		public void taxRegime(int id);
}
