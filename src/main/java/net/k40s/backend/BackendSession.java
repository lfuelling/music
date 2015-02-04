package net.k40s.backend;

import net.k40s.Storage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Created by k40s on 2/4/15.
 */
public class BackendSession extends AuthenticatedWebSession {
    public BackendSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return username.equals(Storage.getBackendUserName()) && password.equals(Storage.getBackendUserPass());
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}
