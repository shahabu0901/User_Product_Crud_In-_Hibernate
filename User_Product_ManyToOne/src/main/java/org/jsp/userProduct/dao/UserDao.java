package org.jsp.userProduct.dao;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityTransaction;
	import javax.persistence.NoResultException;
	import javax.persistence.Persistence;
	import javax.persistence.Query;

	import org.jsp.userProduct.dto.User;

	public class UserDao {
		EntityManager manager=Persistence.createEntityManagerFactory("md").createEntityManager();
	    public User registerUser(User u) {
	    	EntityTransaction transaction= manager.getTransaction();
	    	manager.persist(u);
	    	transaction.begin();
	    	transaction.commit();
	    	return u;
	    	
	    }
	    public User updateUser(User u) {
	    	EntityTransaction transaction= manager.getTransaction();
	    	manager.merge(u);
	    	transaction.begin();
	    	transaction.commit();
	    	return u;
	    	
	    }
	    
	    public User verifyuser(long phone , String password) {
	    	String qry= "select u from User u where u.phone=?1 and password=?2";
	    	Query q= manager.createQuery(qry);
	    	q.setParameter(1, phone);
	    	q.setParameter(2, password);
	    	try {
	    		return (User) q.getSingleResult();
	    	}catch(NoResultException e) {
	    		return null;
	    	}
	    }
	    public User verifyuser(String email , String password) {
	    	String qry= "select u from User u where u.email=?1 and password=?2";
	    	Query q= manager.createQuery(qry);
	    	q.setParameter(1, email);
	    	q.setParameter(2, password);
	    	try {
	    		return (User) q.getSingleResult();
	    	}catch(NoResultException e) {
	    		return null;
	    	}
	    }

	}


