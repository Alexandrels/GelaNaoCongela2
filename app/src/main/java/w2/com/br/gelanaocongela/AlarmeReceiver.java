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

        String pega_minha_string = intent.getExtras().getString("extra");
        Log.e("Qual é a chave", pega_minha_string);


        Intent intentService = new Intent(context, RingtonePlayingService.class);
        intentService.putExtra("extra", pega_minha_string);

        context.startService(intentService);
    }

}
