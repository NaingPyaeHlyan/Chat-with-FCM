package com.ethicaldigit.chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.ethicaldigit.chat.Adapter.RecyclerMessageAdapter;
import com.ethicaldigit.chat.Model.MessageModel;
import com.ethicaldigit.chat.Utils.App;
import com.ethicaldigit.chat.Utils.Helpers;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnSend;
    private ImageView btnAddImgage;
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private RecyclerMessageAdapter adapter;
    private List<MessageModel> messageModelList;



    private void initUI(){
        etInput = findViewById(R.id.etInputMessage);
        btnSend = findViewById(R.id.btnSend);
        btnAddImgage = findViewById(R.id.btnAddImage);
        recyclerView = findViewById(R.id.messageRecyclerView);
        manager = new LinearLayoutManager(App.context);

        recyclerView.setLayoutManager(manager);
        messageModelList = new ArrayList<>();
        adapter = new RecyclerMessageAdapter(App.context, messageModelList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        btnAddImgage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        });

        btnSend.setOnClickListener(v -> {
            String message = etInput.getText().toString();
            Helpers.pushMessageToFirebase(message, FirebaseDatabase.getInstance().getReference());
            etInput.setText("");
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
