package com.ktr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.ktr.app.KtrApp;


/**
 * 文件操作工具类
 * Created by zhoubo on 2014/9/24.
 */
public class FileUtil {

	/**
	 * 本地文件存储路径
	 */
	public static final String appFilePath = FileUtil.getSaveFilePath() + "/cache/files/";
	
	public static final String appSeletcedImagePath = FileUtil.getSaveFilePath() + "/cache/take_images/";
	
	public static String getSaveFilePath() {

		return Environment.getExternalStorageDirectory() + "/Android/data/" + "com.ktr.privatemaker";
	}
	
    /**
     * 获取build版本号
     * @return
     */
    public static String getBuildVersion(){

//        try {
//            InputStream is = MakerApp.getInstance().getAssets().open("BuildVersion");
//            return String.valueOf((short)((is.read() << 8) | (is.read())));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "0";
    }

    /**
     * 从资源文件中获取图片file
     * @param resID
     * @param filePath
     * @param fileName
     * @return
     */
    public static File getFileFromRes(int resID,String filePath,String fileName){
        String newFilePath = filePath+File.separator+fileName;
        File file = new File(newFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Bitmap pic = BitmapFactory.decodeResource(KtrApp.getInstance().getResources(), resID);
                FileOutputStream fos = new FileOutputStream(file);
                pic.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    public static StringBuilder readFile(String filePath, String charsetName) {

        File file = new File(filePath);

        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    public static boolean writeFile(String filePath, String content, boolean append) {

        if (Utility.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (Utility.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder
                .mkdirs();
    }

    public static String getFolderName(String filePath) {

        if (Utility.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
	 * 清楚缓存
	 */
	public static void clearCache(Context context) {

		deleteFile(new File(getSaveFilePath()));
	}

    /**
     * 删除文件
     */
    private static void deleteFile(File file) {

        // 判断文件是否存在
        if (file.exists()) {

            // 判断是否是文件
            if (file.isFile()) {
                // 删除文件
                file.delete();
            } else
                // 否则如果它是一个目录
                if (file.isDirectory()) {
                    // 遍历目录下的所有文件;
                    File files[] = file.listFiles();
                    for (int index = 0; index < files.length; index++) {
                        // 删除每个文件
                        deleteFile(files[index]);
                    }
                    // 删除文件夹
                    file.delete();
                }
        }
    }

    /**
     * 获取文件大小
     */
    @SuppressWarnings("resource")
    public static long getFileSize(File file) {

        long size = 0;

        try {

            if (file.exists()) {

                FileInputStream inputStream = new FileInputStream(file);
                size = inputStream.available();
            }
        } catch (Exception e) {

            size = 0;
        }

        return size;
    }

    /**
     * 获取文件夹大小
     */
    public static long getFolderSize(File folder) {

        long size = 0;

        try {

            File fileList[] = folder.listFiles();
            for (int index = 0; index < fileList.length; index++) {

                if (fileList[index].isDirectory()) {

                    size += getFolderSize(fileList[index]);
                } else {

                    size += getFileSize(fileList[index]);
                }
            }
        } catch (Exception e) {
        }

        return size;
    }

    /**
     * 文件夹大小转换
     */
    public static String FormetFileSize(long fileSize) {

        String fileSizeString = null;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        if (fileSize < 1024) {

            fileSizeString = decimalFormat.format((double) fileSize) + " Byte";
        } else if (fileSize < Math.pow(1024, 2)) {

            fileSizeString = decimalFormat.format((double) fileSize / 1024)
                    + " KB";
        } else if (fileSize < Math.pow(1024, 3)) {

            fileSizeString = decimalFormat.format((double) fileSize
                    / Math.pow(1024, 2))
                    + " MB";
        } else {

            fileSizeString = decimalFormat.format((double) fileSize
                    / Math.pow(1024, 3))
                    + " GB";
        }

        return fileSizeString;
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        boolean isok = true;
        if (oldPath != null && newPath != null) {
            try {
                int bytesum = 0;
                int byteread = 0;
                File oldfile = new File(oldPath);
                File newfile = new File(newPath);
                if (oldfile.exists() && !newfile.exists()) { // 旧文件存在时，新文件不存在时
                    InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                    FileOutputStream fs = new FileOutputStream(newPath);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; // 字节数 文件大小
                        fs.write(buffer, 0, byteread);
                    }
                    fs.flush();
                    fs.close();
                    inStream.close();
                } else {
                    isok = false;
                }
            } catch (Exception e) {
                // e.printStackTrace();
                isok = false;
            }
        } else {
            isok = false;
        }
        return isok;
    }
}
