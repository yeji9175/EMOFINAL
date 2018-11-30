package com.trois.project.emo.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomAdapter extends FragmentStatePagerAdapter {

    public CustomAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                OneFragment one = new OneFragment();
                return one;
            case 1:
                TwoFragment two = new TwoFragment();
                return two;
            case 2:
                ThreeFragment three = new ThreeFragment();
                return three;

                default :
                return new TwoFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
