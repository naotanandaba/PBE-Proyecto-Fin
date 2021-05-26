package com.example.pbe_table;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instance = null;

    public RequestQueue requestQueue;


    private VolleySingleton(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getInstance(Context context)
    {
        if (null == instance)
            instance = new VolleySingleton(context);
        return instance;
    }

    public static synchronized VolleySingleton getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(VolleySingleton.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }


    //public RequestQueue getRequestQueue() {
    //    return requestQueue;
    //}
}
