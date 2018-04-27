package com.example.gowthamkrishna.tourex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    Button _btn_login;
    EditText raw_email, raw_password;
    String email,password;
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        signupLink = (TextView) findViewById(R.id.display_signup);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPage();
            }
        });

        _btn_login = findViewById(R.id.btn_login);
        _btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raw_email = findViewById(R.id.input_email);
                raw_password = findViewById(R.id.input_password);
                email = raw_email.getText().toString().trim();
                password = raw_password.getText().toString().trim();
                login();
            }
        });

    }

    public void login(){
        if(validate()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, UserInput.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(MainActivity.this, "Invalid Input.",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate(){
        boolean valid=true;
        if(email.isEmpty()){
            raw_email.setError("Email cannot be empty");
            valid = false;
        }
        if(password.isEmpty()){
            raw_password.setError("Password cannot be empty");
            valid = false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            raw_email.setError("Invalid Format");
            valid=false;
        }
        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void signUpPage() {
        Intent intent = new Intent(this, SignUp.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
