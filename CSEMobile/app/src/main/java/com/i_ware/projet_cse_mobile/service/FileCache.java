package com.i_ware.projet_cse_mobile.service;

import android.content.Context;

import com.i_ware.projet_cse_mobile.ProjetCSEMobileApplication;
import com.i_ware.projet_cse_mobile.utils.UrlParse;

import java.io.File;
import java.net.URL;

/**
 * Created by mac on 07/10/2017.
 */

public class FileCache {
    private File cacheDir;
    ProjetCSEMobileApplication myvalue;

    public FileCache(Context context){
//        myvalue= (ProjetCSEMobileApplication) this.getApplication();

        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"Elec/"+UrlParse.getDirName());
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url){
        //String filename=String.valueOf("ness.pdf");
        String filename=String.valueOf(UrlParse.getPdfName());
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

    public void clearFile(String filename){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }


}
