package co.jp.phone.project.Constant;

public class TelNumberConst {

    // 電話番号＆キー
    public static final String  button1="1";
    public static final String  button2="2";
    public static final String  button3="3";
    public static final String  button4="4";
    public static final String  button5="5";
    public static final String  button6="6";
    public static final String  button7="7";
    public static final String  button8="8";
    public static final String  button9="9";
    public static final String  button0="0";
    public static final String  button_sharp="#";
    public static final String  button_asta="*";



    //シナリオ電話番号定義
    String telPhoneList[] = {"1","110","1"};


    //電話番号定義 取得
    public String[] getTelPhoneList() {
        return telPhoneList;
    }
    //電話番号定義　設定
    public void setTelPhoneList(String[] telPhoneList) {
        this.telPhoneList = telPhoneList;
    }
}
