package com.ethicaldigit.chat.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Const {
    public static String CURRENT_TIME = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    public static String DISPLAY_USER_ID    = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static String DISPLAY_IMG_URL    = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
    public static String DISPLAY_NAME       = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    public static String DISPLAY_EMAIL      = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    public static String DISPLEY_LOADING    = "https://www.google.com/images/spin-32.gif";
    public static String MESSAGE_CHILD      = "message";


    public static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    public static StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();

}
