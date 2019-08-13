package com.example.sonia.uvapp.slideshow_fototipos_data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.sonia.uvapp.R;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_fototipo);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mPager);
    }




    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }



        String getTitle(int indice ){  return getResources().getStringArray( R.array.fototipos_title) [ indice] ; }

        String getDescription(int indice){ return  getResources().getStringArray( R.array.fototipos_features) [ indice]; }

        String getSunEffects(int indice){ return getResources().getStringArray( R.array.fototipos_sun_effects) [indice] ; }

        String getMelaninStatus( int indice){ return getResources().getStringArray( R.array.fototipos_melanin) [indice]; }

        int imagen_fototipo( int index){
            int ar= 0;
            switch (index){
                case 0: ar= R.drawable.fototipo1;break;
                case 1: ar= R.drawable.fototipo2;break;
                case 2: ar= R.drawable.fototipo3;break;
                case 3: ar= R.drawable.fototipo4;break;
                case 4: ar= R.drawable.fototipo5;break;
                case 5: ar= R.drawable.fototipo6;break;
            }  return  ar;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf( position+1);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle arguments = new Bundle();
            arguments.putInt( "fototipo", imagen_fototipo( position ));
            arguments.putString("title", getTitle( position));
            arguments.putString("text1", getDescription( position));
            arguments.putString("text2", getSunEffects( position));
            arguments.putString("text3",getMelaninStatus( position));
            return ScreenSlidePageFragment.obtener_fragmento(  arguments);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
