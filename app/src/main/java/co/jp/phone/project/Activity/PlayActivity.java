package co.jp.phone.project.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import co.jp.phone.project.Constant.telNumberConst;
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


    /*ボタン入力情報を設定*/
    public void on_ac_telButton_Click(View view){
        if(view.getId() == R.id.ac_bottom){
            this.clearText();
        }else if (view.getId() == R.id.tel_Bottom){
            this.numberLoad();
        }else if(view.getId() == R.id.tel_bottom1){
            this.init(telNumberConst.button1);
        }else if(view.getId() == R.id.tel_bottom2){
            this.init(telNumberConst.button2);
        }else if(view.getId() == R.id.tel_bottom3){
            this.init(telNumberConst.button3);
        }else if(view.getId() == R.id.tel_bottom4){
            this.init(telNumberConst.button4);
        }else if(view.getId() == R.id.tel_bottom5){
            this.init(telNumberConst.button5);
        }else if(view.getId() == R.id.tel_bottom6){
            this.init(telNumberConst.button6);
        }else if(view.getId() == R.id.tel_bottom7){
            this.init(telNumberConst.button7);
        }else if(view.getId() == R.id.tel_bottom8){
            this.init(telNumberConst.button8);
        }else if(view.getId() == R.id.tel_bottom9){
            this.init(telNumberConst.button9);
        }else if(view.getId() == R.id.tel_bottom0){
            this.init(telNumberConst.button0);
        }else if(view.getId() == R.id.tel_bottom_asta){
            this.init(telNumberConst.button_asta);
        }else if(view.getId() == R.id.tel_bottom_sharp){
            this.init(telNumberConst.button_sharp);
        }
    }

    /*EditTextクリア処理*/
    private void clearText() {
        EditText editText =(EditText)findViewById(R.id.text);
        String clearTxt = editText.getText().toString();
        clearTxt = "";
        editText.setHeight(12);
        editText.setText(clearTxt, EditText.BufferType.NORMAL);
   
    }


    // BGM準備
    //BGMPlayer bgmPlayer = new BGMPlayer();
    MediaPlayer bgmPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        editText =(EditText)findViewById(R.id.text);
        editText.setTextColor(Color.RED);
        editText.setFocusable(false);
        ImageView iv_coin1 = (ImageView)findViewById(R.id.coin1);
        ImageView iv_coin2 = (ImageView)findViewById(R.id.coin2);
        ImageView iv_coin3 = (ImageView)findViewById(R.id.coin3);
        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        Bitmap bmp3 = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        iv_coin1.setImageBitmap(bmp1);

        bgmPlayer.create(getApplicationContext(),R.raw.bgm_higurashi);

    }


    @Override
    protected void onResume() {
        super.onResume();
        bgmPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgmPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgmPlayer.release();
        bgmPlayer = null;
    }

    /*入力番号をEditText蘭に表示
                すでに入力されている情報がある場合は値を付加*/
    private String init(String val){

        editText =(EditText)findViewById(R.id.text);
        editText.setHeight(12);

        String value;
        value = editText.getText().toString();
        if(value.equals("")){

            editText.setText(val, EditText.BufferType.NORMAL);
        }else if(!value.equals("")){
            val = value + val;
            editText.setText(val, EditText.BufferType.NORMAL);
        }

        return null;
    }


    /*電話番号入力情報を取得*/
    private String numberLoad(){

        editText =(EditText)findViewById(R.id.text);
        editText.setHeight(12);

        String value;
        value = editText.getText().toString();

        //番号入力チェック
        if(value.equals("")){
            return null;
        }

        this.telPhoneTime(value);



        this.clearText();
        return null;
    }

    /**
     * 指定した電話番号先を呼び出す
     * @param val　入力された電話番号
     * @return
     */
    private String telPhoneTime(String val){
        if(val.equals("")){
            return null;
        }

        /** ヘルパーオブジェクト生成 */
        DatabeseHelper helper = new DatabeseHelper(getBaseContext());
        /** ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        StringBuilder sql = new StringBuilder();
       /* sql.append()
        "select * from TEL_PHONE_LIST tpl"
        "where tpl.count_number ="
        "and tpl.tel_number1  ="
        "and tpl.tel_number2 ="
        "and tpl.tel_number3 ="*/


        try {



        }catch (Exception ex){
            Log.e("telPhoneTimeエラー",ex.toString());
        }finally {
            db.close();;
        }

        return null;
    }
}
