package co.jp.phone.project.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import co.jp.phone.project.Constant.CreateDataConstant;
import co.jp.phone.project.Constant.InsertDataConstant;

/**
 * @author growth
 * @date 2020/10/11.
 */
public class DatabaseConnectHelper extends SQLiteOpenHelper {
    static final public String DBNAME = "telList.sqlite";
    static final public int VERSION = 1;
    static final private CreateDataConstant createData = new CreateDataConstant();
    static final private InsertDataConstant insertData = new InsertDataConstant();

    //コンストラクタ
    public DatabaseConnectHelper (Context context){
        super(context,DBNAME,null,VERSION);
    }

    /**
     * 内部にデータベースが存在しないときに１回だけ実行されるメソッド
     * @param db DB接続のオブジェクト
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        /** SQL実行(Create文) */
        Log.i("onCreate","DatabaseConnectHelper SQL実行");
        Log.i("onCreate","createData.getCreateTelPhoneMeassgeList():"+ createData.getCreateTelPhoneMeassgeList());
        db.execSQL(createData.getCreateTelPhoneMeassgeList());
        db.execSQL(createData.getTelwkList());
        db.execSQL(createData.getTelEndMeassgeList());
        /** SQL実行(Insert文) */
        List<String> insertTelPhoneMeassgeList = insertData.getInsertTelPhoneMeassgeList();
        for(String telPhoneMeassge :insertTelPhoneMeassgeList){
            //TODO 一意エラー時の時のためのログだし 削除後予定
            Log.i("insert",telPhoneMeassge);
            db.execSQL(telPhoneMeassge);
        }
        List<String> insertTelEndMeassgeList = insertData.getInsertTelEndMeassgeList();
        for(String insertTelEndMeassge :insertTelEndMeassgeList){
            db.execSQL(insertTelEndMeassge);
        }
    }
    /**
     * Versionの違いを利用してALTER TABLEなどのDB内部構造を変更する。
     * @param db DB接続オブジェクト
     * @param oldVersion 現在の内部番号　
     * @param newVersion コンストラクタで設定した番号。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}