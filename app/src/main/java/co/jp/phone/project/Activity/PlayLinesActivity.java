package co.jp.phone.project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.ArrayUtils;

import co.jp.phone.project.Constant.CommonConst;
import co.jp.phone.project.Helper.DatabaseConnectHelper;
import co.jp.phone.project.R;



/**
 * * セリフ画面アクションクラス
 * @author growth
 * @date 2020/07/12.
 */
public class PlayLinesActivity extends AppCompatActivity {

    //prologueListカウンター用
    int listCount = 0;
    //改行数のカウンター用
    int rnCount = 0;
    //改行数設定
    int rnSetConut = 4;
    //再生の準備
    MediaPlayer p;
    //表示文字列結合用
    String serif = "";
    //セリフ文字列を代入
    String[] serifList ;
    //共通定義クラス
    CommonConst commonConst= new CommonConst();
    //メッセージ区切り文字定義 取得
    String commonSplitStr = commonConst.getMessageSplit();
    //プロローグのENDID条件
    String conditions = "P000";
    //セリフ
    String message;
    //ENDID
    String end_id;
    //ENDメッセージ
    String[] endMessage;
    String[] endKeyWord = {"END_ME"};

    private ImageView imageView;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tellCountをプレイ画面より受け取り
        //受け取った時　プロローグに遷移
        setContentView(R.layout.activity_playline);
        System.out.println("遷移先：セリフ画面");

        imageView = findViewById(R.id.imageView);

        // インテントを取得
        Intent intent = getIntent();
        // PLAY画面のインテントに保存されたデータを取得
        message = intent.getStringExtra("message");
        end_id = intent.getStringExtra("end_id");

        /* ヘルパーオブジェクト生成 */
        DatabaseConnectHelper helper = new DatabaseConnectHelper(getBaseContext());
        /* ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        //メッセージがない場合
        if(TextUtils.isEmpty(message)){
            //DBからプロローグを取得
            serifList = getEndList(db,conditions);
            //メッセージがある場合
        }else{
            //プレイ画面のメッセージからセリフリスト取得
            serifList = message.split(commonSplitStr, 0);
        }
        //messageとend_idがNullでない場合
        if (!TextUtils.isEmpty(message)&& !TextUtils.isEmpty(end_id)){
            //end_idからエンドリストを取得
            endMessage = getEndList(db,end_id);

            if(endMessage != null){
                endMessage = ArrayUtils.addAll(endKeyWord,endMessage);
                //セリフリストとして配列を結合する。
                serifList = ArrayUtils.addAll(serifList,endMessage);
            }
        }

        //セリフ表示初めの文字列をセットする。
        ((TextView)findViewById(R.id.textView)).setText(serifList[listCount]);
        //表示文字列結合用変数に入れる。
        serif = serifList[listCount];
        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(),R.raw.bgm_higurashi);
        //連続再生設定
        p.setLooping(true);
    }
    //タッチしたら文字変わる
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try{
            if (event.getAction() == MotionEvent.ACTION_DOWN) {// カウントの加算
                listCount++;

                //プロローグを出したら処理を終了
                if (serifList.length == listCount) {

                    //end_idがない場合は、プレイ画面へ
                    if (TextUtils.isEmpty(end_id)) {
                        Intent intentPlay = new Intent(PlayLinesActivity.this, PlayActivity.class);
                        startActivity(intentPlay);
                        //end_idがある場合は、TOP画面へ
                    } else {
                        Intent intentMain = new Intent(this, MainActivity.class);
                        // ActivitySecond と ActivityThird を消す	該当のActivity上にスタックされたタスクをクリアしてから起動
                        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // ActivityFirst を再利用する（onCreate() は呼ばれない）現在のActivityと同じアクティビティは起動しない
                        intentMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        startActivity(intentMain);
                    }
                }

                //serifListのサイズを確認して文字列をセットする。
                if (serifList.length > listCount) {
                    //プロローグの文字をセットする。
                    if (listCount % (rnSetConut + 1) == 0 && !serifList[listCount].equals("END_ME")) {
                        //1行目の場合（または表示クリア後1行目）
                        ((TextView) findViewById(R.id.textView)).setText(serifList[listCount]);
                    } else {
                        //改行数の加算
                        rnCount++;
                        //2行目移行の場合（または表示クリア後2行目移行）
                        if (serif.equals("")) {
                            ((TextView) findViewById(R.id.textView)).setText(serifList[listCount]);
                        } else {
                            ((TextView) findViewById(R.id.textView)).setText(String.format("%s\n%s", serif, serifList[listCount]));
                        }
                    }

                    if (rnCount == rnSetConut) {
                        //5個以上でTEXTVIEWVをクリアする
                        serif = "";
                        //改行数のカウンター用変数をリセット
                        rnCount = 0;

                    } else if (serifList[listCount].equals("END_ME")) {
                        ((TextView) findViewById(R.id.textView)).setText("");
                        //エンドのセリフのためTEXTVIEWVをクリアする
                        serif = "";
                        //改行数のカウンター用変数をリセット
                        rnCount = 0;
                        //エンドのセリフによりrnSetConutにずれが発生するので補正
                        rnSetConut += (listCount % (rnSetConut + 1));

                        //黒い画面から透過度をあげて徐々にもとの背景に戻る。
                        //fadeinXml();//変更したい場合に備えて

                        //元の背景の透過度を上げて徐々に画面を黒くする。
                        fadeoutXml();
                    } else {
                        if (listCount % (rnSetConut + 1) == 0) {
                            //1行目の場合（または表示クリア後1行目）表示プロローグ文字列に格納する。
                            serif = serifList[listCount];
                        } else {
                            //2行目移行の場合（または表示クリア後2行目移行）表示プロローグ文字列に格納する。
                            if (serif.equals("")) {
                                serif = serifList[listCount];
                            } else {
                                serif = serif + "\n" + serifList[listCount];
                            }
                        }
                    }

                }
            }

        }catch (IllegalStateException e){
            throw new IllegalStateException("Unexpected value: " + event.getAction());
        }
        return super.onTouchEvent(event);
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

    //プロローグメッセージ 取得
    public String[] getEndList(SQLiteDatabase db,String selectId) {
        // プロローグメッセージ　SELECT要素の定義
        // SELECT message FROM TEL_END_MESSAGE_LIST WHERE end_id = "P000";
        String table = "TEL_END_MESSAGE_LIST"; //テーブル名
        String[] columns = {"message"}; //取得カラム名
        String selection = "end_id = " + "'" + selectId + "'"; //検索条件

        System.out.println("selection :" + selection);
        System.out.println("プロローグメッセージ取得はじめ");
        Cursor c = db.query(table, columns, selection, null, null, null, null);
        c.moveToFirst();
        CharSequence[] list = new CharSequence[c.getCount()];
        for (int i = 0; i < list.length; i++) {
            list[i] = c.getString(0);
            c.moveToNext();
        }
        c.close();
        String puloStr = (String) list[0];
        String[] puloStrList = puloStr.split(commonSplitStr, 0);
        System.out.println("プロローグメッセージ取得終わり");
        return puloStrList;
    }
    //フィードアウトアニメーション
    private void fadeoutXml(){
        Animation animation= AnimationUtils.loadAnimation(this,
                R.anim.alpha_fadeout);
        imageView.startAnimation(animation);
    }
    //フィードインアニメーション　変更したい場合に備えて
    //private void fadeinXml(){
    //    Animation animation= AnimationUtils.loadAnimation(this,
    //            R.anim.alpha_fadein);
    //    imageView.startAnimation(animation);
    //}

}
