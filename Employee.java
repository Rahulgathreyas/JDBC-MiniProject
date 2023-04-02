package jdbc_project;

import java.util.*;
public class Employee {
		private int employee_id;
		private String employee_name;
		private double employee_salary;
		private String employee_branch;
		private double employee_deductions;
		private String employee_regime;
		//Default Constructor
		public Employee() {
			
		}
		public int getEmployee_id() {
			return employee_id;
		}
		//Parameterised_Constructor
		public Employee(int employee_id, String employee_name, double employee_salary, String employee_branch) {
			super();
			this.employee_id = employee_id;
			this.employee_name = employee_name;
			this.employee_salary = employee_salary;
			this.employee_branch = employee_branch;
		}
		//Getters and Setters
		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}
		public String getEmployee_name() {
			return employee_name;
		}
		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}
		public double getEmployee_salary() {
			return employee_salary;
		}
		public void setEmployee_salary(double employee_salary) {
			this.employee_salary = employee_salary;
		}
		public String getEmployee_branch() {
			return employee_branch;
		}
		public void setEmployee_branch(String employee_branch) {
			this.employee_branch = employee_branch;
		}
		public double getEmployee_deductions() {
			return employee_deductions;
		}
		public void setEmployee_deductions(double employee_deductions) {
			this.employee_deductions = employee_deductions;
		}
		public String getEmployee_regime() {
			return employee_regime;
		}
		public void setEmployee_regime(String employee_regime) {
			this.employee_regime = employee_regime;
		}
		@Override
		public String toString() {
			return "Employee [employee_id=" + employee_id + ", employee_name=" + employee_name + ", employee_salary="
					+ employee_salary + ", employee_branch=" + employee_branch + ", employee_deductions="
					+ employee_deductions + ", employee_regime=" + employee_regime + "]";
		}
	
		
}
