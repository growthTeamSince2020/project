package co.jp.phone.project.Constant;


/**
 * @author growth
 * @date 2020/10/11.
 */
public class CreateDataConstant{
    //Create文
    private String CreateStr ="CREATE TABLE ";

    //telPhoneMeassgeListテーブル名
    private String telPhoneMeassgeList ="TEL_PHONE_LIST  ";
    //Create 電話番号メッセージリストマスタ
    private String CreateTelPhoneMeassgeList = CreateStr + telPhoneMeassgeList
            + "(record_no INT(4) PRIMARY KEY NOT NULL,"
            + "count_number INT(4) NOT NULL,"
            + "tel_number1 VARCHAR(14),"
            + "tel_number2 VARCHAR(14),"
            + "tel_number3 VARCHAR(14),"
            + "sel_numer VARCHAR(2),"
            + "message VARCHAR(2000) NOT NULL,"
            + "end_id VARCHAR(3)"
            + ");";

    public String getCreateTelPhoneMeassgeList() {
        return CreateTelPhoneMeassgeList;
    }


    //tel_wk_listテーブル名
    private String telwkList ="TEL_WK_LIST ";
    //Create 電話番号メッセージリストマスタ
    private String CreateTelWkList = CreateStr + telwkList
            + "(record_no INT(4) PRIMARY KEY NOT NULL,"
            + "count_number INT(4) NOT NULL,"
            + "tel_number1 VARCHAR(14)"
            + ");";

    public String getTelwkList() { return CreateTelWkList; }


    //telEndMessageListテーブル名
    private String telEndMeassgeList ="TEL_END_MESSAGE_LIST ";
    //Create 電話番号メッセージリストマスタ
    private String CreateEndMeassgeList = CreateStr + telEndMeassgeList
            + "(end_id VARCHAR(3) PRIMARY KEY NOT NULL,"
            + "message VARCHAR(2000) NOT NULL"
            + ");";

    public String getTelEndMeassgeList() { return CreateEndMeassgeList; }
}
