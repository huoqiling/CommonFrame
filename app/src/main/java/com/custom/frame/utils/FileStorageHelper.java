package com.custom.frame.utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author zhangxin
 * @date 2018/2/28
 * @description 获取raw目录下文件，并保存在本地
 */
public class FileStorageHelper {

    private static final String SEPARATOR = File.separator;//路径分隔符

    /**
     * 复制res/raw中的文件到指定目录
     *
     * @param context     上下文
     * @param id          资源ID
     * @param fileName    文件名
     * @param storagePath 目标文件夹的路径
     */
    public static String copyFilesFromRaw(Context context, int id, String fileName, String storagePath) {
        String filePath = "";
        try {
            InputStream inputStream = context.getResources().openRawResource(id);
            File file = new File(storagePath);
            if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
                file.mkdirs();
            }
            filePath = readInputStream(storagePath + SEPARATOR + fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }


    /**
     * 读取输入流中的数据写入输出流
     *
     * @param storagePath 目标文件路径
     * @param inputStream 输入流
     */
    private static String readInputStream(String storagePath, InputStream inputStream) {
        File file = new File(storagePath);
        try {
            if (!file.exists()) {
                // 1.建立通道对象
                FileOutputStream fos = new FileOutputStream(file);
                // 2.定义存储空间
                byte[] buffer = new byte[inputStream.available()];
                // 3.开始读文件
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {// 循环从输入流读取buffer字节
                    // 将Buffer中的数据写到outputStream对象中
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();// 刷新缓冲区
                // 4.关闭流
                fos.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
