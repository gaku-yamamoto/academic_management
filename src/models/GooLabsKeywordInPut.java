package models;

public class GooLabsKeywordInPut {

//  private String app_id = "sdapoiqwvnpoitnptnoiqwt;@opunvwe:sd;lkfj;fbljwktgwioejb;iema";
    private String app_id = "<ここにGooラボのAPIKeyをセットしてください>";
    private String request_id;
    private String title ;
    private String body ;
    private String max_num;
    private String focus;

    public String getApp_id() {
        return app_id;
    }
    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
    public String getRequest_id() {
        return request_id;
    }
    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getMax_num() {
        return max_num;
    }
    public void setMax_num(String max_num) {
        this.max_num = max_num;
    }
    public String getFocus() {
        return focus;
    }
    public void setFocus(String focus) {
        this.focus = focus;
    }
}
