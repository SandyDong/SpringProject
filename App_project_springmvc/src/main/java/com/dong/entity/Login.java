package com.dong.entity;

import lombok.Data;


@Data
public class Login {

    private String name;
    private String  date;
    private String  remoteIp;
    private int  remotePort;
    private String  contentType;
    private String  serverName;
    private int  serverPort;
    private String  requestUrl;
}
