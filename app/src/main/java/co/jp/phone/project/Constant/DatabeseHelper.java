package co.jp.phone.project.Constant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// TODO: DatabeseHelperが二つあるため、こちらを後日削除
public class DatabeseHelper extends SQLiteOpenHelper {
    /** データベース名 */
    private static final String DATABASE_NAME = "LoadToSQLiteMaster.db";

    /** データベースバージョン */
    private static final  int DATABESE_VERSION = 1;

    //Create文
    private String CreateStr ="CREATE TABLE ";

    public DatabeseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABESE_VERSION);
    }

    /**
     * 内部にデータベースが存在しないときに１回だけ実行されるメソッド
     * @param db DB接続のオブジェクト
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuffer sb = new StringBuffer();
        /** Createクラスのインスタンス生成 */
        CreateDataConstant create = new CreateDataConstant();

        /** SQL実行(Create文) */
        db.execSQL(create.getCreateTelPhoneMeassgeList());
        db.execSQL(create.getTelwkList());
        db.execSQL(create.getTelEndMeassgeList());
    }

    /**
     * Versionの違いを利用してALTER TABLEなどのDB内部構造を変更する。
     * @param db DB接続オブジェクト
     * @param oldVersion 現在の内部番号　
     * @param newVersion コンストラクタで設定した番号。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }
}

