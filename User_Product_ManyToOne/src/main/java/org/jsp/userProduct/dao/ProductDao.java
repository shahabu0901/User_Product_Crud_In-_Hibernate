package org.jsp.userProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userProduct.dto.Product;
import org.jsp.userProduct.dto.User;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("md").createEntityManager();

	public Product addproduct(Product p, int u_id) {
		User u = manager.find(User.class, u_id);
		if (u != null) {
			p.setUsers(u);
			u.getProds().add(p);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(p);
			transaction.begin();
			transaction.commit();
			return p;
		}
		return null;
	}

	public Product updateproduct(Product p, int u_id) {
		User u = manager.find(User.class, u_id);
		if (u != null) {
			p.setUsers(u);
			u.getProds().add(p);
			EntityTransaction transaction = manager.getTransaction();
			manager.merge(p);
			transaction.begin();
			transaction.commit();
			return p;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> viewProduct(int u_id) {
		String qry = "select u.prods from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, u_id);
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Product> viewProduct(long u_phone, String u_password) {
		String qry = "select u.prods from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, u_phone);
		q.setParameter(2, u_password);
		return q.getResultList();

	}

}
