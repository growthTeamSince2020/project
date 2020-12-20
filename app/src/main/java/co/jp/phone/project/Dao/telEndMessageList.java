package co.jp.phone.project.Dao;

/**
 * entity定義
 * 論理テーブル名：TEL_END_MESSAGE_LIST
 * 物理テーブル名：エンドメッセージリスト
 */
public class telEndMessageList {

    // 回数
    private int end_id;

    // セリフ
    private String end_message;

    /** 回数を取得 */
    public int getEndid(){
        return this.end_id;
    }
    /** 回数を設定*/
    public void setEndid(int end_id) {
        this.end_id = end_id;
    }

    /** セリフを取得 */
    public String getEndMessage(){
        return this.end_message;
    }
    /** セリフを設定*/
    public void setEndMessage(String end_message) {
        this.end_message = end_message;
    }
}
