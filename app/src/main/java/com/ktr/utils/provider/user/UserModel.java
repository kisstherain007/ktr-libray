package com.ktr.utils.provider.user;

import android.support.annotation.NonNull;

import com.ktr.utils.provider.base.BaseModel;

/**
 * Created by kisstherain on 2015/10/18.
 */
public interface UserModel extends BaseModel {

    /**
     * First name of this person. For instance, John.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Get the {@code gender} value.
     * Cannot be {@code null}.
     */
    @NonNull
    Gender getGender();

}
