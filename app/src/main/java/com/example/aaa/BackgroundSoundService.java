package com.example.aaa;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundSoundService extends Service implements MediaPlayer.OnCompletionListener {
    private List<Song> mList;//歌曲列表
    private static final String TAG = "BackgroundSoundService";
    MediaPlayer mediaPlayer;
    // 记录当前播放歌曲的位置
    public int mCurrentPosition;

    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "onBind()" );
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mList = new ArrayList<>();//实例化
        //数据赋值
        mList = MusicUtils.getMusicData(this);//将扫描到的音乐赋值给音乐列表

        //player = MediaPlayer.create(this, R.raw.jorgesys_song);
        // player.setLooping(true); // Set looping
        // player.setVolume(100,100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        //player.start();
        changeMusic(0);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    //切歌
    private void changeMusic(int position) {
        Log.e("MainActivity", "position:" + position);
        if (position < 0) {
            mCurrentPosition = position = mList.size() - 1;
            Log.e("MainActivity", "mList.size:" + mList.size());
        } else if (position > mList.size() - 1) {
            mCurrentPosition = position = 0;
        }
        Log.e("MainActivity", "position:" + position);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);//监听音乐播放完毕事件，自动下一曲
        }

        try {
            // 切歌之前先重置，释放掉之前的资源
            mediaPlayer.reset();
            // 设置播放源
            Log.d("Music", mList.get(position).path);
            mediaPlayer.setDataSource(mList.get(position).path);

            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mediaPlayer.prepare();
            // 开始播放
            mediaPlayer.start();
            mediaPlayer.setVolume(0.3f,0.3f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLowMemory() {
        Log.i(TAG, "onLowMemory()");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        changeMusic(++mCurrentPosition);
    }
}

