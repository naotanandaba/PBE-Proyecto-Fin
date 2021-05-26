package com.example.pbe_table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pbe_table.ui.main.ui.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TableLayout tableLayout;
    private EditText textQuery;
    private String[] header;
    private ArrayList<String[]> rows;
    private TableDynamic tableDynamic;
    private TextView titleText;
    private TextView welcomeText;
    private String ip;
    private String username;
    private boolean tableExist=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rows = new ArrayList<>();
        tableLayout = (TableLayout) findViewById(R.id.table);
        textQuery = (EditText) findViewById(R.id.textQuery);
        titleText = (TextView) findViewById(R.id.textView);
        welcomeText= (TextView) findViewById(R.id.textUsername);
        titleText.setTextSize(30);
        titleText.setAllCaps( true );
        titleText.setTextColor( Color.RED );
        titleText.setText( "title" );
        Intent intent= getIntent();
        ip= intent.getStringExtra(LoginActivity.EXTRA_MESSAGE_IP);
        username= intent.getStringExtra(LoginActivity.EXTRA_MESSAGE_NAME);
        welcomeText.setText("Welcome: "+username);
        welcomeText.setTextSize( 20 );
        VolleySingleton.getInstance(this);
        tableDynamic= new TableDynamic(tableLayout, getApplicationContext());
        textQuery.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    httpGet(v);
                    return true;
                }
                return false;
            }
        } );
    }

    public void httpGet(View view){
        String url=ip;
        if(textQuery.getText().toString().contains("&"))
        {
            url += textQuery.getText().toString()+"&nombreuser="+username;
        }else{
            url += textQuery.getText().toString()+"?nombreuser="+username;
        }
        String title=textQuery.getText().toString().split("\\?")[0];
        System.out.println(url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray  response) {
                        JSONArray jsonArray = response;

                        if(tableExist) {tableDynamic.deleteAll(tableLayout);}
                        if(jsonArray.isNull( 0 )){
                            Toast.makeText( getApplicationContext(),"Query incorrecta", Toast.LENGTH_LONG ).show();
                        }

                        try {
                            header = new String[jsonArray.getJSONObject(0).names().length()];
                            for(int h=0; h<header.length; h++){
                                header[h]=jsonArray.getJSONObject(0).names().getString(h);
                            }
                            tableDynamic.addHeader(header);
                            String[][] item = new String[jsonArray.length()][header.length];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                for(int j=0; j < header.length; j++){
                                    String cell=jsonObject.getString(header[j]);
                                    item[i][j]=cell;
                                }
                                rows.add(item[i]);
                            }

                            titleText.setText(title);

                            tableDynamic.addData(rows);
                            tableDynamic.backgroundHeader(Color.CYAN);
                            tableDynamic.backgroundData(Color.YELLOW, Color.GREEN);
                            rows.clear();

                            tableExist=true;

                        } catch (Exception w) {
                            titleText.setText(w.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText( getApplicationContext(), error.toString(), Toast.LENGTH_LONG ).show();
                    }
                });

        VolleySingleton.getInstance().requestQueue.add(jsonArrayRequest);

    }
    public void logOut(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
        finish();
    }

}