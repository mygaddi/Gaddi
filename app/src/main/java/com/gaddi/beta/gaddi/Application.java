package com.gaddi.beta.gaddi;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

/**
 * Created by Farhan on 18-10-2015.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "0ZbAVpsuwhS4u4pvdkmY7pxZHIwwxERm2IVnrApB", "asaGfLX45Kpv7kKUemqEEFkEuQ2H2K8mRMWPjYZk");
        ParseFacebookUtils.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}
