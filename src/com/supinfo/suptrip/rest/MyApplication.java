package com.supinfo.suptrip.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("com.supinfo.suptrip.rest");
    }
}
