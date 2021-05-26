package com.example.pbe_table.ui.main.data;

import android.graphics.Color;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pbe_table.VolleySingleton;
import com.example.pbe_table.ui.main.data.model.LoggedInUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private boolean log=false;
    private String userN,userID;
    public Result<LoggedInUser> login(String username, String password, String ip) {


        try {

            // TODO: handle loggedInUser authentication
            /*String url=ip+"php/indexphp.php?nameuser="+username +"&contrasenya=" +password;
            StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String  response) {

                                if (!response.isEmpty()){
                                    log=true;
                                } else {
                                    log=false;
                                }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            System.err.println(error.networkResponse);
                        }
                    });

            VolleySingleton.getInstance().requestQueue.add(jsonArrayRequest);*/

                LoggedInUser User = new LoggedInUser(
                        java.util.UUID.randomUUID().toString(),
                        username,
                        ip );
                return new Result.Success<>( User );

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}