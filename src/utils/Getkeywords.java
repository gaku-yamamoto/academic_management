package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import models.GooLabsKeywordInPut;
import models.GooLabsKeywordOutPut;
import models.Report;

public class Getkeywords {

    private static String API_URL = "https://labs.goo.ne.jp/api/keyword";

    public static ArrayList<String> getKeywords(Report r){

        ArrayList<String> keywords = new ArrayList<String>();

        try {
            String result = "";
            Gson gson = new Gson();

            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.connect(); // URL接続

            GooLabsKeywordInPut param = new GooLabsKeywordInPut();
            param.setTitle(r.getName());
            param.setBody(r.getSearchdata());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            writer.write(gson.toJson(param));
            System.out.println(gson.toJson(param));
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";

            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }

            System.out.println(result);
            GooLabsKeywordOutPut ks = gson.fromJson(result, GooLabsKeywordOutPut.class);

            for(HashMap<String, String> mp : ks.getKeywords() ){
                Object[] key = mp.keySet().toArray();
                keywords.add((String)key[0]);
            }

            in.close();
            con.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return keywords;

    }

    public static String getkeyword2ArrayStr(ArrayList<String> keywords){
        StringBuffer strb = new StringBuffer();
        for(String str : keywords) strb.append(str + ",");
        return strb.toString().substring(0, strb.toString().length() - 1);

    }

    public static void main(String[] args) {



        String result = "{\"keywords\": [{\"多紀元\": 0.141}, {\"八十一難\": 0.1239}, {\"易経\": 0.1214}, {\"傷寒論\": 0.0928}, {\"Nangyo\": 0.0899}, {\"Kampo Med Vol.70\": 0.0891}, {\"穴\": 0.0811}, {\"Nanjing\": 0.0731}, {\"日東医誌\": 0.0631}, {\"トカゲ\": 0.0626}], \"request_id\": \"labs.goo.ne.jp\t1611231125\t0\"}";
        Gson gson = new Gson();

        GooLabsKeywordOutPut ks = gson.fromJson(result, GooLabsKeywordOutPut.class);
        System.out.println(ks.getRequest_id());

        for(HashMap<String, String> mp : ks.getKeywords() ){
            Object[] key = mp.keySet().toArray();
            System.out.println((String)key[0] +":"+ mp.get((String)key[0]) );
        }
        ArrayList<String> keywords = new ArrayList<String>();
        for(HashMap<String, String> mp : ks.getKeywords() ){
            Object[] key = mp.keySet().toArray();
            keywords.add((String)key[0]);
        }

        Report r = new Report();

        r.setLabels(getkeyword2ArrayStr(keywords));

        System.out.println(r.getLabels());

        for(String str : r.getlabelList()){
            System.out.println(str);
        }


    }

}
