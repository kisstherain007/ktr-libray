package com.ktr.utils.provider.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.ktr.utils.provider.base.AbstractSelection;

/**
 * Created by kisstherain on 2015/10/18.
 */
public class UserSelection extends AbstractSelection<UserSelection> {

    @Override
    protected Uri baseUri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public UserCursor query(Context context) {
        return query(context, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PersonCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PersonCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public UserCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    public UserSelection id(long... value) {
        addEquals("person." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection idNot(long... value) {
        addNotEquals("person." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection orderById(boolean desc) {
        orderBy("person." + UserColumns._ID, desc);
        return this;
    }

    public UserSelection orderById() {
        return orderById(false);
    }

    public UserSelection mame(String... value) {
        addEquals(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection nameNot(String... value) {
        addNotEquals(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection nameLike(String... value) {
        addLike(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection nameContains(String... value) {
        addContains(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection nameStartsWith(String... value) {
        addStartsWith(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection nameEndsWith(String... value) {
        addEndsWith(UserColumns.USER_NAME, value);
        return this;
    }

    public UserSelection orderByName(boolean desc) {
        orderBy(UserColumns.USER_NAME, desc);
        return this;
    }

    public UserSelection orderByName() {
        orderBy(UserColumns.USER_NAME, false);
        return this;
    }

}
