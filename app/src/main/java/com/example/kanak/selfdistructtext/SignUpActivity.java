package com.example.kanak.selfdistructtext;

import android.app.AlertDialog;
import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText)findViewById(R.id.UserNameField);
        mPassword = (EditText)findViewById(R.id.PasswordField);
        mEmail = (EditText)findViewById(R.id.EmailField);
        mSignupButton = (Button)findViewById(R.id.SignUp);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();

                if (username.isEmpty()|| password.isEmpty() || email.isEmpty()){

                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.Signup_Error_Message);
                    builder.setTitle(R.string.Signup_Error_Title);
                    builder.setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog =builder.create();
                    dialog.show();

                }

                else{
                    // create new user
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser newUser= new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);
                        if (e ==null){

                     // Success!
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle(R.string.Signup_Error_Title);
                            builder.setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog =builder.create();
                            dialog.show();

                        }

                        }

                        });
                    }
           }
        });


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
             setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
