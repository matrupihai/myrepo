package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.Loan;
import com.weblib.hbm.model.User;

public class UserDAOImpl extends GenericDAOImpl<User, Integer> {
	
	public User findByName(String userName) {
		return findByString("user_name", userName);
	}
	
	public List<User> findAllUsers() {
		return findAll();
	}
	
	public User findUserById(Integer id) {
		return findById(id);
	}

	public List<Book> findBooksBorrowedByUser(Integer id) {
		User user = findUserById(id);
		for (Loan loan : user.getLoans()) {
			//TODO
		}
		
		return null;
	}
	
	
	
}
