package com.moutamid.servicebuying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.servicebuying.Notifications.APIService;
import com.moutamid.servicebuying.Notifications.Client;
import com.moutamid.servicebuying.Notifications.Data;
import com.moutamid.servicebuying.Notifications.MyResponse;
import com.moutamid.servicebuying.Notifications.Sender;
import com.moutamid.servicebuying.Notifications.Token;
import com.moutamid.servicebuying.databinding.ActivityServiceBookingBinding;
import com.moutamid.servicebuying.model.Users;
import com.moutamid.servicebuying.utils.Constants;

import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceBooking extends AppCompatActivity {

    private ActivityServiceBookingBinding binding;
    String name = "";
    private String id = "";
    String phone,location,date, time = "";
    ProgressDialog pd;
    private String username = "";
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        binding.name.setText(name);

        apiService = Client.getRetrofit("https://fcm.googleapis.com/").create(APIService.class);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceBooking.this,MainScreen.class));
                finish();
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validInfo()) {
                    pd = new ProgressDialog(ServiceBooking.this);
                    pd.setMessage("Sending your request....");
                    pd.show();
                    saveDetails();
                }
            }
        });
        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(ServiceBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int mon= monthOfYear+1;
                        date = dayOfMonth+"/"+mon+"/"+year;
                        binding.date.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        binding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ServiceBooking.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                time = hourOfDay + ":" + minute;
                                binding.time.setText(time);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });
        getUserDetails();
    }

    private void getUserDetails() {
        FirebaseUser firebaseUser = Constants.auth().getCurrentUser();
        DatabaseReference db = Constants.databaseReference().child("Users").child(firebaseUser.getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Users users = snapshot.getValue(Users.class);
                    username = users.getFname() + " " + users.getLname();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void saveDetails() {
        DatabaseReference db = Constants.databaseReference().child("Requests");
        FirebaseUser user =  Constants.auth().getCurrentUser();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("location",location);
        hashMap.put("date",date);
        hashMap.put("time",time);
        db.child(id).updateChildren(hashMap);
        sendNotification("admin","New Request","You have a new request from " + location +" for "+ name +" service on "+ date+"/"+time);
        pd.dismiss();
        Intent intent = new Intent(ServiceBooking.this, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

  /*  private void sendNotification(String topic, String title, String desp) {
        new FcmNotificationsSender(
                "/topics/" + topic,                title,
                desp,                getApplicationContext(),
                ServiceBooking.this)                .SendNotifications();
    }*/


    private void sendNotification(String uId, String title, String content) {
        DatabaseReference tokens = Constants.databaseReference().child("Tokens");
        FirebaseUser user = Constants.auth().getCurrentUser();
        Query query = tokens.orderByKey().equalTo(uId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Token token = snapshot.getValue(Token.class);

                    Data data = new Data(user.getUid(), R.mipmap.ic_launcher, content,
                            title,uId);

                    Sender sender = new Sender(data, token.getToken());

                    apiService.sendNotification(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    if(response.code() == 200){
                                        if (response.body().success != 1){
                                            System.out.println("Failed to send notification!");
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {

                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public boolean validInfo() {

        phone = binding.phone.getText().toString();
        location = binding.location.getText().toString();
        date = binding.date.getText().toString();
        time = binding.time.getText().toString();

        if (phone.isEmpty()) {
            binding.phone.setError("Input Phone!");
            binding.phone.requestFocus();
            return false;
        }
        if (location.isEmpty()) {
            binding.location.setError("Input Location!");
            binding.location.requestFocus();
            return false;
        }

        if (date.isEmpty()) {
            binding.date.setError("Input Date!");
            binding.date.requestFocus();
            return false;
        }

        if (time.isEmpty()) {
            binding.time.setError("Input Time!");
            binding.time.requestFocus();
            return false;
        }

        return true;
    }
}