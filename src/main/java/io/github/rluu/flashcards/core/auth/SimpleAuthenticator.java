package io.github.rluu.flashcards.core.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, SimplePrincipal> {
//    private final UserDAO userDao;
//    
//    public SimpleAuthenticator(UserDAO userDao) {
//	this.userDao = userDao;
//    }

    @Override
    public Optional<SimplePrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
	// TODO_rluu: use database to check the credentials and return the SimplePrincipal.
//        if ("secret".equals(credentials.getPassword())) {
//            return Optional.of(new User(credentials.getUsername()));
//        }
        return Optional.empty();
    }
}
