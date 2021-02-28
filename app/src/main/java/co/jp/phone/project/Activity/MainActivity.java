package co.jp.phone.project.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.jp.phone.project.Helper.DatabaseConnectHelper;
import co.jp.phone.project.R;

/**
 * トップ画面アクションクラス
 * @author growth
 *
 */
public class MainActivity extends AppCompatActivity {
    //DBヘルパークラス
    private DatabaseConnectHelper helper;
    //再生の準備
    MediaPlayer p;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //プロセスダイアログ表示
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("処理を実行中しています");
        progressDialog.setCancelable(true);
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DBファイルを削除
        deleteDatabase("LoadToSQLiteMaster.db");
        deleteDatabase("telList.sqlite");

        //データベースでテーブルを作成する
        //ヘルパークラスインスタンス化
        helper = new DatabaseConnectHelper(this);
        /* データベースを取得 */
        try(SQLiteDatabase db = helper.getWritableDatabase()){
        Toast.makeText(this,"接続しました",Toast.LENGTH_SHORT).show();
        }

        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(),R.raw.bgm_higurashi);
        //連続再生設定
        p.setLooping(true);

        //データベース接続後、プロセスダイアログ表示を閉じる。
        progressDialog.dismiss();
    }
    //「プロローグ」ボタン遷移　prologue_icon
    public void prologue_icon(View v){
        Intent intent = new Intent(MainActivity.this, PlayLinesActivity.class);
        startActivity(intent);
    }

    //「プロローグ後から」ボタン遷移　prologue_after_icon
    public void prologue_after_icon(View v) throws Exception {
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        startActivity(intent);
        }

    //アプリ起動時、再開時 画面が表示されるたびに実行(バックグラウンドミュージック
    @Override
    protected void onResume() {
        super.onResume();
        p.start(); //再生
    }
    //ホームボタン押下時　画面が非表示に実行(バックグラウンドミュージック)
    @Override
    protected void onPause() {
        super.onPause();
        p.pause();
    }
    //戻るボタン押下時　アプリ終了時に実行(バックグラウンドミュージック)モバイル　
    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.release(); //メモリの開放
        p = null; //音楽プレーヤーを破棄
    }

    //TODO　DBデータ作成時の待ち時間に読み込み中のアニメーションを追加する。
}



