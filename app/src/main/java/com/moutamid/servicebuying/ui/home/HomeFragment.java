package com.moutamid.servicebuying.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.moutamid.servicebuying.R;
import com.moutamid.servicebuying.SelectedCategoryScreen;
import com.moutamid.servicebuying.adapter.HomeCategoryListAdapter;
import com.moutamid.servicebuying.adapter.SlideViewPagerAdapter;
import com.moutamid.servicebuying.databinding.FragmentHomeBinding;
import com.moutamid.servicebuying.model.Category;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    SlideViewPagerAdapter adapter;
    public static ViewPager viewPager;
    private ArrayList<Category> categoryArrayList = new ArrayList<>();
    HomeCategoryListAdapter homeCategoryListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewPager = root.findViewById(R.id.viewpager);
        adapter=new SlideViewPagerAdapter(getActivity());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //loadCategories();

        binding.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Agriculture Service",
                        R.drawable.agriculture,"This service function will only deal with Agricultural related services like cultivation of Paddy, Wheat, Pulse, Millet ,Maize, Sugarcane etc");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        binding.item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Horticultural Service",
                        R.drawable.horticulture,"This service function will deal with cultivation of fruits, vegetables, medicinal, aromatic, and ornamental plants.");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        binding.item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Veterinary Service Function",
                        R.drawable.veterinary,"This service function will deal with the diseases, injuries, and treatment of farm and domestic animals.");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        binding.item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Animal Husbandry Service",
                        R.drawable.pets,"This service function will deal with livestock raising and selective breeding services");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        binding.item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Commercial Service",
                        R.drawable.market,"This service function will deal with services to commercial establishments in urban areas");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        binding.item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category("Domestic Service",R.drawable.domestic,
                        "This service function will deal with services offered to domestic urban households like Flats ,Villa, Independent houses  ");
                Intent intent = new Intent(getActivity(), SelectedCategoryScreen.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        return root;
    }

    private void loadCategories() {
        Category category1 = new Category("AGRICULTURAL SERVICE",
                R.drawable.agriculture,"This service function will only deal with Agricultural related services like cultivation of Paddy, Wheat, Pulse, Millet ,Maize, Sugarcane etc");
        categoryArrayList.add(category1);
        Category category2 = new Category("HORTICULTURAL SERVICE",
                R.drawable.horticulture,"This service function will deal with cultivation of fruits, vegetables, medicinal, aromatic, and ornamental plants.");
        categoryArrayList.add(category2);
        Category category3 = new Category("VETERINARY SERVICE FUNCTION",
                R.drawable.veterinary,"This service function will deal with the diseases, injuries, and treatment of farm and domestic animals.");
        categoryArrayList.add(category3);
        Category category4 = new Category("ANIMAL HUSBANDRY SERVICE",
                R.drawable.pets,"This service function will deal with livestock raising and selective breeding services");
        categoryArrayList.add(category4);
        Category category5 = new Category("COMMERCIAL SERVICE",
                R.drawable.market,"This service function will deal with services to commercial establishments in urban areas");
        categoryArrayList.add(category5);
        Category category6 = new Category("DOMESTIC SERVICE",R.drawable.domestic,"This service function will deal with services offered to domestic urban households like Flats ,Villa, Independent houses  ");
        categoryArrayList.add(category6);

       // binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //homeCategoryListAdapter = new HomeCategoryListAdapter(getActivity(),categoryArrayList);
        //binding.recyclerview.setAdapter(homeCategoryListAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}