package io.github.rluu.flashcards.core.auth;

import io.dropwizard.auth.PrincipalImpl;
import io.github.rluu.flashcards.api.User;

public class SimplePrincipal extends PrincipalImpl {
    private User user;
    
    public SimplePrincipal(String name) {
	super(name);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
