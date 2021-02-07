package co.jp.phone.project.Activity;

import android.database.Cursor;
import android.database.DatabaseUtils;
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

import co.jp.phone.project.Constant.DatabeseHelper;
import co.jp.phone.project.Constant.InsertDataConstant;
import co.jp.phone.project.Constant.telNumberConst;
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
    //再生の準備
    MediaPlayer p;
    /**
     * シングルクォート
     */
    private final String sQuote = "'";

    /**
     * ボタン入力情報を設定
     */
    public void on_ac_telButton_Click(View view) {
        if (view.getId() == R.id.ac_bottom) {
            this.clearText();
        } else if (view.getId() == R.id.tel_Bottom) {
            this.numberLoad();
        } else if (view.getId() == R.id.tel_bottom1) {
            this.init(telNumberConst.button1);
        } else if (view.getId() == R.id.tel_bottom2) {
            this.init(telNumberConst.button2);
        } else if (view.getId() == R.id.tel_bottom3) {
            this.init(telNumberConst.button3);
        } else if (view.getId() == R.id.tel_bottom4) {
            this.init(telNumberConst.button4);
        } else if (view.getId() == R.id.tel_bottom5) {
            this.init(telNumberConst.button5);
        } else if (view.getId() == R.id.tel_bottom6) {
            this.init(telNumberConst.button6);
        } else if (view.getId() == R.id.tel_bottom7) {
            this.init(telNumberConst.button7);
        } else if (view.getId() == R.id.tel_bottom8) {
            this.init(telNumberConst.button8);
        } else if (view.getId() == R.id.tel_bottom9) {
            this.init(telNumberConst.button9);
        } else if (view.getId() == R.id.tel_bottom0) {
            this.init(telNumberConst.button0);
        } else if (view.getId() == R.id.tel_bottom_asta) {
            this.init(telNumberConst.button_asta);
        } else if (view.getId() == R.id.tel_bottom_sharp) {
            this.init(telNumberConst.button_sharp);
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
        ImageView iv_coin1 = (ImageView) findViewById(R.id.coin1);
        ImageView iv_coin2 = (ImageView) findViewById(R.id.coin2);
        ImageView iv_coin3 = (ImageView) findViewById(R.id.coin3);

        /** ワークテーブルから情報を取得*/
        int record_count = wkTbList();

        if (record_count == 1) {
            /** １０円玉１枚分を非表示*/
            iv_coin3.setVisibility(View.GONE);
        } else if (record_count == 2) {
            /** １０円玉２枚分を非表示*/
            iv_coin2.setVisibility(View.GONE);
            iv_coin3.setVisibility(View.GONE);
        }
        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(),R.raw.bgm_higurashi);
        //連続再生設定
        p.setLooping(true);
    }

    /**
     * ワークテーブルにレコードが存在するか確認
     *
     * @return
     */
    private int wkTbList() {

        /** SQL作成*/
        StringBuilder wkSql = new StringBuilder();
        wkSql.append("SELECT * FROM TEL_WK_LIST");

        /** rawQueryメソッドでデータを取得*/
        DatabeseHelper dbHelper = new DatabeseHelper(this);
        SQLiteDatabase wk = dbHelper.getReadableDatabase();

        try {
            /** SQL文を実行*/
            Cursor cursor = wk.rawQuery(wkSql.toString(), null);

            /** 件数をカウント*/
            int ct = cursor.getCount();

            /** 取得件数を返す*/
            return ct;

        } catch (Exception ex) {
            Log.e("telWklistテーブル情報取得エラー", ex.toString());
        }

        return 99;
    }


    /*入力番号をEditText蘭に表示
                すでに入力されている情報がある場合は値を付加*/
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
    private String numberLoad() {

        editText = (EditText) findViewById(R.id.text);
        editText.setHeight(12);

        String value;
        value = editText.getText().toString();

        //番号入力チェック
        if (value.equals("")) {
            return null;
        }

        this.telPhoneTime(value);


        this.clearText();
        return null;
    }

    /**
     * 指定した電話番号先を呼び出す
     *
     * @param val1 入力された電話番号1
     * @return
     */
    private String telPhoneTime(String val1) {
        /** nullチェック*/
        if (val1.equals("")) {
            return null;
        }

        /** デフォルトのカウント情報を取得*/
        int count = 1;

        /** ワークテーブルから情報を取得*/
        Integer record_count = wkTbList();

        Cursor dataList = wkDataList();

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
            sql.append("select tpl.end_id ,tpl.message from TEL_PHONE_LIST tpl");
            if (count == 1) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
            } else if (count == 2) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
//            sql.append(" and tpl.tel_number2 = " + "'" + val2 + "'");
            } else if (count == 3) {
                sql.append(" where tpl.count_number = " + "'" + count + "'");
                sql.append(" and tpl.tel_number1 = " + "'" + val1 + "'");
//            sql.append(" and tpl.tel_number2 = " + "'" + val2 + "'");
//            sql.append(" and tpl.tel_number3 = " + "'" + val3 + "'");
            }
            SQLiteCursor c = (SQLiteCursor) db.rawQuery(sql.toString(), null);
            rowcount = c.getCount();

            StringBuffer sb = new StringBuffer();
            String end_id = "";
            for (int i = 0; i < rowcount; i++) {

                if(c.getString(0) != "" || c.getString(0) != null) {
                    end_id = c.getString(0);
                }
                String message = c.getString(1);

                sb.append("[" + end_id + ":" + message + "]");
                c.moveToNext();
            }

            InsertDataConstant dataConstant = new InsertDataConstant();
            dataConstant.getInsertTelWkList(count,val1);

        } catch (Exception ex) {
            Log.e("telPhoneTimeエラー", ex.toString());
        } finally {
            db.close();
            ;
        }

        return null;
    }

    /**
     * ワークテーブルから電話履歴情報を取得する。
     *
     * @return
     */
    private Cursor wkDataList() {

        /** SQL作成*/
        StringBuilder wkSql = new StringBuilder();
        wkSql.append("SELECT * FROM TEL_WK_LIST");

        /** rawQueryメソッドでデータを取得*/
        DatabeseHelper dbHelper = new DatabeseHelper(this);
        SQLiteDatabase wk = dbHelper.getReadableDatabase();

        try {
            /** SQL文を実行*/
            Cursor cursor = wk.rawQuery(wkSql.toString(), null);

//            ArrayList<Cursor> arrayList = new ArrayList<>();
//            arrayList.add(cursor);

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
