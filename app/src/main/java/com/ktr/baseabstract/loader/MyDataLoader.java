//package com.ktr.baseabstract.loader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.loaderdemo.data.MyData;
//
//public class MyDataLoader extends AbstractAsyncNetRequestTaskLoader<MyData> {
//
//	List<MyData> myDatas = new ArrayList<MyData>();
//
//	int flag = 0;
//
//	public MyDataLoader(Context context) {
//		super(context);
//	}
//
//	public static final String TAG = "MyDataLoader";
//
//	@Override
//	protected MyData loadData() throws Exception {
//
//		MyData myDataP = new MyData();
//
//		for (int i = 0; i < 10; i++) {
//
//			Thread.sleep(1000);
//			Log.e(TAG, i+"");
//			MyData myData = new MyData();
//			myData.setId(i + "");
//			myData.setName("ktr ���� " + flag);
//
//			myDatas.add(myData);
//		}
//
//		myDataP.setList(myDatas);
//
//		flag ++;
//
//		return myDataP;
//	}
//
//}
