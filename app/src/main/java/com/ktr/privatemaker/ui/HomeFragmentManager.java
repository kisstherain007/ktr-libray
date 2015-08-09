package com.ktr.privatemaker.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;

import com.ktr.baseabstract.fragmentmanager.AppFragmentCacheManager;
import com.ktr.privatemaker.ui.home.HomeFragment;

/**
 * Created by kisstherain on 2015/7/12.
 */
public class HomeFragmentManager extends AppFragmentCacheManager {

    public static final int home_tag = 0;

    protected HomeFragmentManager(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public void initFragments(SparseArray<Fragment> fragments, FragmentActivity mActivity) {

        fragments.append(home_tag, getHomeFragment());
    }

    private Fragment getHomeFragment(){

        return HomeFragment.newInstance();
    }
}
