package com.moutamid.servicebuying;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.moutamid.servicebuying.adapter.CustomizedExpandableListAdapter;
import com.moutamid.servicebuying.databinding.ActivitySelectedCategoryScreenBinding;
import com.moutamid.servicebuying.model.Category;
import com.moutamid.servicebuying.model.Request;
import com.moutamid.servicebuying.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectedCategoryScreen extends AppCompatActivity {

    private ActivitySelectedCategoryScreenBinding binding;
    private Category category;
    CustomizedExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectedCategoryScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        expandableListView = findViewById(R.id.expandaleList);
        category = getIntent().getParcelableExtra("category");
        binding.name.setText(category.getName());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      startActivity(new Intent(SelectedCategoryScreen.this, MainScreen.class));
                finish();
            }
        });
        if (category.getName().equals("Agriculture Service")) {
            expandableDetailList = loadData1();
        }else if (category.getName().equals("Horticultural Service")) {
            expandableDetailList = loadData2();
        }else if (category.getName().equals("Veterinary Service Function")) {
            expandableDetailList = loadData3();
        }else if (category.getName().equals("Animal Husbandry Service")) {
            expandableDetailList = loadData4();
        }else if (category.getName().equals("Commercial Service")) {
            expandableDetailList = loadData5();
        }else if (category.getName().equals("Domestic Service")) {
            expandableDetailList = loadData6();
        }
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        expandableListAdapter = new CustomizedExpandableListAdapter(this, expandableTitleList, expandableDetailList);
        expandableListView.setAdapter(expandableListAdapter);

        for(int i=0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);
        // This method is called when the group is expanded
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
               // Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the group is collapsed
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the child in any group is clicked
        // via a toast method, it is shown to display the selected child item as a sample
        // we may need to add further steps according to the requirements
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String child = expandableDetailList.get(
                        expandableTitleList.get(groupPosition)).get(
                        childPosition);
                Intent intent = new Intent(SelectedCategoryScreen.this, SubSelectedCategoryScreen.class);
                intent.putExtra("category",category);
                intent.putExtra("name",child);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    private HashMap<String, List<String>> loadData1() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("Ploughing");
        list1.add("Levelling");
        list1.add("Manuring");

        List<String> list2 = new ArrayList<String>();
        list2.add("Broadcast Seeding");
        list2.add("Transplanting");

        List<String> list3 = new ArrayList<String>();
        list3.add("Weed Management");
        list3.add("Fertilizer Application");
        list3.add("Pestisides Application");
        list3.add("Plant Protection");
        list3.add("Crop Care");
        list3.add("Irrigation");

        List<String> list4 = new ArrayList<String>();
        list4.add("Reaping");
        list4.add("Hauling");
        list4.add("Stacking");
        list4.add("Threshing");
        list4.add("Winnowing");

        List<String> list5 = new ArrayList<String>();
        list5.add("Industry Grade Cleaning");
        list5.add("Drying");
        list5.add("Packaging And Storage");
        list5.add("Transporting");

        List<String> list6 = new ArrayList<String>();
        list6.add("Farm Land Lease");
        list6.add("Farm Insurance");
        list6.add("Soil Testing");
        list6.add("Crop Selection");
        list6.add("Farming Education");
        list6.add("Consult With Agro Expert");

        detailList.put("Soil Preparation Service", list1);
        detailList.put("Sowing Service", list2);
        detailList.put("Growing Service", list3);
        detailList.put("Harvesting Service",list4);
        detailList.put("Post Harvesting Service",list5);
        detailList.put("Other Services",list6);
        return detailList;
    }
    private HashMap<String, List<String>> loadData2() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("Soil Preparation");
        list1.add("Sowing");
        list1.add("Growing");
        list1.add("Harvesting");
        list1.add("Post Harvesting");

        List<String> list2 = new ArrayList<String>();
        list2.add("Soil Preparation");
        list2.add("Sowing");
        list2.add("Growing");
        list2.add("Harvesting");
        list2.add("Post Harvesting");

        List<String> list3 = new ArrayList<String>();
        list3.add("Soil Preparation");
        list3.add("Sowing");
        list3.add("Growing");
        list3.add("Harvesting");
        list3.add("Post Harvesting");


        List<String> list4 = new ArrayList<String>();
        list4.add("Soil Preparation");
        list4.add("Sowing");
        list4.add("Growing");
        list4.add("Harvesting");
        list4.add("Post Harvesting");

        List<String> list5 = new ArrayList<String>();
        list5.add("Soil Preparation");
        list5.add("Sowing");
        list5.add("Growing");
        list5.add("Harvesting");
        list5.add("Post Harvesting");

        detailList.put("Fruits Cultivation Services", list1);
        detailList.put("Vegetable Cultivation Services", list2);
        detailList.put("Flower Cultivation Services", list3);
        detailList.put("Spices Crop Culture ",list4);
        detailList.put("Medical And Aromatic Plants Culture",list5);

        return detailList;
    }
    private HashMap<String, List<String>> loadData3() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("Treatment of Animals");
        list1.add("Biological Samples Examination");
        list1.add("Immunisation of Animals");
        list1.add("Castration ");
        list1.add("Training of Farmers");
        list1.add("Artificial Insemination in Cattle and Buffalos");
        list1.add("Pregnancy Diagnosis ");
        list1.add("Deworming of Animals");
        list1.add("Organisation of Animal Health Camps");
        list1.add("Mobile Veterinary Services");
        list1.add("Issuance of Animal Health Certificates");
        list1.add("Disaster Management Activities");
        list1.add("Fodder Cultivation Activities ");
        list1.add("Nutritional Management of Animals");
        list1.add("Insurance of Animals");


        detailList.put("Veterinary Services", list1);


        return detailList;
    }
    private HashMap<String, List<String>> loadData4() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("Dairy Farming");
        list1.add("Poultry Farming");
        list1.add("Fish Farming");
        list1.add("Bee Farming");

        detailList.put("Animal Husbandry Services", list1);
        return detailList;
    }
    private HashMap<String, List<String>> loadData5() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("Landscaping of CORPORATE, GOVT OFFICES AND ESTABLISHMENTS");
        list1.add("Landscaping of shopping mall and Apartments ");
        list1.add("Landscaping of Private and Public Parks");
        list1.add("Landscaping of Hotel and Resorts ");
        list1.add("Maintaining Corporate Office Lawn");
        list1.add("Maintaining of Govt Office Lawn");
        list1.add("Maintaining of shopping mall");
        list1.add("Maintaining of Apartment Lawn and  other establishment lawn");
        list1.add("Maintaining of Private and Public Parks");
        list1.add("Maintaining of Golf Court");
        list1.add("Maintaining of Stadium ground");
        list1.add("Maintaining of Playground");
        list1.add("Maintaining of Hotel and Resort Lawn");
        list1.add("Landscaping of any commercial space ");
        list1.add("Interior scaping");
        list1.add("Turf management");

        detailList.put("Commercial Services", list1);
        return detailList;
    }
    private HashMap<String, List<String>> loadData6() {
        HashMap<String, List<String>> detailList = new HashMap<String, List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("House and Villa  GARDENING (Ground and Roof Top )");
        list1.add("House and Villa Flower plantation (Ground and Roof Top)");
        list1.add("House and Villa  Fruit ,Vegetable and other farming");
        list1.add("Apartment balcony Gardening (Grass and Flower)");
        list1.add("Apartment balcony Fruit ,Vegetable farming");
        list1.add("Interior scaping");
        list1.add("Any other gardening or farming service assistance");

        detailList.put("Domestic Services", list1);
        return detailList;
    }
}