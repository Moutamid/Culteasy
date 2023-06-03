package com.moutamid.servicebuying;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.moutamid.servicebuying.databinding.ActivitySubSelectedCategoryScreenBinding;
import com.moutamid.servicebuying.model.Category;
import com.moutamid.servicebuying.model.Request;
import com.moutamid.servicebuying.model.SubCategory;
import com.moutamid.servicebuying.utils.Constants;

public class SubSelectedCategoryScreen extends AppCompatActivity {

    private ActivitySubSelectedCategoryScreenBinding binding;
    String purpose = "";
    ProgressDialog pd;
    private Category category;
    private SubCategory subCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubSelectedCategoryScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        category = getIntent().getParcelableExtra("category");
        subCategory = getIntent().getParcelableExtra("subCategory");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubSelectedCategoryScreen.this, SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
                finish();
            }
        });

        binding.title.setText(category.getName());
        binding.name.setText(subCategory.getName());
        binding.image.setImageResource(category.getImage());
        binding.desp.setText(subCategory.getDescription());
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = binding.message.getText().toString();
                if (!TextUtils.isEmpty(purpose)) {
                    pd = new ProgressDialog(SubSelectedCategoryScreen.this);
                    pd.setMessage("Saving your details...");
                    pd.show();
                    saveDetails();
                } else {
                    binding.message.setError("Please Enter description!");
                }
            }
        });
    }

    private void saveDetails() {
        DatabaseReference db = Constants.databaseReference().child("Requests");
        FirebaseUser user = Constants.auth().getCurrentUser();
        String id = db.push().getKey();
        Request model = new Request(id, category.getName(), subCategory.getName(),"", user.getUid(), "", "", "", "Pending", purpose);
        db.child(id).setValue(model);
        pd.dismiss();
        Intent intent = new Intent(SubSelectedCategoryScreen.this, ServiceBooking.class);
        intent.putExtra("id", id);
        intent.putExtra("title", subCategory.getName());
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}