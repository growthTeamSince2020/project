package co.jp.phone.project.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import co.jp.phone.project.Constant.InsertDataConstant;
import co.jp.phone.project.Constant.TelNumberConst;
import co.jp.phone.project.Helper.DatabaseConnectHelper;
import co.jp.phone.project.R;

public class PlayActivity extends AppCompatActivity {

    //　オールクリアボタン
    private ImageView ac_bottom;

    // 電話番号呼び出しボタン
    private ImageView tel_Bottom;

    private EditText editText;

    //音源再生用の引数
    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private AudioAttributes audioAttributes;
    private final String se1 = "1";
    private final String se2 = "2";
    private final String se3 = "3";
    private final String se4 = "4";
    /** デフォルトのカウント情報を取得*/
    int count = 1;
    //再生の準備
    MediaPlayer p;
    /**
     * シングルクォート
     */
    private final String sQuote = "'";

    /**
     * ボタン入力情報を設定
     */
    public void on_ac_telButton_Click(View view) throws InterruptedException {

        if (view.getId() == R.id.ac_bottom) {
            this.clearText();
        } else if (view.getId() == R.id.tel_Bottom) {
            ImageView iv_telbtm = (ImageView) findViewById(R.id.tel_Bottom);
            iv_telbtm.setVisibility(View.GONE);
            this.numberLoad();
        } else if (view.getId() == R.id.tel_bottom1) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_b);
            p.start();
            this.init(TelNumberConst.button1);
        } else if (view.getId() == R.id.tel_bottom2) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_c);
            p.start();
            this.init(TelNumberConst.button2);
        } else if (view.getId() == R.id.tel_bottom3) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_d);
            p.start();
            this.init(TelNumberConst.button3);
        } else if (view.getId() == R.id.tel_bottom4) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_c);
            p.start();
            this.init(TelNumberConst.button4);
        } else if (view.getId() == R.id.tel_bottom5) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_b);
            p.start();
            this.init(TelNumberConst.button5);
        } else if (view.getId() == R.id.tel_bottom6) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_d);
            p.start();
            this.init(TelNumberConst.button6);
        } else if (view.getId() == R.id.tel_bottom7) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_a);
            p.start();
            this.init(TelNumberConst.button7);
        } else if (view.getId() == R.id.tel_bottom8) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_c);
            p.start();
            this.init(TelNumberConst.button8);
        } else if (view.getId() == R.id.tel_bottom9) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_b);
            p.start();
            this.init(TelNumberConst.button9);
        } else if (view.getId() == R.id.tel_bottom0) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_d);
            p.start();
            this.init(TelNumberConst.button0);
        } else if (view.getId() == R.id.tel_bottom_asta) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_c);
            p.start();
            this.init(TelNumberConst.button_asta);
        } else if (view.getId() == R.id.tel_bottom_sharp) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_btm_a);
            p.start();
            this.init(TelNumberConst.button_sharp);
        }
    }

    /*EditTextクリア処理*/
    private void clearText() {
        EditText editText = (EditText) findViewById(R.id.text);
        String clearTxt = editText.getText().toString();
        clearTxt = "";
        editText.setHeight(12);
        editText.setText(clearTxt, EditText.BufferType.NORMAL);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        editText = (EditText) findViewById(R.id.text);
        editText.setTextColor(Color.RED);
        editText.setFocusable(false);

        /** １０円玉の画像を取得*/
        ImageView iv_coin2 = (ImageView) findViewById(R.id.coin2);
        ImageView iv_coin3 = (ImageView) findViewById(R.id.coin3);

        /** ワークテーブルから情報を取得*/
        Cursor record = wkTbList();
        int record_count = record.getCount();

        if (record_count == 1) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_juwakitoru);
            p.start();

            /** １０円玉１枚分を非表示*/
            iv_coin3.setVisibility(View.GONE);
        } else if (record_count == 2) {
            //音楽の読み込み
            p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_juwakitoru);
            p.start();

            /** １０円玉２枚分を非表示*/
            iv_coin2.setVisibility(View.GONE);
            iv_coin3.setVisibility(View.GONE);
        }

        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_higurashi);
        //連続再生設定
        p.setLooping(true);
    }
    /*入力番号をEditText蘭に表示すでに入力されている情報がある場合は値を付加*/
    private String init(String val) {

        editText = (EditText) findViewById(R.id.text);
        editText.setHeight(12);

        String value;
        value = editText.getText().toString();
        if (value.equals("")) {

            editText.setText(val, EditText.BufferType.NORMAL);
        } else if (!value.equals("")) {
            val = value + val;
            editText.setText(val, EditText.BufferType.NORMAL);
        }

        return null;
    }


    /*電話番号入力情報を取得*/
    private String numberLoad() throws InterruptedException {

        editText = (EditText) findViewById(R.id.text);
        editText.setHeight(12);

        String value;
        value = editText.getText().toString();

        //番号入力チェック
        if (value.equals("")) {
            return null;
        }
        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(), R.raw.bgm_call);
        p.start();
        Thread.sleep(9000);
        this.telPhoneTime(value);
        //this.clearText();
        return null;
    }

    /**
     * 指定した電話番号先を呼び出す
     * @param val1 入力された電話番号1
     * @return
     */
    private String telPhoneTime(String val1) {
        /**入力された電話番号2*/
        String val2 = "";
        /**入力された電話番号3*/
        String val3 = "";

        /** nullチェック*/
        if (val1.equals("")) {
            return null;
        }

        /** ワークテーブルから情報を取得*/
        Cursor rt = wkTbList();
        /** 件数をカウント*/
        int record_count = rt.getCount();

        if(record_count > 0) {
            for (int i = 0; i < record_count; i++) {
                rt.moveToFirst();
                String telNumber = rt.getString(2);
                rt.moveToNext();

                if(record_count == 1){
                    val2 = telNumber;
                }else if(record_count == 2 && val2 != ""){
                    val3 = telNumber;
                }else if(record_count == 2){
                    val2 = telNumber;
                }
            }
        }


        /** countを設定する*/
        if (record_count == 1) {
            count = count + 1;
        } else if (record_count == 2) {
            count = count + 2;
        }

        /** ヘルパーオブジェクト生成 */
        DatabaseConnectHelper helper = new DatabaseConnectHelper(getBaseContext());
        /** ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        StringBuffer sql = new StringBuffer();
        int rowcount = 0;
        try {
            sql.append("select tpl.record_no" +
                    ",tpl.count_number" +
                    ",tpl.tel_number1" +
                    ",tpl.tel_number2" +
                    ",tpl.tel_number3" +
                    ",tpl.sel_number" +
                    ",tpl.message" +
                    ",tpl.end_id" +
                    " from TEL_PHONE_LIST tpl");
            if (count == 1) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
            } else if (count == 2) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
                sql.append(" and tpl.tel_number2 = " + "'" + val2 + "'");
            } else if (count == 3) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
                sql.append(" and tpl.tel_number2 = " + "'" + val2 + "'");
                sql.append(" and tpl.tel_number3 = " + "'" + val3 + "'");
            }
            SQLiteCursor c = (SQLiteCursor) db.rawQuery(sql.toString(), null);
            rowcount = c.getCount();
            c.moveToFirst();

            if(rowcount == 0){
                StringBuffer noSql = new StringBuffer();
                String none = "none";
                noSql.append("select tpl.record_no" +
                        ",tpl.count_number" +
                        ",tpl.tel_number1" +
                        ",tpl.tel_number2" +
                        ",tpl.tel_number3" +
                        ",tpl.sel_number" +
                        ",tpl.message" +
                        ",tpl.end_id" +
                        " from TEL_PHONE_LIST tpl");
                noSql.append(" where tpl.count_number = " + "'" + count + "'");
                noSql.append(" and tpl.tel_number1 = " + "'" + none + "'");
                SQLiteCursor no = (SQLiteCursor) db.rawQuery(noSql.toString(), null);
                no.moveToFirst();

                String end_id = no.getString(7);
                String message = no.getString(6);
                ContentValues cv = new ContentValues();
                cv.put("end_id",end_id);
                cv.put("message",message);
                val1="-";
                InsertDataConstant inst = new InsertDataConstant();
                String wkInsert = inst.getInsertTelWkList(count,val1);
                db.execSQL(wkInsert);

                //電話応答先のセリフを渡す
                Intent intent = new Intent(PlayActivity.this,PlayLinesActivity.class);

                //TODO:toyama test用 後削除
                //message = "endId変数だけ中身がない場合■test■test■test";
                //end_id = "E000";

                // 渡したいデータとキーを指定する
                intent.putExtra("end_id", end_id);
                intent.putExtra("message", message);

                startActivity(intent);
            }
            for (int i = 0; i < rowcount; i++) {

                String end_id = c.getString(7);
                String message = c.getString(6);
                c.moveToNext();
                ContentValues cv = new ContentValues();
                cv.put("end_id",end_id);
                cv.put("message",message);

                InsertDataConstant inst = new InsertDataConstant();
                String wkInsert = inst.getInsertTelWkList(count,val1);

                db.execSQL(wkInsert);

                //電話応答先のセリフを渡す
                Intent intent = new Intent(PlayActivity.this,PlayLinesActivity.class);

                // 渡したいデータとキーを指定する
                intent.putExtra("end_id", end_id);
                intent.putExtra("message", message);
                startActivity(intent);
            }
            if(count == 3){
                this.wkListDel();
            }
        } catch (Exception ex) {
            ex.getStackTrace();
            Log.e("telPhoneTimeエラー", ex.toString());
        }
        return null;
    }

    /**
     * ワークテーブルにレコード削除
     * @return
     */
    private Cursor wkListDel() {

        /** SQL作成*/
        StringBuilder wkSql = new StringBuilder();
        wkSql.append("DELETE FROM TEL_WK_LIST");

        /** ヘルパーオブジェクト生成 */
        DatabaseConnectHelper helper = new DatabaseConnectHelper(getBaseContext());
        /** ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            /** SQL文を実行*/
            Cursor cursor = db.rawQuery(wkSql.toString(), null);
            cursor.moveToFirst();

            return cursor;

        } catch (Exception ex) {
            Log.e("telWklistテーブル削除エラー", ex.toString());
        }

        return null;
    }


    /**
     * ワークテーブルにレコードが存在するか確認
     * @return
     */
    private Cursor wkTbList() {

        /** SQL作成*/
        StringBuilder wkSql = new StringBuilder();
        wkSql.append("SELECT * FROM TEL_WK_LIST");

        /** ヘルパーオブジェクト生成 */
        DatabaseConnectHelper helper = new DatabaseConnectHelper(getBaseContext());
        /** ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            /** SQL文を実行*/
            Cursor cursor = db.rawQuery(wkSql.toString(), null);


            /** 取得件数を返す*/
            return cursor;

        } catch (Exception ex) {
            Log.e("telWklistテーブル情報取得エラー", ex.toString());
        }

        return null;
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
}
