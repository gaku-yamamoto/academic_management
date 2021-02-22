package models;

import java.util.ArrayList;
import java.util.HashMap;

public class GooLabsKeywordOutPut {

    private String request_id;
    private String focus;
    private ArrayList<HashMap<String, String>> keywords;

    public String getRequest_id() {
        return request_id;
    }
    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
    public String getFocus() {
        return focus;
    }
    public void setFocus(String focus) {
        this.focus = focus;
    }
    public ArrayList<HashMap<String, String>> getKeywords() {
        return keywords;
    }
    public void setKeywords(ArrayList<HashMap<String, String>> keywords) {
        this.keywords = keywords;
    }

}
