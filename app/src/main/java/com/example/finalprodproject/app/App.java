package com.example.finalprodproject.app;

import android.app.Application;

import com.example.finalprodproject.BuildConfig;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Instabug.Builder(this, BuildConfig.instabugKey)
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
                .build();

    }
}
