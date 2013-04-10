package com.ggmathur.textheads;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class TextReceiver extends BroadcastReceiver {
    public static String MESSAGE_INTENT_EXTRA = "messages";
    
    public TextReceiver() {
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        
        final Bundle extras = intent.getExtras();

        final JSONObject messages = new JSONObject();
        if ( extras != null ) {
            // Get received SMS array
            final Object[] smsExtra = (Object[]) extras.get( "pdus" );
               
            for ( int i = 0; i < smsExtra.length; ++i )
            {
                final SmsMessage sms = SmsMessage.createFromPdu( (byte[]) smsExtra[i] );
                 
                final String body = sms.getMessageBody().toString();
                final String address = sms.getOriginatingAddress();
                try {
                    messages.put(address, body);
                } catch (JSONException e) {
                    Log.e("UPDATE_ME", "Error while adding message to json array", e);
                }              
            }
             
            // Display SMS message
            Toast.makeText( context, messages.toString(), Toast.LENGTH_SHORT ).show();
        }
                
        final Intent textHeadIntent = new Intent(context, TextHeadService.class);
        
        textHeadIntent.putExtra(MESSAGE_INTENT_EXTRA, messages.toString());
        context.startService(textHeadIntent);
        
        final Intent broadcastSmsIntent = new Intent("SMS_RECEIVED_ACTION");
        context.sendBroadcast(broadcastSmsIntent);        
    }
}
