package w2.com.br.gelanaocongela;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by alelinux on 31/01/17.
 */

public class RingtonePlayingService extends Service {

    public MediaPlayer mediaSong;
    private int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state: extra e",state);

        assert state != null;
        switch (state) {
            case "alarme on":
                startId = 1;
                break;
            case "alarme off":
                startId = 0;

                break;
            default:
                startId = 0;
                break;
        }

        mediaSong = MediaPlayer.create(this, R.raw.super_mario_world);
        mediaSong.start();


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
    }


}
