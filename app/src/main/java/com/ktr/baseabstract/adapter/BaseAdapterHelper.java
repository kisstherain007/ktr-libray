package com.ktr.baseabstract.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHodler
 * @author zhoubo
 * 
 */
public class BaseAdapterHelper {

	private Context context;

	private SparseArray<View> views;

	private View convertView;

	private int position;

	public int layoutId;

	protected BaseAdapterHelper(Context context, ViewGroup parent,int layoutId, int position) {
		
		this.context = context;
		this.position = position;
		this.layoutId = layoutId;
		this.views = new SparseArray<View>();
		convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		convertView.setTag(this);
	}
	
	public static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId) {
		return get(context, convertView, parent, layoutId, -1);
	}
	
	static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new BaseAdapterHelper(context, parent, layoutId, position);
		}

		BaseAdapterHelper existingHelper = (BaseAdapterHelper) convertView.getTag();
		if (existingHelper.layoutId != layoutId) {
			return new BaseAdapterHelper(context, parent, layoutId, position);
		}

		existingHelper.position = position;
		
		return existingHelper;
	}
	
	public <T extends View> T getView(int viewId) {
		return retrieveView(viewId);
	}
	
	/**
	 * retrieve convertView to 
	 * @return
	 */
	public View getView() {
		return convertView;
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends View> T retrieveView(int viewId) {
		View view = views.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
	
	public int getPosition() {
		if (position == -1)
			throw new IllegalStateException(
					"Use BaseAdapterHelper constructor "
							+ "with position if you need to retrieve the position.");
		return position;
	}
	
	public BaseAdapterHelper setVisible(int viewId, boolean visible) {
		View view = retrieveView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}
	
	public BaseAdapterHelper setTextColorRes(int viewId, int textColorRes) {
		TextView view = retrieveView(viewId);
		view.setTextColor(context.getResources().getColor(textColorRes));
		return this;
	}
	
	public BaseAdapterHelper setTextColor(int viewId, int textColor) {
		TextView view = retrieveView(viewId);
		view.setTextColor(textColor);
		return this;
	}
	
	public BaseAdapterHelper setBackgroundColor(int viewId, int color) {
		View view = retrieveView(viewId);
		view.setBackgroundColor(color);
		return this;
	}
	
	public BaseAdapterHelper setBackgroundRes(int viewId, int backgroundRes) {
		View view = retrieveView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}
	
	public BaseAdapterHelper setImageResource(int viewId, int imageResId) {
		ImageView view = retrieveView(viewId);
		view.setImageResource(imageResId);
		return this;
	}
	
	public BaseAdapterHelper setText(int viewId, String value) {
		TextView view = retrieveView(viewId);
		view.setText(value);
		return this;
	}
	
	public BaseAdapterHelper setTag(int viewId, Object tag) {
		View view = retrieveView(viewId);
		view.setTag(tag);
		return this;
	}
	
	public BaseAdapterHelper setOnClickListener(int viewId, View.OnClickListener listener) {
		View view = retrieveView(viewId);
		view.setOnClickListener(listener);
		return this;
	}
	
	public BaseAdapterHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
		View view = retrieveView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}
}
