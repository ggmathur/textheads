package com.ggmathur.textheads;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

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
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();

        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        final View overlay = new HUDView(this);/* create your overlay here */;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT);
        windowManager.addView(overlay , params);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
//        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    class HUDView extends ViewGroup {
        private Paint mLoadPaint;

        public HUDView(Context context) {
            super(context);
            Toast.makeText(getContext(),"HUDView", Toast.LENGTH_LONG).show();

            mLoadPaint = new Paint();
            mLoadPaint.setAntiAlias(true);
            mLoadPaint.setTextSize(10);
            mLoadPaint.setARGB(255, 255, 0, 0);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawText("Hello World", 5, 15, mLoadPaint);
        }

        @Override
        protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //return super.onTouchEvent(event);
            Toast.makeText(getContext(),"onTouchEvent", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
