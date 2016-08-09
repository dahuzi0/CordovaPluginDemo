package com.maxrocky.plugins.base64Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by hwb on 2016/3/30.
 */
public class MRBase64Image extends CordovaPlugin  {
    private final String GET_ACTION = "addressConvertedToDataStream";
    private JSONArray parmars;
    private JSONArray result;
    String s = "";
    String base64 ="";
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        result = new JSONArray();
        if(action.equals(GET_ACTION)){
            parmars = args.getJSONArray(0);
            for(int i=0;i<parmars.length();i++){
                s =  parmars.get(i).toString().substring(8);
                base64 =  imgToBase64(s,null);
                result.put(base64);
            }
            if(result != null){
                callbackContext.success(result);
            }else{
                callbackContext.error("fail");
            }



        }
        return super.execute(action, args, callbackContext);
    }

    /**
     *
     * @param imgPath
     * @param bitmap
     * @return
     */
    public static String imgToBase64(String imgPath, Bitmap bitmap) {
        if (imgPath !=null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if(bitmap == null){
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }
}
