package edu.uit.unit6_broadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;
    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissionSMS();
        initBroadcastReceiver();
    }

    private void requestPermissionSMS() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
            // YES!!
            Toast.makeText(this, "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> YES", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (broadcastReceiver == null){
            initBroadcastReceiver();
        }

        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    private void initBroadcastReceiver(){
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceiver(context, intent);
            }
        };
    }

    private void processReceiver(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();

        TextView tvContent = findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        StringBuilder sms = new StringBuilder();

        SmsMessage smsMessage;
        for (Object object : messages) {
            if (Build.VERSION.SDK_INT >= 23) {
                smsMessage = SmsMessage.createFromPdu((byte[]) object, "3gpp");
            } else {
                smsMessage = SmsMessage.createFromPdu((byte[]) object);
            }

            String smsBody = smsMessage.getMessageBody();

            String address = smsMessage.getDisplayOriginatingAddress();

            sms.append(address).append(":\n").append(smsBody).append("\n");
        }

        tvContent.setText(sms.toString());
    }
}
