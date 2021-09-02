package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    private Context context = null;

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
