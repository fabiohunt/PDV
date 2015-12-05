package br.com.trainning.pdv.domain.util;

import android.content.Context;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fabiogomes on 12/5/15.
 */
public class Util {

    public static String getUniqueId(Context context){

        String android_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Date data = new Date();

        String dataFormatada = sdf.format(data);

        return android_id+dataFormatada;


    }


}
