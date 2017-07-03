package com.wisata.wisata.cache;

/**
 * Created by syaiful9507 on 1/31/2017.
 */

import android.content.Context;

import java.io.File;

public class FileCache {
    private File cacheDir;
    public FileCache(Context context) {


        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment
                    .getExternalStorageDirectory(), "LazyList");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }
    public File getFile(String url) {

        String filename = String.valueOf(url.hashCode());

        File f = new File(cacheDir, filename);
        return f;
    }
    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }
}
