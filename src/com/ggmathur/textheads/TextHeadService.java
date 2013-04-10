package com.ggmathur.textheads;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

public class TextHeadService extends Service {
    private static String TAG = "TextHeadService";
    public TextHeadService() {
        
    }
    
    @Override
    public void onStart(final Intent intent, final int startId) {
        // TODO Auto-generated method stub
//        onCreate();
        final Bundle extras = intent.getExtras();
        
        if (extras != null) {
            final String messagesAsJson = extras.getString(TextReceiver.MESSAGE_INTENT_EXTRA);
            try {
                final JSONObject messages = new JSONObject(messagesAsJson);
                Log.i(TAG, messages.toString());
                

                final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

               final Iterator<String> addressIterator = messages.keys();
               
               TextHeadView textHead = null;
               while (addressIterator.hasNext()) {
                   final String address = addressIterator.next();
                   final String message = (String) messages.get(address);
                   textHead = new TextHeadView(this, address, message);
               }
               
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    PixelFormat.TRANSLUCENT);
                windowManager.addView(textHead , params);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
