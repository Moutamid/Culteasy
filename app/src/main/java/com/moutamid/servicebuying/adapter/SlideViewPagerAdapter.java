package com.moutamid.servicebuying.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.moutamid.servicebuying.R;

import java.util.Locale;

public class SlideViewPagerAdapter extends PagerAdapter {

    private Context ctx;


    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_slider_layout,container,false);
        ImageView logo1 =view.findViewById(R.id.image);

        switch (position)
        {
            case 0:
                logo1.setImageResource(R.drawable.banner1);
                break;

            case 1:
                logo1.setImageResource(R.drawable.banner2);
                break;
            case 2:
                logo1.setImageResource(R.drawable.banner3);
                break;
            case 3:
                logo1.setImageResource(R.drawable.banner4);
                break;
            case 4:
                logo1.setImageResource(R.drawable.banner5);
                break;
            case 5:
                logo1.setImageResource(R.drawable.banner6);
                break;
        }



        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
