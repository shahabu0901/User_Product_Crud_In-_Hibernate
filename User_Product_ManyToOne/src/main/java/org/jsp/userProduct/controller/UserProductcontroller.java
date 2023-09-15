package org.jsp.userProduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userProduct.dao.ProductDao;
import org.jsp.userProduct.dao.UserDao;
import org.jsp.userProduct.dto.Product;
import org.jsp.userProduct.dto.User;

public class UserProductcontroller {
	static UserDao uDao = new UserDao();
	static ProductDao pDao = new ProductDao();
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1. Register the User");
		System.out.println("2. Verify the User by Phone and Password");
		System.out.println("3. Verify the User by Email and Password");
		System.out.println("4. Update the User");
		System.out.println("5. Add the Product");
		System.out.println("6. View the Product by User Id");
		System.out.println("7. View the Product by User Phone and Password");
		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			regUser();
			break;
		}
		case 2: {
			verifyUserbyPhone();
			break;
		}
		case 3: {
			verifyUserbyEmail();
			break;
		}
		case 4: {
			updtUser();
			break;
		}
		case 5: {
			addProd();
			break;
		}
		case 6: {
			viewprodbyuid();
			break;
		}
		case 7: {
			viewprodbyUphn();
			break;
		}

		}
	}

	public static void regUser() {
		System.out.println("Enter User name, Phone, Email and Password");
		String name = s.next();
		long phone = s.nextLong();
		String email = s.next();
		String password = s.next();

		User u = new User();
		u.setName(name);
		u.setPhone(phone);
		u.setEmail(email);
		u.setPassword(password);

		u = uDao.registerUser(u);
		System.out.println("User Saved with Id: " + u.getId());
	}

	public static void verifyUserbyPhone() {
		System.out.println("Enter user Phone and Password");
		long phone = s.nextLong();
		String password = s.next();
		User u = uDao.verifyuser(phone, password);
		if (u != null) {
			System.out.println("user name" + u.getName());
			System.out.println("user id" + u.getId());
			System.out.println("user phone" + u.getPhone());
			System.out.println("user email" + u.getEmail());

		} else {
			System.out.println("invalid Phone and Password");
		}
	}

	public static void verifyUserbyEmail() {
		System.out.println("Enter User Email and password");
		String email = s.next();
		String password = s.next();
		User u = uDao.verifyuser(email, password);
		if (u != null) {
			System.out.println("User Name" + u.getName());
			System.out.println("User Id" + u.getId());
			System.out.println("User Phone" + u.getPhone());
			System.out.println("User Email" + u.getEmail());

		} else {
			System.out.println("invalid Phone and Password");
		}
	}

	public static void updtUser() {
		System.out.println("enter id to update");
		int id = s.nextInt();
		System.out.println("enter user name, phone, email and password");

		String name = s.next();
		long phone = s.nextLong();
		String email = s.next();
		String password = s.next();

		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setPhone(phone);
		u.setEmail(email);
		u.setPassword(password);

		u = uDao.updateUser(u);
		System.out.println("user saved with id: " + u.getId());
	}

	public static void addProd() {
		System.out.println("Enter user id to add product");
		int u_id = s.nextInt();
		System.out.println("EFnter product name, description, brand, category and price");
		String name = s.next();
		String description = s.next();
		String brand = s.next();
		String category = s.next();
		double price = s.nextDouble();

		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setBrand(brand);
		p.setCategory(category);
		p.setPrice(price);
		p = pDao.addproduct(p, u_id);
		System.out.println("Product Added");
	}

	public static void viewprodbyuid() {
		System.out.println("Entre User id");
		int u_id = s.nextInt();
		List<Product> prods = pDao.viewProduct(u_id);
		if (prods.size() > 0) {
			for (Product p : prods) {
				System.out.println("Product Id " + p.getId());
				System.out.println("Product Name " + p.getName());
				System.out.println("Product Desc " + p.getDescription());
			}
		} else {
			System.out.println("Invalid User Id");
		}

	}

	public static void viewprodbyUphn() {
		System.out.println("Entre User Phone and Password");
		long u_phone = s.nextLong();
		String u_password = s.next();

		List<Product> prods = pDao.viewProduct(u_phone, u_password);
		if (prods.size() > 0) {
			for (Product p : prods) {
				System.out.println("Product Id " + p.getId());
				System.out.println("Product Name " + p.getName());
				System.out.println("Product Descr " + p.getDescription());
			}
		} else {
			System.out.println("Invalid User Id");
		}
	}

}
