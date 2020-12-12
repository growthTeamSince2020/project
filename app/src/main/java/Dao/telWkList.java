package Dao;

/**
 * entity定義
 * 論理テーブル名：TEL_WK_LIST
 * 物理テーブル名：電話番号リストワークテーブル
 */
public class telWkList {
    // 項目番号
    private int record_no;

    // 回数
    private int count_number;

    // 電話番号１
    private String tel_number1;

    /** 項目番号を取得 */
    public int getRecordNo(){
        return this.record_no;
    }
    /** 項目番号を設定*/
    public void setRecordNo(int record_no) {
        this.record_no = record_no;
    }

    /** 回数を取得 */
    public int getCountNumber(){
        return this.count_number;
    }
    /** 回数を設定*/
    public void setCountNumber(int count_number) {
        this.count_number = count_number;
    }

    /** 電話番号１を取得 */
    public String getTelNumber1(){
        return this.tel_number1;
    }
    /** 電話番号１を設定*/
    public void setTelNumber1(String tel_number1) {
        this.tel_number1 = tel_number1;
    }
}
