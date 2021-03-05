package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

 	 System.out.println("==== TESTE 1: FindAll ====");
	 
	 DepartmentDao dep = DaoFactory.createDepartmentDao();
	 System.out.println(dep.findAll());
	 
	 System.out.println("\n==== TESTE 2: Insert ===="); 
	 dep.insert(new Department(null, "Games"));
	 
	 System.out.println("\n==== TESTE 3: FindById ===="); 
	 System.out.println(dep.findById(171));
	 
	 System.out.println("\n==== TESTE 4: Update ===="); 
	 dep.update(new Department(171, "Hardware"));
	 System.out.println(dep.findById(171));
	 
	 System.out.println("\n==== TESTE 5: DeleteById ===="); 
	 dep.deleteById(171);
	 System.out.println(dep.findById(171));
	 
	}
}
