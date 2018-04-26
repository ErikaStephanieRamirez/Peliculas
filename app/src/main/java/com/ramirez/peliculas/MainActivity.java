package com.ramirez.peliculas;


import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Pelicula> peliculasArrayList;

    private ArrayList<Pelicula> peliculasArrayList2;
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;
    RecyclerViewAdapter_cardview adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FillPelis();
        tabLayout = findViewById(R.id.TabLayoutID);
        viewPager = findViewById(R.id.view_pagerID);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(TabFragment.newInstance(peliculasArrayList, true), "Peliculas");
        viewPagerAdapter.addFragment(new Fragment(), "Favoritos");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        adapter = new RecyclerViewAdapter_cardview(peliculasArrayList) {
            @Override
            public void onFavClick(CompoundButton buttonView, boolean isChecked) {

            }
        };
    }

    private void FillPelis(){
        peliculasArrayList = new ArrayList<>();
        peliculasArrayList.add(new Pelicula("Coco", R.drawable.coco, false ));
        peliculasArrayList.add(new Pelicula("Hotel Transilvania", R.drawable.hotel, false ));
        peliculasArrayList.add(new Pelicula("Kung Fu Panda", R.drawable.kfp, false));
    }
}