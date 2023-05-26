package com.moutamid.servicebuying.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.servicebuying.EditProfile;
import com.moutamid.servicebuying.R;
import com.moutamid.servicebuying.model.Users;
import com.moutamid.servicebuying.utils.Constants;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {

    private com.moutamid.servicebuying.databinding.FragmentProfileBinding binding;
    private DatabaseReference db;
    private FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = com.moutamid.servicebuying.databinding.FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = Constants.auth().getCurrentUser();
        db = Constants.databaseReference().child("Users");
        binding.account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.auth().signOut();
            }
        });

        getProfileDetails();


        return root;
    }

    private void getProfileDetails() {

        db.child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Users model = snapshot.getValue(Users.class);
                            binding.name.setText(model.getFname() + " " + model.getLname());
                            if (model.getImageUrl().equals("")){

                                Picasso.with(getActivity())
                                        .load(R.drawable.user)
                                        .into(binding.imageView);
                            }else {
                                Picasso.with(getActivity())
                                        .load(model.getImageUrl())
                                        .into(binding.imageView);
                            }
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