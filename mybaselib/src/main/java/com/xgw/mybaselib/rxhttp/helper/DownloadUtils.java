package com.xgw.mybaselib.rxhttp.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by XieGuangwei on 2017/9/22.
 */

public class DownloadUtils {
    public static void saveDownloadFile(InputStream inputStream, File file) {
        FileOutputStream fos = null;
        if (inputStream != null) {
            try {
                fos = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int slice;
                while ((slice = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, slice);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
