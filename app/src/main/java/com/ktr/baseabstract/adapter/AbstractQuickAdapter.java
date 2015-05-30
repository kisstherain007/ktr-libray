package com.ktr.baseabstract.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import static com.ktr.baseabstract.adapter.BaseAdapterHelper.get;

/**
 * 
 * @author zhoubo 
 *
 * @param <T>
 */
public abstract class AbstractQuickAdapter<T> extends AbstractBaseQuickAdapter<T, BaseAdapterHelper> {

	public AbstractQuickAdapter(Context context, int layoutResId) {
		super(context, layoutResId);
	}
	
	public AbstractQuickAdapter(Context context, int layoutResId, List<T> data) {
		super(context, layoutResId, data);
	}

	protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
		
		return get(context, convertView, parent, layoutResId);
	}
}
