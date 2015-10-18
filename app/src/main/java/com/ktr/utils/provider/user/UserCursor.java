package com.ktr.utils.provider.user;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.ktr.utils.provider.base.AbstractCursor;

/**
 * Created by kisstherain on 2015/10/18.
 */
public class UserCursor extends AbstractCursor implements UserModel{

    public UserCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(UserColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * First name of this person. For instance, John.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(UserColumns.USER_NAME);
        if (res == null)
            throw new NullPointerException("The value of 'first_name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code gender} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public Gender getGender() {
        Integer intValue = getIntegerOrNull(UserColumns.GENDER);
        if (intValue == null)
            throw new NullPointerException("The value of 'gender' in the database was null, which is not allowed according to the model definition");
        return Gender.values()[intValue];
    }
}
