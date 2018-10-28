package damisbachtiar17.web.id.jadwalkuliah;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    private NotificationManager mNotificationManager;

    EditText namaMatkul;
    TimePicker timeKuliah;

    Button setMatkul;

    Intent notifyIntent;

    ToggleButton alarmToggle;

    AlarmManager alarmManager;

    PendingIntent notifyPendingIntent;

    public static final String NAMA_MATKUL = "NAMA MATKUL";
    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMatkul = (Button)findViewById(R.id.set_matkul);

        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        alarmToggle = findViewById(R.id.alarmToggle);
        namaMatkul = (EditText) findViewById(R.id.namaMatkul);
        timeKuliah  = (TimePicker) findViewById(R.id.jamKuliah);
        timeKuliah.setIs24HourView(true);
        // Set up the Notification Broadcast Intent.
        //Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        //notifyIntent.putExtra(NAMA_MATKUL,""+namaMatkul.getText().toString());

        setMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MataKuliah",""+namaMatkul.getText().toString());
                notifyIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                notifyIntent.putExtra(NAMA_MATKUL, ""+namaMatkul.getText().toString());

                boolean alarmUp = (PendingIntent.getBroadcast(getApplicationContext(), NOTIFICATION_ID,
                        notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
                alarmToggle.setChecked(alarmUp);

                notifyPendingIntent = PendingIntent.getBroadcast
                        (getApplicationContext(), NOTIFICATION_ID, notifyIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);


                alarmManager = (AlarmManager) getSystemService
                        (ALARM_SERVICE);
            }
        });

        // Set the click listener for the toggle button.
        alarmToggle.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged
                            (CompoundButton buttonView, boolean isChecked) {
                        String toastMessage;
                        Date currentTime = Calendar.getInstance().getTime();
                        timeKuliah.setIs24HourView(true);
                        int jamKuliah = timeKuliah.getCurrentHour();
                        int menitKuliah = timeKuliah.getCurrentMinute();
                        Calendar calendar = GregorianCalendar.getInstance();
                        int nowJam=calendar.get(Calendar.HOUR_OF_DAY);
                        int nowMinute = calendar.get(Calendar.MINUTE);

                        String time1 = nowJam+":"+nowMinute+":00";
                        String time2 = jamKuliah+":"+menitKuliah+":00";

                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        Date date1 = null;
                        Date date2 = null;
                        try {
                            date1 = format.parse(time1);
                            date2 = format.parse(time2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long difference = date2.getTime() - date1.getTime();
                        long diffDetik=difference/1000;
                        long diffMenit = diffDetik/60;
                        Log.d("TEST", "onCreate: ");
                        Log.d("TEST", "jamKuliah: "+jamKuliah);
                        Log.d("TEST", "menitKuliah: "+menitKuliah);
                        Log.d("TEST", "nowJam: "+nowJam);
                        Log.d("TEST", "nowMinute: "+nowMinute);
                        Log.d("TEST", "difference: "+difference);

                        if (isChecked) {
                            long repeatInterval = 3000;




                            long triggerTime = SystemClock.elapsedRealtime()
                                    + repeatInterval;

                            if(diffMenit>=30){
                                long perbedaanWaktu=(difference-(30*60000));
                                triggerTime=SystemClock.elapsedRealtime()+perbedaanWaktu;
                                diffDetik=perbedaanWaktu/1000;
                                diffMenit=diffDetik/60;
                                Toast.makeText(MainActivity.this, "ALARM AKAN AKTIF DALAM "+diffMenit+" Menit",
                                        Toast.LENGTH_SHORT).show();
                            }

                            Log.d("fuck", "triggerTime: " + triggerTime);

                            // If the Toggle is turned on, set the repeating alarm with
                            // a 15 minute interval.
                            if (alarmManager != null) {
                                alarmManager.setInexactRepeating
                                        (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                                triggerTime, repeatInterval,
                                                notifyPendingIntent);
                            }
                            // Set the toast message for the "on" case.
                            toastMessage = getString(R.string.alarm_on_toast);

                        } else {
                            // Cancel notification if the alarm is turned off.
                            mNotificationManager.cancelAll();

                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            // Set the toast message for the "off" case.
                            toastMessage = getString(R.string.alarm_off_toast);

                        }

                        // Show a toast to say the alarm is turned on or off.
                        Toast.makeText(MainActivity.this, toastMessage,
                                Toast.LENGTH_SHORT).show();
                    }
                });

        // Create the notification channel.
        createNotificationChannel();
    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to " +
                    "stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
