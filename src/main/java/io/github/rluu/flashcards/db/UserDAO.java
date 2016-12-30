package io.github.rluu.flashcards.db;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.github.rluu.flashcards.api.User;

public class UserDAO extends AbstractDAO<User> {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO(SessionFactory factory) {
	super(factory);
    }

    @Timed
    @UnitOfWork
    public User getUserById(Long userId) {
	return get(userId);
    }

    @Timed
    @UnitOfWork
    public User getUserByUserName(String username) {
	if (!StringUtils.isEmpty(username)) {
	    log.trace("Getting User by username: " + username);
	    Session session = currentSession();
	    User user = (User)session.createCriteria(User.class)
		     .add(Restrictions.eq("usr_nm", username))
		     .uniqueResult();
	    return user;
	}
	return null;
    }

    @Timed
    @UnitOfWork
    public User getUserByEmailAddress(String emailAddress) {
	if (!StringUtils.isEmpty(emailAddress)) {
	    log.trace("Getting User by email address: " + emailAddress);
	    Session session = currentSession();
	    User user = (User)session.createCriteria(User.class)
		     .add(Restrictions.eq("eml_addr", emailAddress))
		     .uniqueResult();
	    return user;
	}
	return null;
    }

    @Timed
    @UnitOfWork
    public User createUser(User user) {
	return persist(user);
    }

    @Timed
    @UnitOfWork
    public User saveOrUpdateUser(User user) {
	return persist(user);
    }

    @Timed
    @UnitOfWork
    public void deleteUser(User user) {
	if (user != null && user.getId() != null) {
	    Long userId = user.getId();
	    Session session = currentSession();
	    Query query;
	    int result;

	    query = session.createQuery("delete usr_flshcrd_lst where user_id = :userId");
	    query.setParameter("userId", userId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table usr_flshcrd_lst for userId: " + userId);
	   
	    query = session.createQuery("delete usr_role where user_id = :userId");
	    query.setParameter("userId", userId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table usr_role for userId: " + userId);
	   
	    query = session.createQuery("delete usr where user_id = :userId");
	    query.setParameter("userId", userId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table usr for userId: " + userId);
	}
    }
}
