package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		SellerDao sd = DaoFactory.createSellerDao();
		System.out.println("==== TEST 1: findById ====");
		Seller sl = sd.findById(3);
		System.out.println(sl);

		List<Seller> sellers = sd.findByDepartment(2);
		System.out.println("\n==== TEST 2: findByDepartment ====");
		for (Seller s : sellers) {
			System.out.println(s);
		}

		List<Seller> sellersAll = sd.findAll();
		System.out.println("\n==== TEST 3: findAll ====");
		for (Seller s : sellersAll) {
			System.out.println(s + "/n");
		}

		System.out.println("\n==== TEST 4: Insert ====");

		Seller sl2 = new Seller(null, "Jos� Bezerra", "jose@gmail.com", new Date(sdf.parse("03/03/1985").getTime()),
				4000.0, new Department(2, null));
		sd.insert(sl2);

		System.out.println("\n==== TEST 5: Update ====");

		Seller sl3 = new Seller(10, "Romero Bezerra", "romero@gmail.com", new Date(sdf.parse("05/07/1985").getTime()), 4000.0, new Department(3, null));
		sd.update(sl3);
		System.out.println(sd.findById(10));

		System.out.println("\n==== TEST 6: Delete ====");

		sd.deleteById(11);
		if (sd.findById(11) == null) {
			System.out.println("Deleted record!");
		} else {
			System.out.println(sd.findById(11));
		}
	}
}
