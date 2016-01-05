package com.bizan.mobile10.dpod;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity
        implements OnClickListener, SeekBar.OnSeekBarChangeListener {

    MediaPlayer mp;
    Button btnRev;
    Button btnStp;
    Button btnPly;
    Button btnPause;
    Button btnFwd;
    Button btnMinus;
    Button btnMute;
    Button btnPlus;
    SeekBar skbVol;
    TextView txvTitle;
    ListView lst;

    private String[] items = {"apple_loop_only", "tsuchi", "Music03", "Music04",
                                "Music05", "Music06", "Music07", "Music08"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //表示するビューの指定
        setContentView(R.layout.activity_main);

        btnRev = (Button) findViewById(R.id.btnRev);
        btnRev.setOnClickListener(this);

        btnStp = (Button) findViewById(R.id.btnStp);
        btnStp.setOnClickListener(this);

        btnPly = (Button) findViewById(R.id.btnPly);
        btnPly.setOnClickListener(this);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);

        btnFwd = (Button) findViewById(R.id.btnFwd);
        btnFwd.setOnClickListener(this);

        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnMute = (Button) findViewById(R.id.btnMute);
        btnMute.setOnClickListener(this);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        skbVol = (SeekBar) findViewById(R.id.skbVol);
        skbVol.setOnSeekBarChangeListener(this);

        lst = (ListView) findViewById(R.id.lst);
        txvTitle = (TextView) findViewById(R.id.txvTitle);

        //ArrayAdapterオブジェクトの生成
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, items);
        //Adapterの指定
        lst.setAdapter(adapter);
        //初期選択要素の位置決め
//        listView.setSelection(0);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //トースト表示
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                txvTitle.setText(item);
                Toast.makeText(MainActivity.this, "Click: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        // 音声メディアの指定&再生
        try
        {
            mp = MediaPlayer.create(this, R.raw.apple_loop_only);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Log.v("MediaPlayer", "onDestroy");
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.release();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnPly:
                mp.start();
                break;

            case R.id.btnPause:
                if (mp.isPlaying()) {
                    mp.pause();
                } else {
                    mp.start();
                }
                break;

            case R.id.btnStp:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.apple_loop_only);
                break;

            case R.id.btnFwd:
                break;

            case R.id.btnRev:
                break;

            case R.id.btnMinus:
                break;

            case R.id.btnPlus:
                break;

            case R.id.btnMute:
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
