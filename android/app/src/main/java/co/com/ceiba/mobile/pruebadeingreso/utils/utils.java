package co.com.ceiba.mobile.pruebadeingreso.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class utils {

    public final static String ID_USER = "id";
    public static View inflate(Context context, int viewId, ViewGroup parent, boolean attachToRoot){
        return LayoutInflater.from(context).inflate(viewId,parent,attachToRoot);
    }
}
