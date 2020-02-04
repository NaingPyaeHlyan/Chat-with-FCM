package com.ethicaldigit.chat.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.ethicaldigit.chat.Model.MessageModel;
import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Helpers {
    private static Context context = App.context;


    public static void setGlideImage(Context context, String url, ImageView view){
        Glide.with(context)
                .load(url)
                .into(view);
    }

    public static void handleFacebookAccessToken(AccessToken token, FirebaseAuth auth, LoginButton loginButton){

        loginButton.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            loginButton.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public static void pushMessageToFirebase(String message, DatabaseReference mDatabaseReference){
        String CURRENT_TIME = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        MessageModel tempMessage = new MessageModel(message, Const.DISPLAY_NAME, Const.DISPLAY_IMG_URL, null /* no image */, CURRENT_TIME);
        mDatabaseReference.child(Const.MESSAGE_CHILD).child(CURRENT_TIME).setValue(tempMessage);

      //    Log.d("@@@", tempMessage.getName() + "\t" + tempMessage.getText() + "\t" + tempMessage.getPhotoUrl() + "\t" + tempMessage.getImageUrl() + tempMessage.getTime());
    }


    public static void readMessageFromFirebase(DatabaseReference databaseReference){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("@@", value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
