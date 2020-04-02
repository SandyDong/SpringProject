package com.dong.service.common;

import com.dong.entity.ApplicationEmail;

public interface Mailer {
    public void sendMailByAsynchronousMode(final ApplicationEmail email);
}
