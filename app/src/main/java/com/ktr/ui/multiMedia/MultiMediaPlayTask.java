package com.ktr.ui.multiMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisstherain on 2015/11/11.
 */
public class MultiMediaPlayTask {

    /** 播放地址*/
    private List<String> playUrlList;

    /** 当前播放位置*/
    private int currentPlayIndex;

    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    public String getNextPlayUrl(){

        currentPlayIndex ++;

        if (playUrlList != null && !playUrlList.isEmpty()){

            if(currentPlayIndex > playUrlList.size() - 1){

                currentPlayIndex = 0;
            }

            return playUrlList.get(currentPlayIndex);
        }

        return null;
    }

    public String getPrePlayUrl(){

        currentPlayIndex--;

        if (playUrlList != null && !playUrlList.isEmpty()){

            if (currentPlayIndex < 0){

                currentPlayIndex = playUrlList.size() - 1;
            }
                return playUrlList.get(currentPlayIndex);
        }

        return null;
    }

    public String getCurrentPlayUrl(){

        if (playUrlList != null && !playUrlList.isEmpty()){

            return playUrlList.get(currentPlayIndex);
        }

        return null;
    }

    /**
     * 设置播放地址
     * @param url 播放路径
     */
    public void addPlayUrl(String url){

        if (playUrlList == null){

            playUrlList = new ArrayList<>();
        }

        if(!playUrlList.contains(url)){

            playUrlList.add(url);

            currentPlayIndex = playUrlList.size() - 1;
        }else{

            currentPlayIndex = playUrlList.indexOf(url);
        }
    }

    /**
     * 设置播放地址
     * @param urlList
     */
    public void addPlayUrl(List<String> urlList){

        if (playUrlList == null){

            playUrlList = new ArrayList<>();
        }

        playUrlList.addAll(urlList);

        currentPlayIndex = playUrlList.size() - 1;
    }

    /**
     * 清空所有播放地址
     */
    public void removeAllPlayUrl(){

        if(playUrlList != null) playUrlList.clear();
        currentPlayIndex = -1;
    }

    /**
     * 清空播放地址
     * @param index
     */
    public void removePlayUrl(int index){

        if(playUrlList != null && !playUrlList.isEmpty()) playUrlList.remove(index);
    }
}
