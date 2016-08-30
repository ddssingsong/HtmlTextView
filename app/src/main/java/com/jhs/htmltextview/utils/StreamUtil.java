package com.jhs.htmltextview.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

    public static String stream2String(InputStream stream) {
        if (stream != null) {


            InputStreamReader isr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(isr);
            String content;
            StringBuilder strBuilder = new StringBuilder();
            try {
                while ((content = br.readLine()) != null) {
                    strBuilder.append(content);
                }
                br.close();
                isr.close();
                stream.close();
            } catch (IOException e) {
                LogUtil.e("JavaHttpUtil", " stream2String异常　", e);
                return null;
            }

            return strBuilder.toString();
        }
        return null;
    }


    public static byte[] stream2ByteArray(InputStream stream) {
        BufferedInputStream bis = new BufferedInputStream(stream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int len = 0;
            byte[] buffer = new byte[128];
            while ((len = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, buffer.length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        byte[] bitmaps = baos.toByteArray();
        try {
            baos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bitmaps;
    }

    public static Bitmap byteArray2Bitmap(byte[] datas, int targetW, int targetH) {
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        int wRate = width / targetW;
        int hRate = height / targetH;
        int rate = wRate > hRate ? wRate : hRate;
        if (rate < 1) {
            rate = 1;
        }
        // rate最好为2的被数,给压缩处理算法减压 (此处未处理)
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = rate;
        return BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
    }

    public static Bitmap stream2Bitmap(InputStream stream, int targetW, int targetH) {
        byte[] datas = stream2ByteArray(stream);
        if (datas == null) {
            return null;
        }
        return byteArray2Bitmap(datas, targetW, targetH);
    }
}
