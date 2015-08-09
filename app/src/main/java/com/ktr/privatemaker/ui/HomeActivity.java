package com.ktr.privatemaker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractActivity;
import com.ktr.ui.DrawerLeftMenuFragment;


public class HomeActivity extends AbstractActivity implements DrawerLeftMenuFragment.OnLeftMenuItemClickListener{

    DrawerLayout drawerLayout;
    private ShareActionProvider mShareActionProvider;
    HomeFragmentManager homeFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initVew();
    }

    private void initVew() {

        homeFragmentManager = new HomeFragmentManager(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayou);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                drawerLayout.setTag(null);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                if(drawerLayout.getTag() == null) return;

                String title = drawerLayout.getTag().toString();

                switch (title){
                    case "首页":
                        homeFragmentManager.changeFragmentByCache(HomeFragmentManager.home_tag, R.id.fragment_container_layout);
                        break;
                    case "店铺":
                        break;
                    case "发现":
                        break;
                    case "我的":
                        break;
                }

            }
        };
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeDrawerLayout();
            }
        });

        homeFragmentManager.changeFragmentByCache(HomeFragmentManager.home_tag, R.id.fragment_container_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu
                .findItem(R.id.action_share));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        mShareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onMenuItemClick(String title) {

        drawerLayout.setTag(title);

        closeDrawerLayout();
    }

    private void closeDrawerLayout(){

        if (drawerLayout.isDrawerOpen(Gravity.LEFT))
            drawerLayout.closeDrawer(Gravity.LEFT);
        else
            drawerLayout.openDrawer(Gravity.LEFT);
    }
}
