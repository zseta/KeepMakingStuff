package making.stuff.backgroundmusic;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

public class MusicService extends Service {

    private static final String PLAY_ACTION = "making.stuff.PLAY";
    private static final String PAUSE_ACTION = "making.stuff.PAUSE";
    private MediaPlayer player;
    private BroadcastReceiver receiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(PLAY_ACTION)){
                    if (player != null) player.start();
                    else startPlaying();
                }
                else if (action.equals(PAUSE_ACTION)){
                    player.pause();
                }
            }
        };
        initReceiver();
        return START_NOT_STICKY;
    }

    private void startPlaying(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                player = MediaPlayer.create(this, R.raw.my_music);
                player.start();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    private void initReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(PLAY_ACTION);
        filter.addAction(PAUSE_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){
        player.release();
        unregisterReceiver(receiver);
    }
}
