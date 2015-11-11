package com.ktr.privatemaker.ui.my;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ktr.baseabstract.adapter.BaseAdapterHelper;
import com.ktr.baseabstract.adapter.QuickAdapter;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.privatemaker.ui.HomeActivity;
import com.ktr.ui.widget.DisplayPicsView;
import com.ktr.utils.ThemeUtils;
import com.ktr.utils.ToastUtil;
import com.ktr.utils.provider.user.UserColumns;
import com.ktr.utils.provider.user.UserCursor;
import com.ktr.utils.provider.user.UserSelection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by kisstherain on 2015/9/26.
 */
public class MyFragment extends AbstractFragment {

//    ListView setting_listView;
//    EditText edit_content;
//    List<String> datas = new ArrayList<>();
//    南昌公共  http://w.ncnews.com.cn:1935/live/nctv4/playlist.m3u8
//    南昌新闻  http://w.ncnews.com.cn:1935/live/nctv1/playlist.m3u8
//    南昌咨询  http://w.ncnews.com.cn:1935/live/nctv3/playlist.m3u8
    public static final String TAG = MyFragment.class.getSimpleName();

    private static final String[] FIRST_NAMES = { "周波", "kisstherain", "18611352192" };

    DisplayPicsView dispaly_pics_view;

    VideoView videoView;

    public static MyFragment newInstance(){

        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dispaly_pics_view = (DisplayPicsView) view.findViewById(R.id.dispaly_pics_view);

        view.findViewById(R.id.theme1_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispaly_pics_view.setPics(1);
            }
        });

        view.findViewById(R.id.theme2_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispaly_pics_view.setPics(2);
            }
        });

        view.findViewById(R.id.theme3_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispaly_pics_view.setPics(3);
            }
        });

        view.findViewById(R.id.theme4_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispaly_pics_view.setPics(4);
            }
        });

        videoView = (VideoView) view.findViewById(R.id.video_view);

        Uri uri = Uri.parse("http://bcs.duapp.com/dlna-sample/out_MP4_AVC_AAC_320x240_2013761628.mp4?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:7zwy3HtoM%2B5hXB2%2FlJFN6OkWFCs%3D");
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
//        datas.add("主题1");
//        datas.add("主题2");
//        datas.add("主题3");
//        datas.add("主题4");
//
//        setting_listView = (ListView) view.findViewById(R.id.setting_listView);
//
//        setting_listView.setAdapter(new QuickAdapter<String>(getActivity(), R.layout.my_list_item_layout, datas) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, String item) {
//
//                helper.setText(R.id.title_textView, item);
//            }
//        });
//
//        setting_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                ((HomeActivity) getActivity()).reload(ThemeUtils.getThemeColor(position));
//            }
//        });
//
//        edit_content = (EditText) view.findViewById(R.id.edit_content);
//        edit_content.setFilters(new InputFilter[]{inputFilter});
//        edit_content.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                SpannableString spanned = new SpannableString(s.toString());
//                ForegroundColorSpan span = new ForegroundColorSpan(Color.BLACK);
//                spanned.setSpan(span, 0, s.toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                for (String str : inputList){
//
//                    if (s.toString().contains(str)){ // 找匹配
//
//                        SpannableString spanned1 = new SpannableString(str);
//                        ForegroundColorSpan span1 = new ForegroundColorSpan(Color.BLUE);
//                        spanned1.setSpan(span1, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

//    List<String> inputList = new ArrayList<String>(Arrays.asList("zhoubo", "kisstherain"));
//
//    private InputFilter inputFilter = new InputFilter() {
//
//        @Override
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
//            String inputContent = source.toString();
//
//            if ("".equals(source)) {
//
////                SpannableString spanned = new SpannableString(dest.toString());
////                ForegroundColorSpan span = new ForegroundColorSpan(Color.BLACK);
////                spanned.setSpan(span, 0, dest.toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////
////                add += spanned;
////
////                for (String str : inputList){
////
////                    if (dest.toString().contains(str)){ // 找匹配
////
////                        SpannableString spanned1 = new SpannableString(str);
////                        ForegroundColorSpan span1 = new ForegroundColorSpan(Color.BLUE);
////                        spanned1.setSpan(span1, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////                    }
////                }
//
//                ToastUtil.showMsg("start:" + start + "end" + end);
//
//                return null;
//            }
//
//            if(inputList.contains(inputContent)){
//                SpannableString spanned = new SpannableString(source.toString());
//                ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
//                spanned.setSpan(span, 0, source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                return spanned;
//            }else return source;
//        }
//    };

    private void queryUser(){

        UserSelection userSelection = new UserSelection();

        userSelection.mame("kisstherain");
        String[] projection = { UserColumns._ID, UserColumns.USER_NAME};
        UserCursor c = userSelection.query(getActivity().getContentResolver(), projection);
        while (c.moveToNext()) {
            Log.d(TAG, c.getName());
        }
        c.close();

        // Query one person
        userSelection  = new UserSelection();
        userSelection.id(2);
        c = userSelection.query(getActivity().getContentResolver(), projection);
        while (c.moveToNext()) {
            Log.d(TAG, c.getId() + " - " + c.getName());
        }
        c.close();

        // Another way to query one person
        Log.d(TAG, "---");
        Uri uri = ContentUris.withAppendedId(UserColumns.CONTENT_URI, 2l);
        Cursor c2 = getActivity().getContentResolver().query(uri, projection, null, null, null);
        c = new UserCursor(c2);
        while (c.moveToNext()) {
            Log.d(TAG, c.getId() + " - " + c.getName() );
        }
        c.close();

        // Like / startsWitdh / contains / endsWith + order by
        Log.d(TAG, "---");
        userSelection = new UserSelection();
        userSelection.nameEndsWith("SON").or().nameContains("ar", "ae").orderByName();
        c = userSelection.query(getActivity().getContentResolver(), projection);
        while (c.moveToNext()) {
            Log.d(TAG, c.getId() + " - " + c.getName());
        }
        c.close();
    }
}
