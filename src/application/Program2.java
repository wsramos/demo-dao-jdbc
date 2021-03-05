package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

 	 System.out.println("==== TESTE 1: findAll ====");
	 
	 DepartmentDao dep = DaoFactory.createDepartmentDao();
	 System.out.println(dep.findAll());
	 
	 System.out.println("==== TESTE 2: Insert ===="); dep.insert(new Department(null, "Games"));
	 

	}

}
