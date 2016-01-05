package com.ktr.utils.bitmaploader.download;

/**
 * Created by kisstherain on 2015/11/19.
 */
public interface Downloader {

    public byte[] downloadBitmap(String url/*, ImageConfig config*/) throws Exception;
}
