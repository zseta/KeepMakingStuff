package making.stuff.backgroundmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(this,MusicService.class);
        startService(serviceIntent);

        Button playBtn = (Button) findViewById(R.id.playButton);
        Button pauseBtn = (Button) findViewById(R.id.pauseButton);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent("making.stuff.PLAY");
                sendBroadcast(serviceIntent);
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent("making.stuff.PAUSE");
                sendBroadcast(serviceIntent);
            }
        });
    }
}
