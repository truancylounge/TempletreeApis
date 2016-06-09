package com.templetree.application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Lalith on 10/1/15.
 */
public class TempletreeApplication extends ResourceConfig {

    public TempletreeApplication() {
        packages("com.templetree.resources");
        register(MultiPartFeature.class);
        //register(JacksonFeature.class);
    }

}
