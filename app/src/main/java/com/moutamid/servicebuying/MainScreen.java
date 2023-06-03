package com.moutamid.servicebuying;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.moutamid.servicebuying.Notifications.Token;
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

                    showLogoutDialog();
                }
            }
        });
    //    FirebaseUser firebaseUser = Constants.auth().getCurrentUser();
  //      FirebaseMessaging.getInstance().subscribeToTopic(firebaseUser.getUid());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
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
        });


    }

    private void showLogoutDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainScreen.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_account_layout, null);
        dialogBuilder.setView(dialogView);

        TextView yesBtn = (TextView) dialogView.findViewById(R.id.yes);
       // yesBtn.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(),R.drawable.shape_yellow,null));
        TextView noBtn = (TextView) dialogView.findViewById(R.id.no);
        //noBtn.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(),R.drawable.shape_red,null));
        AlertDialog alertDialog = dialogBuilder.create();
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.auth().signOut();
                Intent intent = new Intent(MainScreen.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void updatetoken(String token) {
        FirebaseUser firebaseUser = Constants.auth().getCurrentUser();

        DatabaseReference db = Constants.databaseReference().child("Tokens");
        Token uToken = new Token(token);
        db.child(firebaseUser.getUid()).setValue(uToken);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}