package edu.uit.unit6_broadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;

    private IntentFilter filter;
    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> listRequester;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastAutoResponseReceiver;
    public static boolean isRunning;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissionSMS();
//        initBroadcastReceiver();

        //for bai 4
        findViewsByIds();
        initVariables();
        handleOnClickListener();
    }

    private void requestPermissionSMS() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS},
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

//        if (broadcastReceiver == null) {
//            initBroadcastReceiver();
//        }

//        registerReceiver(broadcastReceiver, filter);

        isRunning = true;
        if (broadcastAutoResponseReceiver == null){
            initAutoResponseBroadcastReceiver();
        }

        IntentFilter intentFilter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastAutoResponseReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
//        unregisterReceiver(broadcastReceiver);
        unregisterReceiver(broadcastAutoResponseReceiver);
    }

    private void initBroadcastReceiver() {
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceiver(context, intent);
            }
        };
    }

    private void initAutoResponseBroadcastReceiver(){
        broadcastAutoResponseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<String> addresses =  intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);

                assert addresses != null;
                processReceiverAddress(addresses);
            }
        };
    }

    private void processReceiver(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();

        TextView tvContent = findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        assert bundle != null;
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

    private void findViewsByIds() {
        swAutoResponse = findViewById(R.id.sw_auto_response);
        llButtons = findViewById(R.id.ll_buttons);
        lvMessages = findViewById(R.id.lv_messages);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
    }

    private void initVariables(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        reentrantLock =  new ReentrantLock();
        listRequester = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listRequester);
        lvMessages.setAdapter(adapter);
        lvMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        boolean autoResponse = sharedPreferences.getBoolean(AUTO_RESPONSE, false);
        if (autoResponse) llButtons.setVisibility(View.GONE);

        initAutoResponseBroadcastReceiver();
    }

    private void respond(String to, String response) {
        reentrantLock.lock();
        listRequester.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(to, null, response, null, null);
    }

    public void respond(boolean ok) {
        String okString = getString(R.string.i_am_safe_and_well_worry_not);
        String notOkString = getString(R.string.tell_my_mother_i_love_her);
        String outputString = ok ? okString : notOkString;
        ArrayList<String> requesterCopy = (ArrayList<String>) listRequester.clone();

        for (String to: requesterCopy)
            respond(to, outputString);
    }

    public void processReceiverAddress(ArrayList<String> addresses){
        for(int i = 0; i< addresses.size(); i++){
            if (!listRequester.contains(addresses.get(i))){
                reentrantLock.lock();
                listRequester.add(addresses.get(i));
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();
            }

            if (swAutoResponse.isChecked()) respond(true);
        }
    }

    private void handleOnClickListener(){
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respond(true);
            }
        });

        btnMayday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respond(false);
            }
        });

        swAutoResponse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) llButtons.setVisibility(View.GONE);
                else llButtons.setVisibility(View.VISIBLE);

                //save auto response setting
                sharedPreferences.edit().putBoolean(AUTO_RESPONSE, isChecked).apply();
            }
        });
    }

}
