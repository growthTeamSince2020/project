package co.jp.phone.project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.jp.phone.project.Constant.PrologueEndDefinitionConstant;
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

    //表示文字列結合用
    String prologueStr = "";
    //プロローグエンド定数クラスのインスタンスを生成
    PrologueEndDefinitionConstant prologueEndDefinitionConstant = new PrologueEndDefinitionConstant();
    //プロローグ文字列を代入
    String[] usePrologueList =prologueEndDefinitionConstant.getPrologueList();
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
        //セリフ表示初めの文字列をセットする。
        ((TextView)findViewById(R.id.textView)).setText(usePrologueList[listCount]);
        //表示文字列結合用変数に入れる。
        prologueStr = usePrologueList[listCount];
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
}
