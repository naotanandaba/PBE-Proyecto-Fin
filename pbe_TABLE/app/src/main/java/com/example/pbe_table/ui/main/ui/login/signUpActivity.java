package com.example.pbe_table.ui.main.ui.login;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pbe_table.MainActivity;
import com.example.pbe_table.R;
import com.example.pbe_table.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class signUpActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private EditText usernameEditText, passwordEditText, password2EditText;
    private Button signUpButton;
    private String ip;
    public static final String EXTRA_MESSAGE_NAME = "NAME.MESSAGE";
    public static final String EXTRA_MESSAGE_IP = "IP.MESSAGE";
    public static final String EXTRA_MESSAGE_USER_ID = "ID.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        VolleySingleton.getInstance(this);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password1);
        password2EditText = findViewById(R.id.password2);
        signUpButton = findViewById(R.id.singUp);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        Intent intent= getIntent();
        ip= intent.getStringExtra(LoginActivity.EXTRA_MESSAGE_IP);




        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                signUpButton.setEnabled(loginFormState.isDataValid());

                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                if (loginResult.getError() != null) {
                    showSingUpFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult( Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });


    }



    public void Click(View view){
        enviarUsuario(ip);
    }

    public void enviarUsuario(String ip){
        String url=ip+"/php/registrophp.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                if(response.contains( "Success" )){
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), ip );
                }else{
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombreuser",usernameEditText.getText().toString());
                parametros.put("password1",passwordEditText.getText().toString());
                parametros.put("password2",password2EditText.getText().toString());
                return parametros;
            }
        };
        VolleySingleton.getInstance().requestQueue.add( stringRequest );
    }

    private void updateUiWithUser(LoggedInUserView model) {

        String ip = model.getIp();
        String name = model.getDisplayName();
        String id = model.getId();

        // TODO : initiate successful logged in experience
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE_USER_ID,id);
        intent.putExtra(EXTRA_MESSAGE_NAME,name);
        intent.putExtra(EXTRA_MESSAGE_IP,ip);
        startActivity(intent);

    }


    private void showSingUpFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}