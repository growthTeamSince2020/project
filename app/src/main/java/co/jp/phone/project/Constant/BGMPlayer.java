package co.jp.phone.project.Constant;

import android.content.Context;
import android.media.MediaPlayer;

public class BGMPlayer {

    MediaPlayer mediaPlayer;

    public void onCreate(Context context, int id){
        mediaPlayer = MediaPlayer.create(context,id);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(1f,1f);
    }

}
