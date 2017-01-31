package w2.com.br.gelanaocongela;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ale on 31/01/2017.
 */

public class AlarmeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Estamos no Receiver", "Uipi");
    }
}
