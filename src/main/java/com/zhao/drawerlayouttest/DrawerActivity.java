package com.zhao.drawerlayouttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    private String[] mTitles;
    private DrawerLayout mDrawer;
    private ListView mMenuListView;
    private CharSequence mTitle;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        Log.i("zcx","action bar:"+actionBar);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_drawer);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mMenuListView = (ListView) findViewById(R.id.iv_menu);
        mTitles = getResources().getStringArray(R.array.menu_list);
        Log.i("zcx", "tiles is" + mTitles.length);
        mMenuListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTitles));
        mMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrawerActivity.this,position+","+mTitles[position], Toast.LENGTH_LONG).show();
                selectItem(position);
            }
        });

    }

    private class DrawItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        PlantFragment fragment = PlantFragment.newInstance(mTitles[position], String.valueOf(position));

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
        mMenuListView.setItemChecked(position, true);
        setTitle(mTitles[position]);
        mDrawer.closeDrawer(mMenuListView);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if(actionBar!=null){
            actionBar.setTitle(title);
        }else{
            Log.i("zcx","get action null");
        }
    }
}
