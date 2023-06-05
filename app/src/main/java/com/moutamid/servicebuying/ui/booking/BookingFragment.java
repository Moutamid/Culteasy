package com.moutamid.servicebuying.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.servicebuying.MainScreen;
import com.moutamid.servicebuying.Notifications.APIService;
import com.moutamid.servicebuying.Notifications.Client;
import com.moutamid.servicebuying.Notifications.Data;
import com.moutamid.servicebuying.Notifications.MyResponse;
import com.moutamid.servicebuying.Notifications.Sender;
import com.moutamid.servicebuying.Notifications.Token;
import com.moutamid.servicebuying.R;
import com.moutamid.servicebuying.adapter.BookingListAdapter;
import com.moutamid.servicebuying.model.Request;
import com.moutamid.servicebuying.model.Users;
import com.moutamid.servicebuying.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookingFragment extends Fragment {

    private com.moutamid.servicebuying.databinding.FragmentBookingBinding binding;
    private ArrayList<Request> requestArrayList;
    private RecyclerView recyclerView;
    private APIService apiService;
    String serverKey;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = com.moutamid.servicebuying.databinding.FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerview);
        serverKey = Stash.getString("serverId");

        apiService = Client.getRetrofit("https://fcm.googleapis.com/").create(APIService.class);
        requestArrayList = new ArrayList<>();
        getBookingData();

        return root;
    }

    private void getBookingData() {
        DatabaseReference db = Constants.databaseReference().child("Requests");
        FirebaseUser user =  Constants.auth().getCurrentUser();
        Query query = db.orderByChild("userId").equalTo(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    requestArrayList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        Request model = ds.getValue(Request.class);
                        requestArrayList.add(model);
                    }
                    Collections.reverse(requestArrayList);
                    BookingListAdapter adapter = new BookingListAdapter(getActivity(),requestArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Cancel Service Request")){
            Request request = requestArrayList.get(item.getItemId());
            DatabaseReference db = Constants.databaseReference().child("Requests");
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("status","Declined");
            db.child(request.getId()).updateChildren(hashMap);
            DatabaseReference userDB = Constants.databaseReference().child("Users");
            userDB.child(request.getUserId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        Users model = snapshot.getValue(Users.class);
                        sendNotification("admin","Service Request Canceled!",model.getFname()+" "+model.getLname() + " has canceled his service request.");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return true;
    }



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

                    Map<String,String> headers = new HashMap<>();
                    headers.put("Content-Type","application/json");
                    headers.put("Authorization","Bearer "+serverKey);

                    apiService.sendNotification(headers,sender)
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}