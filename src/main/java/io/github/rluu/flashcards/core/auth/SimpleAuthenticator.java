package io.github.rluu.flashcards.core.auth;

import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.github.rluu.flashcards.api.User;
import io.github.rluu.flashcards.db.UserDAO;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, SimplePrincipal> {
    private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticator.class);

    private final UserDAO userDao;
    
    public SimpleAuthenticator(UserDAO userDao) {
	this.userDao = userDao;
    }

    @Override
    public Optional<SimplePrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
	log.debug("Attempting to authenticate a user with these credentials: " + ToStringBuilder.reflectionToString(credentials));

	User user = userDao.getUserByUserName(credentials.getUsername());
	if (user != null) {
	    // Found a user with this username.  Check the password.
	    if (user.getPassword() != null && user.getPassword().equals(credentials.getPassword())) {
		log.debug("User with username " + credentials.getUsername() + " was successfully authenticated.");
		return Optional.of(new SimplePrincipal(user.getUsername(), user));
	    }
	    else {
		log.info("User with submitted username " + credentials.getUsername() + 
			  " and submitted password " + credentials.getPassword() + 
			  " did not match the password in the database: " + user.getPassword());
		return Optional.empty();
	    }
	}
	else {
	    log.info("Could not authenticate because we could not find a User with username " + credentials.getUsername());
            return Optional.empty();
	}
    }
}
