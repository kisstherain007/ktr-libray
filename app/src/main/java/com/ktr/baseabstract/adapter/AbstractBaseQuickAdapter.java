package com.ktr.baseabstract.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbstractBaseQuickAdapter<T, H extends BaseAdapterHelper> extends BaseAdapter{

	protected Context context;

	protected int layoutResId;

	protected List<T> data;
	
	public AbstractBaseQuickAdapter(Context context, int layoutResId) {
		this(context, layoutResId, null);
	}
	
	public AbstractBaseQuickAdapter(Context context, int layoutResId, List<T> data) {
		this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
		this.context = context;
		this.layoutResId = layoutResId;
	}
	
	public void add(T elem) {
		data.add(elem);
		notifyDataSetChanged();
	}
	
	public void addAll(List<T> elem) {
		data.addAll(elem);
		notifyDataSetChanged();
	}
	
	public void set(T oldElem, T newElem) {
		set(data.indexOf(oldElem), newElem);
	}

	public void set(int index, T elem) {
		data.set(index, elem);
		notifyDataSetChanged();
	}

	public void remove(T elem) {
		data.remove(elem);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		data.remove(index);
		notifyDataSetChanged();
	}
	
	public void replaceAll(List<T> elem) {
		data.clear();
		data.addAll(elem);
		notifyDataSetChanged();
	}

	public boolean contains(T elem) {
		return data.contains(elem);
	}

	public void clear() {
		data.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int arg0) {
		if (arg0 >= data.size())
			return null;
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		H h = getAdapterHelper(arg0, arg1, arg2);
		
		T item = getItem(arg0);
		
		convert(h, item);
		
		return h.getView();
	}

	protected abstract void convert(H helper, T item);
	
	protected abstract H getAdapterHelper(int position, View convertView, ViewGroup parent);
	
}
