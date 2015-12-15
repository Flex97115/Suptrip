package com.supinfo.suptrip.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.User;
import com.supinfo.suptrip.exception.ItemAlreadyExistException;
import com.supinfo.suptrip.exception.UnknownItemException;

public class JpaUserDao implements UserDao {

	private EntityManager em;
	
	public JpaUserDao (EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(em.find(User.class, user.getIdBooster()) != null) {
				throw new ItemAlreadyExistException(user);
			}
			em.persist(user);
			t.commit();
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.merge(user);
			t.commit();
		} catch (IllegalArgumentException e) {
			throw new UnknownItemException(User.class, user.getIdBooster());
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.remove(user);
			t.commit();
		} catch (IllegalArgumentException e) {
			throw new UnknownItemException(User.class, user.getIdBooster());
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}

	}

	@Override
	public User findById(int id) {
		EntityTransaction t = em.getTransaction();
		try {
			User result = em.find(User.class, id);
			if ( result == null ){
				throw new UnknownItemException(User.class, id);
			}
			return result;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> myUsers = new ArrayList<User>();
		try {
			myUsers.addAll(em.createQuery("SELECT u FROM User AS u").getResultList());		
		} finally {
			em.close();
		}
		return myUsers;
	}
	@Override
	public User findByAuth(int idBooster, String password) {
		User user = new User();
		try {
			user = (User) em.createQuery("SELECT u FROM User AS u WHERE u.idBooster="+idBooster+" AND u.password='"+password+"'").getSingleResult();
			if ( user == null ){
				throw new UnknownItemException(User.class, idBooster);
			}
		} catch(NoResultException e){
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return user;
	}

}
