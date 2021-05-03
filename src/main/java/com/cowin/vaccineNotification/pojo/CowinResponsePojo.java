package com.cowin.vaccineNotification.pojo;

import java.util.List;

public class CowinResponsePojo{
    public List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}