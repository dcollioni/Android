package com.example.exemplonotification;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        String tickerText = "Primeira notificação.";
        CharSequence title = "Atenção!";
        CharSequence message = "Blah blah blah...";
        
        //PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, this.getIntent(), 0);
        
//        Builder b = new Notification.Builder(getBaseContext());
//        b.setTicker(tickerText);
//        b.setContentTitle(title);
//        b.setContentText(message);
        
        
        
        NotificationCompat.Builder b =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        
        Intent resultIntent = new Intent(this, OtherActivity.class);
        
//        b.setContentIntent(pi);
        //b.build();
        
     // The stack builder object will contain an artificial back stack for the
     // started Activity.
     // This ensures that navigating backward from the Activity leads out of
     // your application to the Home screen.
     TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
     // Adds the back stack for the Intent (but not the Intent itself)
     stackBuilder.addParentStack(OtherActivity.class);
     // Adds the Intent that starts the Activity to the top of the stack
     stackBuilder.addNextIntent(resultIntent);
     PendingIntent resultPendingIntent =
             stackBuilder.getPendingIntent(
                 0,
                 PendingIntent.FLAG_UPDATE_CURRENT
             );
     b.setContentIntent(resultPendingIntent);
     NotificationManager mNotificationManager =
         (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
     // mId allows you to update the notification later on.
     mNotificationManager.notify(10, b.build());
        
        
        
        
//        Notification n = b.build();
//        
//  //      n.s
//        //b.notify();
//        //n.vibrate = new long[] { 1000, 1000 };
//        
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(10, n);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
