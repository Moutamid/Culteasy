package com.moutamid.servicebuying;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.moutamid.servicebuying.databinding.ActivityMainScreenBinding;
import com.moutamid.servicebuying.ui.booking.BookingFragment;
import com.moutamid.servicebuying.ui.home.HomeFragment;
import com.moutamid.servicebuying.utils.Constants;

public class MainScreen extends AppCompatActivity {

    private ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        binding.bottomMenu.setItemSelected(R.id.home,true);
        binding.bottomMenu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i == R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                }
                if(i == R.id.booking){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new BookingFragment()).commit();
                }
                if(i == R.id.logout){
                    Constants.auth().signOut();
                    Intent intent = new Intent(MainScreen.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
        FirebaseUser firebaseUser = Constants.auth().getCurrentUser();
        FirebaseMessaging.getInstance().subscribeToTopic("admin");
        /*FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    System.out.println("Fetching FCM registration token failed ");
                    return;
                }

                String token = task.getResult();
                updatetoken(token);
                // Toast.makeText(DashBoard.this,token,Toast.LENGTH_LONG).show();
            }
        });*/

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}