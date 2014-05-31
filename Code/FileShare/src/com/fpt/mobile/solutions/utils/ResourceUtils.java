package com.fpt.mobile.solutions.utils;

/**
 * Created by ThaoHQSE60963 on 5/30/14.
 */

import android.graphics.drawable.Drawable;
import com.fpt.mobile.solutions.config.BunnyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** contain useful methods for loading resource from system */
public class ResourceUtils {

    public static String[] loadStringArray(int ResourceId) {
        return BunnyApplication.getAppContext().getResources().getStringArray(ResourceId);
    }

    public static Drawable getDrawableFromResId(int id) {
        return BunnyApplication.getAppContext().getResources().getDrawable(id);
    }

    /*public static boolean isDatabaseFileExist() {
        File file = new File(BunnyApplication.mContext.getDatabasePath(
                HopAmChuanDatabase.DATABASE_NAME).getAbsolutePath());
        return file.exists();
    }*/

    public static String readFile(String path) {
        //Get the text file
        File file = new File(path);

        //Read text from file
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
        }
        return text.toString();
    }

}
