package io.github.rluu.flashcards.db;

import io.github.rluu.flashcards.api.User;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
	super(factory);
    }

    public User getUserByUserId(Long userId) {
	// TODO_rluu: implement this.
	return null;
    }

    public User getUserByUserName(String username) {
	// TODO_rluu: implement this.
	return null;
    }

    public User getUserByEmailAddress(String emailAddress) {
	// TODO_rluu: implement this.
	return null;
    }

    public User createUser(User user) {
	// TODO_rluu: implement this.
	return null;
    }

    public User updateUser(User user) {
	// TODO_rluu: implement this.
	return null;
    }

    public User deleteUser(User user) {
	// TODO_rluu: implement this.
	return null;
    }
}
