package com.example.pbe_table.ui.main.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pbe_table.MainActivity;
import com.example.pbe_table.VolleySingleton;
import com.example.pbe_table.databinding.ActivityLoginBinding;
import com.example.pbe_table.R;
import com.example.pbe_table.ui.main.*;
import com.example.pbe_table.ui.main.ui.login.LoginViewModel;
import com.example.pbe_table.ui.main.ui.login.LoginViewModelFactory;
import com.example.pbe_table.ui.main.ui.login.*;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    public static final String EXTRA_MESSAGE_NAME = "NAME.MESSAGE";
    public static final String EXTRA_MESSAGE_IP = "IP.MESSAGE";
    public static final String EXTRA_MESSAGE_USER_ID = "ID.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        VolleySingleton.getInstance(this);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final EditText ipEditText = binding.textip;
        final Button loginButton = binding.login;
        final Button signUpButton = binding.buttonRegister;
        final ProgressBar loadingProgressBar = binding.loading;
        ipEditText.setText("http://192.168.56.1/");

        signUpButton.setEnabled( ipEditText.getText().toString()!= null );

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                signUpButton.setEnabled( (loginFormState.getIpError() == null ));
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
                if (loginFormState.getIpError() != null){
                    ipEditText.setError( getString( loginFormState.getIpError() ) );
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),ipEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        ipEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),ipEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                String url=ipEditText.getText().toString()+"php/indexphp.php?nameuser="
                        + usernameEditText.getText().toString() +"&contrasenya="
                        + passwordEditText.getText().toString();
                System.out.println(url);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET,
                    url,null, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray  response) {

                            try {

                                String userN=response.getJSONObject( 0 ).getString( "name" ).toLowerCase();
                                if (userN.equals(usernameEditText.getText().toString().toLowerCase())){
                                    loginViewModel.login(usernameEditText.getText().toString(),
                                            passwordEditText.getText().toString(),ipEditText.getText().toString());
                                }else{
                                    loadingProgressBar.setVisibility( View.INVISIBLE );
                                    Toast.makeText( getApplicationContext(), "username or pass incorrect" , Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            loadingProgressBar.setVisibility( View.INVISIBLE );
                            Toast.makeText( getApplicationContext(), "username or pass incorrect" , Toast.LENGTH_LONG).show();
                        }
                    });

            VolleySingleton.getInstance().requestQueue.add(jsonArrayRequest);

            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpActivityStart( ipEditText.getText().toString() );
                setResult( Activity.RESULT_OK );
                finish();
            }
        });
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

    private void signUpActivityStart(String ip){
        Intent intent = new Intent(this, signUpActivity.class);
        intent.putExtra(EXTRA_MESSAGE_IP,ip);
        startActivity(intent);

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

}