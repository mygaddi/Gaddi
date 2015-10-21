package com.gaddi.beta.gaddi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ParseUser user;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = new ParseUser();
        final TextInputLayout loginEmail = (TextInputLayout) findViewById(R.id.login_emailWrapper);
        final TextInputLayout loginPassword = (TextInputLayout) findViewById(R.id.login_passwordWrapper);
        final EditText emaiText = (EditText) findViewById(R.id.login_email);
        final EditText passwordText = (EditText) findViewById(R.id.login_password);
        TextView registerLink = (TextView) findViewById(R.id.register);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        loginEmail.setErrorEnabled(true);
        loginPassword.setErrorEnabled(true);




        Button loginButton = (Button) findViewById(R.id.login_button);
        Button fblogin = (Button) findViewById(R.id.fb_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emaiText.getText().toString();
                password = passwordText.getText().toString();


                Toast.makeText(getApplicationContext(),email+"",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),password+"",Toast.LENGTH_LONG).show();
                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e)
                        {
                            if (user != null) {
                                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(mainActivity);
                                finish();
                            } else {
                                String errorMessage = e.getMessage();
                                View view = findViewById(R.id.loginlinear);
                                Snackbar
                                        .make(view, errorMessage, Snackbar.LENGTH_LONG)
                                        .show();
                            }
                        }

                });
            }
        });
        fblogin.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:

                break;
            case R.id.fb_login_button:
               List permissions = new ArrayList();
                permissions.add("public_profile");
                permissions.add("email");
                //permissions.add("public_profile");

              ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                           Log.e("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            //Log.e("MyApp", "User signed up and logged in through Facebook!");
                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);
                            finish();
                        } else {
                            //Log.e("MyApp", "User logged in through Facebook!");
                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);
                            finish();
                        }
                    }
                });
        }
    }
}
