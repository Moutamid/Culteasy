package com.moutamid.servicebuying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.moutamid.servicebuying.databinding.ActivityWelcomeScreenBinding;
import com.moutamid.servicebuying.utils.Constants;

public class WelcomeScreen extends AppCompatActivity {

    private ActivityWelcomeScreenBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = Constants.auth().getCurrentUser();
        if (user != null){
            Intent intent = new Intent(WelcomeScreen.this, MainScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}