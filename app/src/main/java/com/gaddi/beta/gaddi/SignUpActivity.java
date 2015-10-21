package com.gaddi.beta.gaddi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    ParseUser parseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        parseUser = new ParseUser();
        final TextInputLayout fnameWrapper = (TextInputLayout) findViewById(R.id.signup_fnameWrapper);
        final TextInputLayout lnameWrapper = (TextInputLayout) findViewById(R.id.signup_lnameWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.signup_emailWrapper);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.signup_usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.signup_passwordWrapper);

        fnameWrapper.setErrorEnabled(true);
        lnameWrapper.setErrorEnabled(true);
        emailWrapper.setErrorEnabled(true);
        usernameWrapper.setErrorEnabled(true);
        passwordWrapper.setErrorEnabled(true);

        Button registerButton = (Button) findViewById(R.id.register_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname = fnameWrapper.getEditText().getText().toString();
                final String lname = lnameWrapper.getEditText().getText().toString();
                final String email = emailWrapper.getEditText().getText().toString();
                final String username = usernameWrapper.getEditText().getText().toString();
                final String password = passwordWrapper.getEditText().getText().toString();
                if(!fname.equals(""))
                        parseUser.put("fname", fnameWrapper.getEditText().getText().toString());
                else
                    fnameWrapper.setError("Enter First Name");

                if(!lname.equals(""))
                    parseUser.put("lname", lnameWrapper.getEditText().getText().toString());
                else
                    lnameWrapper.setError("Enter Last Name");

                if(!email.equals(""))
                    parseUser.setEmail(emailWrapper.getEditText().getText().toString());
                else
                    emailWrapper.setError("Enter Email");

                if(!username.equals(""))
                    parseUser.setUsername(usernameWrapper.getEditText().getText().toString());
                else
                    usernameWrapper.setError("Enter Username");
                if(!password.equals(""))
                    parseUser.setPassword(passwordWrapper.getEditText().getText().toString());
                else
                    passwordWrapper.setError("Enter password");

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(mainActivity);
                            finish();
                        }
                        else{
                            String errorMessage = e.getMessage().toString();
                            View view = findViewById(R.id.linear);
                            Snackbar
                                    .make(view, errorMessage, Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }
                });


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
}
