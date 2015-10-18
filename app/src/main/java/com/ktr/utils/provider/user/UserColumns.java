package com.ktr.utils.provider.user;

import android.net.Uri;
import android.provider.BaseColumns;

import com.ktr.utils.provider.CommonProvider;

/**
 * Created by kisstherain on 2015/10/18.
 */
public class UserColumns implements BaseColumns {

    public static final String TABLE_NAME = "user";

    public static final Uri CONTENT_URI = Uri.parse(CommonProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    public static final String _ID = BaseColumns._ID;

    public static final String USER_NAME = "user_name";

    public static final String GENDER = "gender";

    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_NAME,
            GENDER
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_NAME) || c.contains("." + USER_NAME)) return true;
            if (c.equals(GENDER) || c.contains("." + GENDER)) return true;
        }
        return false;
    }
}
