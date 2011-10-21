package com.marker55.apps.kui.domain.model;

import com.marker55.apps.kui.util.ObjectUtils;

public class Configuration {

    private boolean authenticationRequired;

    public boolean isAuthenticationRequired() {
        return authenticationRequired;
    }

    public void setAuthenticationRequired( boolean authenticationRequired ) {
        this.authenticationRequired = authenticationRequired;
    }

    @Override
    public String toString() {
        return ObjectUtils.toString( this );
    }

}
