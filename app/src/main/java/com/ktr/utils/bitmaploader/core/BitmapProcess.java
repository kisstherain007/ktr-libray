package com.ktr.utils.bitmaploader.core;

import android.text.TextUtils;

import com.ktr.utils.bitmaploader.BitmapLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by kisstherain on 2015/11/24.
 */
public class BitmapProcess {

    private static final String TAG = BitmapProcess.class.getSimpleName();

    private FileDisk compFielDisk;// 保存压缩或者缩放后的图片

    private FileDisk origFileDisk;// 保存原始下载

    public BitmapProcess(String imageCache) {
        compFielDisk = new FileDisk(imageCache + File.separator + "compression");
        origFileDisk = new FileDisk(imageCache + File.separator + "originate");
    }

    private byte[] getBitmapFromDiskCache(String url, String key, FileDisk fileDisk, ImageConfig config) throws Exception {
        InputStream inputStream = fileDisk.getInputStream(url, key);

        if (inputStream == null)
            return null;

        if (config.getProgress() != null)
            config.getProgress().sendLength(inputStream.available());

        byte[] buffer = new byte[8 * 1024];
        int readLen = -1;
        int readBytes = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((readLen = inputStream.read(buffer)) != -1) {
            readBytes += readLen;
            if (config.getProgress() != null)
                config.getProgress().sendProgress(readBytes);
            outputStream.write(buffer, 0, readLen);
        }
        return outputStream.toByteArray();
    }

    public File getOirgFile(String url) {
        String key = url;
        return origFileDisk.getFile(url, key);
    }

    public File getCompressFile(String url, String imageId) {
        ImageConfig config = null;
        if (!TextUtils.isEmpty(imageId)) {
            config = new ImageConfig();
            config.setId(imageId);
        }
        String key = url;

        return compFielDisk.getFile(url, key);
    }
}
