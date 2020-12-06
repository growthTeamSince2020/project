package co.jp.phone.project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //データベースでテーブルを作成する
        //ヘルパークラスインスタンス化
        helper = new DatabaseConnectHelper(this);
        /* データベースを取得 */
        try(SQLiteDatabase db = helper.getWritableDatabase()){
        Toast.makeText(this,"接続しました",Toast.LENGTH_SHORT).show();
        }
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
}



