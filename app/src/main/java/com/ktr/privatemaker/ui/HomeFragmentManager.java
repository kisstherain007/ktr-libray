package com.ktr.privatemaker.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;

import com.ktr.baseabstract.fragmentmanager.AppFragmentCacheManager;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.ui.found.FoundFragment;
import com.ktr.privatemaker.ui.fun.FunFragment;
import com.ktr.privatemaker.ui.home.HomeFragment;
import com.ktr.privatemaker.ui.my.MyFragment;
import com.ktr.privatemaker.ui.shop.ShopFragment;

/**
 * Created by kisstherain on 2015/7/12.
 */
public class HomeFragmentManager extends AppFragmentCacheManager {

    public static final int home_tag = 0;
    public static final int shop_tag = 2;
    public static final int my_tag = 1;
    public static final int found_tag = 3;
    public static final int fun_tag = 4;

    protected HomeFragmentManager(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public void initFragments(SparseArray<Fragment> fragments, FragmentActivity mActivity) {

        fragments.append(home_tag, getHomeFragment());
        fragments.append(shop_tag, getShopFragment());
        fragments.append(my_tag, getMyFragment());
        fragments.append(found_tag, getFoundFragment());
        fragments.append(fun_tag, getFunFragment());
    }

    public void changeFragmentByCache(int fragmentId) {
        super.changeFragmentByCache(fragmentId, R.id.fragment_container_layout);
    }

    private Fragment getHomeFragment(){

        return HomeFragment.newInstance();
    }

    private Fragment getMyFragment(){

        return MyFragment.newInstance();
    }

    private Fragment getFoundFragment(){

        return FoundFragment.newInstance();
    }

    private Fragment getShopFragment(){

        return ShopFragment.newInstance();
    }

    private Fragment getFunFragment(){

        return FunFragment.newInstance();
    }
}
