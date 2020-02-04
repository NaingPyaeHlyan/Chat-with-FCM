package com.ethicaldigit.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.ethicaldigit.chat.Utils.App;
import com.ethicaldigit.chat.Utils.Helpers;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private Context context = App.context;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));


        // CallBack registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Helpers.handleFacebookAccessToken(loginResult.getAccessToken(), mAuth, loginButton);

            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
