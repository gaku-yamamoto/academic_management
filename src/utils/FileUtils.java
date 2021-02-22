package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class FileUtils {

    public static String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }

    public static String getFileContents2Str(String path) throws FileNotFoundException, IOException, SAXException, TikaException{

        AutoDetectParser    parser = new AutoDetectParser();
        Metadata            metaData = new Metadata();
        ParseContext        context  = new ParseContext();
        BodyContentHandler  handler = new BodyContentHandler();

        parser.parse(new FileInputStream(new File(path)), handler, metaData, context);

        String ret = handler.toString().replace("\n", "").replace("\r", "");

        return ret;
    }

    public static String getFilePath( ServletContext s, Timestamp t){
        return (String)s.getAttribute("FilesPath") + new SimpleDateFormat("yyyyMMddHHmmss").format(t);
    }

    public static void main(String[] args) {

        String path = "C:\\Users\\knaka\\OneDrive\\デスクトップ\\『難経』の書名についての一考察.pdf";

        try {
            System.out.println(FileUtils.getFileContents2Str(path));
        } catch (IOException | SAXException | TikaException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

    }
}
