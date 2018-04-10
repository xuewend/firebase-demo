package com.skylight.gcmdemo.pcm;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class TokenRefreshService extends FirebaseInstanceIdService{
    private static final String TAG = "FirebaseIns";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "token:" + token);

        /**
         * If you want to send messages to this application or
         * manage this apps subscriptions on the server side, send the
         * Instance ID token to your app server
         */
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token){

    }
}
