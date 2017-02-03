package w2.com.br.gelanaocongela;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private TimePicker timePicker;
    private TextView updateAlarm;
    private Context context;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        //iniciar o gerenciador do alarm
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //iniciar timepicker
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        updateAlarm = (TextView) findViewById(R.id.update_alarm);

        final Calendar calendar = Calendar.getInstance();

        final Intent receiver = new Intent(this.context, AlarmeReceiver.class);

        Button ligaAlarme = (Button) findViewById(R.id.ligar_larm);
        ligaAlarme.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                }

                int hora = timePicker.getCurrentHour();
                int minuto = timePicker.getCurrentMinute();

                String horaStr = String.valueOf(hora);
                String minutoStr = String.valueOf(minuto);
                if (minuto < 10) {
                    minutoStr = "0" + String.valueOf(minuto);

                }

                setTextoAlarm("Alarme ligado!" + horaStr + " : " + minutoStr);

                receiver.putExtra("extra", "alarme on");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        });


        Button desligaAlarm = (Button) findViewById(R.id.desligar_alarm);
        desligaAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextoAlarm("Alarme desligado!");
                alarmManager.cancel(pendingIntent);

                receiver.putExtra("extra", "alarme off");
                sendBroadcast(receiver);
            }
        });


    }

    private void setTextoAlarm(String texto) {
        updateAlarm.setText(texto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
