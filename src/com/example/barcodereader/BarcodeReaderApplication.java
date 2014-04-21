package com.example.barcodereader;


import org.json.JSONException;
import org.json.JSONObject;

import com.example.barcodereader.R;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class BarcodeReaderApplication extends Application {
    private final static String TAG = BarcodeReaderApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();

        /*
         * In this tutorial, we'll subclass ParseObject for convenience to
         * create and modify Meal objects
         */
        //ParseObject.registerSubclass(CartItem.class);

        /*
         * Fill in this section with your Parse credentials
         */
        Parse.initialize(this, "p5987h8D68oVGaaODQvrQfJxZvMKUj7rjiEAHsQf", "mTzcm86SoIlrXnOxZMzweAQljw3l3bslx6ZrAvTX");
        
        /*
         * This app lets an anonymous user create and save photos of meals
         * they've eaten. An anonymous user is a user that can be created
         * without a username and password but still has all of the same
         * capabilities as any other ParseUser.
         * 
         * After logging out, an anonymous user is abandoned, and its data is no
         * longer accessible. In your own app, you can convert anonymous users
         * to regular users so that data persists.
         * 
         * Learn more about the ParseUser class:
         * https://www.parse.com/docs/android_guide#users
         */
        ParseUser.enableAutomaticUser();

        /*
         * For more information on app security and Parse ACL:
         * https://www.parse.com/docs/android_guide#security-recommendations
         */
        ParseACL defaultACL = new ParseACL();

        /*
         * If you would like all objects to be private by default, remove this
         * line
         */
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}
