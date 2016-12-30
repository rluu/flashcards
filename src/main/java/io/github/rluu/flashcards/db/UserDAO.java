package io.github.rluu.flashcards.db;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.hibernate.AbstractDAO;
import io.github.rluu.flashcards.api.User;

public class UserDAO extends AbstractDAO<User> {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO(SessionFactory factory) {
	super(factory);
    }

    public User getUserByUserId(Long userId) {
	return get(userId);
    }

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

    public User createUser(User user) {
	return persist(user);
    }

    public User saveOrUpdateUser(User user) {
	return persist(user);
    }

    public void deleteUser(User user) {
	if (user != null && user.getId() != null) {
	    Session session = currentSession();
	    Query query = session.createQuery("delete usr_flshcrd_lst, usr_role, usr where usr_id=:userId");
	    query.setParameter("userId", user.getId());
	    int result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows for User: " + user);
	}
    }
}
