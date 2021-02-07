package co.jp.phone.project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    int tellCount;
    //再生の準備
    MediaPlayer p;
    //表示文字列結合用
    String prologueStr = "";
    //プロローグ文字列を代入
    String[] usePrologueList ;

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tellCountをプレイ画面より受け取り
        //受け取る変数がないとき
        //受け取った時
        //プロローグに遷移
        setContentView(R.layout.activity_playline);
        System.out.println("遷移先：セリフ画面");

        /** ヘルパーオブジェクト生成 */
        DatabaseConnectHelper helper = new DatabaseConnectHelper(getBaseContext());
        /** ヘルパーからDB接続オブジェクトをもらう */
        SQLiteDatabase db = helper.getWritableDatabase();

        String conditions = "P000";
        //セリフ表示初めの文字列を取得
        usePrologueList = getEndList(db,conditions);

        //セリフ表示初めの文字列をセットする。
        ((TextView)findViewById(R.id.textView)).setText(usePrologueList[listCount]);
        //表示文字列結合用変数に入れる。
        prologueStr = usePrologueList[listCount];
        //音楽の読み込み
        p = MediaPlayer.create(getApplicationContext(),R.raw.bgm_higurashi);
        //連続再生設定
        p.setLooping(true);
    }
    //タッチしたら文字変わる
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try{
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // カウントの加算
                listCount++;
                //プロローグを出したら処理を終了
                if(usePrologueList.length == listCount){

                    Intent intent = new Intent(PlayLinesActivity.this, PlayActivity.class);
                    // 渡したいデータとキーを指定する
                    intent.putExtra("tellCount", tellCount);
                    startActivity(intent);
                }
                
                //usePrologueListのサイズを確認して文字列をセットする。
                if(usePrologueList.length>listCount){
                //プロローグの文字をセットする。
                if(listCount%(rnSetConut+1)==0){
                    //1行目の場合（または表示クリア後1行目）
                    ((TextView)findViewById(R.id.textView)).setText(usePrologueList[listCount]);
                }else {
                    //改行数の加算
                    rnCount++;
                    //2行目移行の場合（または表示クリア後2行目移行）
                    ((TextView)findViewById(R.id.textView)).setText(prologueStr + "\n"+ usePrologueList[listCount]);
                }
                    if(rnCount==rnSetConut){
                        //5個以上でTEXTVIEWVをクリアする
                        prologueStr="";
                        //改行数のカウンター用変数をリセット
                        rnCount=0;
                    }else{
                        if(listCount%(rnSetConut+1)==0){
                            //1行目の場合（または表示クリア後1行目）表示プロローグ文字列に格納する。
                            prologueStr = usePrologueList[listCount];
                        }else {
                            //2行目移行の場合（または表示クリア後2行目移行）表示プロローグ文字列に格納する。
                            prologueStr = prologueStr + "\n"+ usePrologueList[listCount];
                        }
                    }
            }
                break;
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
        CommonConst commonConst= new CommonConst();
        String commonSplitStr = commonConst.getMessageSplit();
        String puloStr = (String) list[0];
        String[] puloStrList = puloStr.split(commonSplitStr, 0);
        System.out.println("プロローグメッセージ取得終わり");
        return puloStrList;
    }
}
