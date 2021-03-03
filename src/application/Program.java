package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sd = DaoFactory.createSellerDao();
		System.out.println("====TEST 1: findById====");
		Seller sl = sd.findById(3);
		System.out.println(sl);
		List<Seller> sellers = sd.findByDepartment(2);
		System.out.println("====TEST 2: findByDepartment====");
		for(Seller s : sellers) {
			System.out.println(s + "/n");
		}
	}
}
