package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<DisplayFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        fragments = new ArrayList<>();

        fragments.add(DisplayFragment.newInstance(R.drawable.pluto));
        fragments.add(DisplayFragment.newInstance(R.drawable.neptune));
        fragments.add(DisplayFragment.newInstance(R.drawable.saturn));

        // ViewPager can be given one of two adapter types
        // 1. Use FragmentStatePagerAdapter for your BookCase app
            // Is built for a dynamic collection, able to adapt if collection of fragments on which is based should grow or shrink as app is executing
        // 2. FragmentPagerAdapter if you use your app won't work
            // Won't adapt for more fragments or less, it works with a fixed collection size
        viewPager = findViewById(R.id.viewPager);

        final MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments); // gets reference to adapter

        // Give ViewPager an adapter
        viewPager.setAdapter(myFragmentAdapter);

        findViewById(R.id.newPlanetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something would happen before this to make collection grow, whenever you add or remove an element Collection is using, need to tell Adapter explicitly that number of elements has changed
                fragments.add(DisplayFragment.newInstance(R.drawable.mars)); // 1. modify collection in some way
                myFragmentAdapter.notifyDataSetChanged(); // 2. notify adapter data set changed DONE
            }
        });
    }

    class MyFragmentAdapter extends FragmentStatePagerAdapter {
        ArrayList<DisplayFragment> fragments; // hold reference to pagers fragments

        public MyFragmentAdapter(FragmentManager fm, ArrayList<DisplayFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
