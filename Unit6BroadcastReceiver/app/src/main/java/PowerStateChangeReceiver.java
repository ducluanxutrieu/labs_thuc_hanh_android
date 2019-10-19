import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import edu.uit.unit6_broadcastreceiver.R;

public class PowerStateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) return;
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, context.getString(R.string.power_conneted), Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().endsWith(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, context.getString(R.string.power_disconnected), Toast.LENGTH_SHORT).show();
        }
    }
}
