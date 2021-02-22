package controllers.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsDownloadServlet
 */
@WebServlet("/reports/download")
public class ReportsDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReportsDownloadServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream out = null;
        InputStream in = null;
        try {

            EntityManager re = DBUtil.createEntityManager();

            Report r = re.find(Report.class, Integer.parseInt(request.getParameter("id")));

            re.close();

            File file = new File("C:\\pleiades\\workspace\\academic_management\\files\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(r.getCreated_at()));
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename="+URLEncoder.encode(r.getFilename(), "UTF-8"));
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff, 0, buff.length)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }
}