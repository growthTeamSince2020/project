package co.jp.phone.project.Dao;
/**
 * entity定義
 * 論理テーブル名：TEL_PHONE_LIST
 * 物理テーブル名：電話番号リスト
 */

public class telPhoneList {

    // 項目番号
    private int record_no;

    // 回数
    private int count_number;

    // 電話番号１
    private String tel_number1;

    // 電話番号２
    private String tel_number2;

    // 電話番号３
    private String tel_number3;

    // 選択番号
    private String sel_number;

    // セリフ
    private String message;

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

    /** 電話番号２を取得 */
    public String getTelNumber2(){
        return this.tel_number2;
    }
    /** 電話番号２を設定*/
    public void setTelNumber2(String tel_number2) {
        this.tel_number2 = tel_number2;
    }

    /** 電話番号３を取得 */
    public String getTelNumber3(){
        return this.tel_number3;
    }
    /** 電話番号３を設定*/
    public void setTelNumber3(String tel_number3) {
        this.tel_number3 = tel_number3;
    }

    /** 選択番号を取得 */
    public String getSelNumber(){
        return this.sel_number;
    }
    /** 選択番号を設定*/
    public void setSelNumber(String sel_number) {
        this.sel_number = sel_number;
    }

    /** 選択番号を取得 */
    public String getMessage(){
        return this.message;
    }
    /** 選択番号を設定*/
    public void setMessage(String message) {
        this.message = message;
    }

}
