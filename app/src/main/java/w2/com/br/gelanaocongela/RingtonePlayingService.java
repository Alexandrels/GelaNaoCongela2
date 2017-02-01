package w2.com.br.gelanaocongela;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by alelinux on 31/01/17.
 */

public class RingtonePlayingService  extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
