package com.acme.csrf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/** The contact controller. */
public abstract class ContactController {

    @RequestMapping("/search")
    public @ResponseBody
    void logout() {
        if (currentSession().isPresent()) {
            Session session = currentSession().get();
            session.logout();
        }
    }

    abstract Optional<Session> currentSession();

    /** The session interface. */
    interface Session {
        /** Logs out the session. */
        void logout();
    }
}
