package com.marker55.apps.kui.domain.model;

import java.util.Date;

public class UserSession {

    private boolean authenticated;
    private String username;
    private Date authenticatedTime;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated( boolean authenticated ) {
        this.authenticated = authenticated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public Date getAuthenticatedTime() {
        return authenticatedTime;
    }

    public void setAuthenticatedTime( Date authenticatedTime ) {
        this.authenticatedTime = authenticatedTime;
    }

}
