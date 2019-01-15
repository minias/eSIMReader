package com.google.android.esimreader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        byte[] inData = new byte[2];
        inData[0] = 0x6F;
        inData[1] = (byte)0xAD;
        String outData="";
        try {
            outData = tm.iccTransmitApduBasicChannel(0xA0, 0xA4, 0x00, 0x00, 0x02, inData.toString());
        }
        catch (SecurityException e)
        {
            Log.i(TAG, "eSIMReader exception: "+e.getMessage());
        }
        Log.i(TAG, "onCreate: received data from iccTransmitApduBasicChannel :"+outData);

    }
}
