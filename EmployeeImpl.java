package jdbc_project;

import java.sql.*;

import com.mysql.cj.*;

public class EmployeeImpl implements EmployeeIntrf{
	Connection con;
	@Override
	public void createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query="insert into employee_details values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setInt(1,emp.getEmployee_id());
			pstm.setString(2,emp.getEmployee_name());
			pstm.setDouble(3,emp.getEmployee_salary());
			pstm.setString(4,emp.getEmployee_branch());
			pstm.setDouble(5,emp.getEmployee_deductions());
			pstm.setDouble(6, 0);
			pstm.setDouble(7, 0);
			pstm.setString(8,emp.getEmployee_regime());			
			int cnt=pstm.executeUpdate();
			if(cnt!=0) {
				System.out.println("Employee inserted successfully");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showAllEmployee() {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query="select * from employee_details";
		try {
			 Statement stmt=con.createStatement();
			 ResultSet result=stmt.executeQuery(query);
			 if(!result.isBeforeFirst()) {
					System.out.println("No data fetched. The table is empty.");
				}
				else {
					System.out.println("--------------------------------------------------------------------------------------------------");
					System.out.println("ID\tNAME\tSALARY\t\tBRANCH\tDEDUCTIONS\tOLDTAX\t\tNEWTAX\t\tREGIME");
					System.out.println("--------------------------------------------------------------------------------------------------");
					while(result.next()) {
						System.out.format("%d\t%s\t%f\t%s\t%f\t%f\t%f\t%s\n",result.getInt(1),result.getString(2),result.getDouble(3),result.getString(4),result.getDouble(5),result.getDouble(6),result.getDouble(7),result.getString(8));
					}
					System.out.println("--------------------------------------------------------------------------------------------------");
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showEmployeeBasedOnID(int ID) {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query="select * from employee_details where employee_id="+ID;
		try {
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(query);
			if(!result.isBeforeFirst()) {
				System.out.println("No data fetched with ID= "+ID);
			}
			else {
				System.out.println("--------------------------------------------------------------------------------------------------");
				System.out.println("ID\tNAME\tSALARY\t\tBRANCH\tDEDUCTIONS\tOLDTAX\t\tNEWTAX\t\tREGIME");
				System.out.println("--------------------------------------------------------------------------------------------------");
				while(result.next()) {
					System.out.format("%d\t%s\t%f\t%s\t%f\t%f\t%f\t%s\n ",result.getInt(1),result.getString(2),result.getDouble(3),result.getString(4),result.getDouble(5),result.getDouble(6),result.getDouble(7),result.getString(8));
				}
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateID(int id, String name) {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query="update employee_details set employee_name=? where employee_id=? ";
		try {
			PreparedStatement psm=con.prepareStatement(query);
			psm.setString(1, name);
			psm.setInt(2, id);
			int n=psm.executeUpdate();
			if(n!=0)
				System.out.println("Employee details successfully updated");
			else
				System.out.println("Updation failed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query="delete from employee_details where employee_id=?";
		try {
			PreparedStatement psm=con.prepareStatement(query);
			psm.setInt(1, id);
			int n=psm.executeUpdate();
			if(n!=0) 
				System.out.println("Employee details successfully deleted");
			else
				System.out.println("Deleteion failed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void taxRegime(int id) {
		// TODO Auto-generated method stub
		con=DBConnection.createDBConnection();
		String query3="select * from employee_details where employee_id="+id;
		try {
			Statement stm=con.createStatement();
			ResultSet result=stm.executeQuery(query3);
			if(!result.isBeforeFirst()) {
				System.out.println("No data fetched with Employee ID= "+id);
			}
			else {
				result.next();
				double salary=result.getDouble(3);
				double deduction=result.getDouble(5);
				String regime=result.getString(8);
				regime=regime.toLowerCase();
				salary=salary*12;
				double taxable_salary;
				double old_tax=0,new_tax=0;
				String updated_regime;
				taxable_salary=salary-deduction;
				//calculation of tax according to old tax regime
				if(taxable_salary >=250000 && taxable_salary < 500000)
					old_tax+= ((5*taxable_salary)/100);
				else if(taxable_salary > 500000 && taxable_salary <= 750000)
					old_tax= old_tax + (5*250000/100) + (20*(taxable_salary-500000)/100);
				else if(taxable_salary > 750000 && taxable_salary <= 1000000)
					old_tax= old_tax + (5*250000/100) + (20*(250000)/100) + (20*(taxable_salary-750000)/100);
				else if(taxable_salary > 1000000 && taxable_salary <= 1250000)
					old_tax= old_tax + (5*250000/100) + (20*(250000)/100) + (20*250000/100) + (30*(taxable_salary-1000000)/100);
				else if(taxable_salary > 1250000 && taxable_salary <= 1500000)
					old_tax= old_tax + (5*250000/100) + (20*(250000)/100) + (20*250000/100) + (30*250000/100) + (30*(taxable_salary-1250000)/100);
				else
					old_tax= old_tax + (5*250000/100) + (20*(250000)/100) + (20*250000/100) + (30*250000/100) + (30*250000/100) + (30*(taxable_salary-1500000)/100);
				//calculation of tax according to new tax regime
				if(salary <= 750000)
					new_tax=0;
				else if(salary > 750000 && salary <= 1000000)
					new_tax= new_tax + (5*250000/100) + (10*250000/100) + (15*(salary-750000)/100);
				else if(salary > 1000000 && salary <= 1250000)
					new_tax= new_tax + (5*250000/100) + (10*250000/100) + (15*250000/100) + (20*(salary-1000000)/100);
				else if( salary > 1250000 && salary <= 1500000 )
					new_tax= new_tax + (5*250000/100) + (10*250000/100) + (15*250000/100) + (20*250000/100) + (25*(salary-1250000)/100);
				else if(salary > 150000)
					new_tax= new_tax + (5*250000/100) + (10*250000/100) + (15*250000/100) + (20*250000/100) + (25*250000/100) + (30*(salary-1500000)/100);
				
				//updating old and new tax in the DB
				String query1="update employee_details set employee_oldtax=?,employee_newtax=? where employee_id=? ";
				try {
					PreparedStatement psm=con.prepareStatement(query1);
					psm.setDouble(1, old_tax);
					psm.setDouble(2, new_tax);
					psm.setInt(3, id);
					int nom=psm.executeUpdate();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				//comparing the old tax and new tax amount
				if(old_tax == new_tax) {
					System.out.println("Tax paid in both the regimes are same. But please consider going to new tax regime");
					updated_regime="new";
					String query2="update employee_details set employee_regime=? where employee_id=? ";
					try {
						PreparedStatement psm=con.prepareStatement(query2);
						psm.setString(1, updated_regime);
						psm.setInt(2, id);
						int no=psm.executeUpdate();
						if(no!=0)
							System.out.println("Employee regime successfully updated to "+updated_regime+" tax regime");
						else
							System.out.println("Updation failed");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else if(old_tax > new_tax) {
					System.out.println("By going to new tax regime you will be able to save tax amount by Rs."+(old_tax-new_tax));
					updated_regime="new";
					if(regime==updated_regime) {
						System.out.println("You are already in the preffered regime that is new tax regime.");
					}
					else {
						System.out.println("Please consider going to new tax regime.");
						String query2="update employee_details set employee_regime=? where employee_id=? ";
						try {
							PreparedStatement psm=con.prepareStatement(query2);
							psm.setString(1, updated_regime);
							psm.setInt(2, id);
							int num=psm.executeUpdate();
							if(num!=0)
								System.out.println("Employee regime successfully updated to "+updated_regime+" tax regime");
							else
								System.out.println("Updation failed");
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					System.out.println("By staying in old tax regime you will be able to save tax amount by Rs."+(new_tax-old_tax));
					updated_regime="old";
					if(regime==updated_regime) {
						System.out.println("You are already in the preffered regime that is old tax regime.");
					}
					else {
						System.out.println("Please consider going to old tax regime.");
						String query2="update employee_details set employee_regime=? where employee_id=? ";
						try {
							PreparedStatement psm=con.prepareStatement(query2);
							psm.setString(1, updated_regime);
							psm.setInt(2, id);
							int nu=psm.executeUpdate();
							if(nu!=0)
								System.out.println("Employee regime successfully updated to "+updated_regime+" tax regime");
							else
								System.out.println("Updation failed");
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
					
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}