package utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import models.Report;
import models.validators.ReportValidator;

public class ReportUtils {

    private Report r = new Report();
    private Timestamp currentTime = null;

    public ReportUtils () {
        this(new Timestamp(System.currentTimeMillis()));
    }

    public ReportUtils (Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public List<String> setReport( HttpServletRequest request ) throws Exception {

        r.setName("");
        r.setCreated_at(currentTime);
        r.setDelete_flag(0);

        return  setReport(r, request);
    }

    public List<String> setReport( Report r ,HttpServletRequest request ) throws Exception {

        this.r = r;

        // 異なる題名が入力されていたら重複チェックを行う
        Boolean nameDuplicateCheckFlag = !r.getName().equals(request.getParameter("name"));
        System.out.println("nameDuplicateCheckFlag:"+nameDuplicateCheckFlag);

        this.r.setName(request.getParameter("name"));
        this.r.setAuthor(request.getParameter("author"));
        this.r.setYear(request.getParameter("year"));
        this.r.setMagazine(request.getParameter("magazine"));
        this.r.setDepartment(request.getParameter("department"));
        if(request.getPart("avatar") != null) this.r.setFilename(FileUtils.getFileName(request.getPart("avatar")));
        this.r.setUpdated_at(currentTime);

        return ReportValidator.validate(this.r, nameDuplicateCheckFlag);
    }

    public void setSearchdata(String path){
        StringBuffer sd = new StringBuffer();
        sd.append(r.getName() + "" + r.getAuthor() + "" + r.getMagazine() + "" + r.getDepartment() + "" + r.getFilename() + "" + r.getFilename() + " ");
        try {
            sd.append(FileUtils.getFileContents2Str(path));
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
        r.setSearchdata(sd.toString());
        r.setLabels(Getkeywords.getkeyword2ArrayStr(Getkeywords.getKeywords(r)));
    }

    public Report getReport() {
        return r;
    }

}
