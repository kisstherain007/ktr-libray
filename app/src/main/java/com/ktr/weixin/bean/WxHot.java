package com.ktr.weixin.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by kisstherain on 2016/1/5.
 */
public class WxHot {

    private int code;

    private String msg;

    private List<Map<String, HotChild>> hotChilds;

    static class HotChild{

        String hottime;
        String title;
        String description;
        String picUrl;
        String url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Map<String, HotChild>> getHotChilds() {
        return hotChilds;
    }

    public void setHotChilds(List<Map<String, HotChild>> hotChilds) {
        this.hotChilds = hotChilds;
    }
}
