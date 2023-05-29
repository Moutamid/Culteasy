package com.moutamid.servicebuying.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.servicebuying.R;
import com.moutamid.servicebuying.adapter.BookingListAdapter;
import com.moutamid.servicebuying.model.Request;
import com.moutamid.servicebuying.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;


public class BookingFragment extends Fragment {

    private com.moutamid.servicebuying.databinding.FragmentBookingBinding binding;
    private ArrayList<Request> requestArrayList;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = com.moutamid.servicebuying.databinding.FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerview);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}