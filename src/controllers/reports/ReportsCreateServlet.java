package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtil;
import utils.FileUtils;
import utils.ReportUtils;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
@MultipartConfig(location="C:\\pleiades\\workspace\\academic_management\\tmp", maxFileSize=1048576000)
public class ReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String _token = (String)request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                ReportUtils ru = new ReportUtils(currentTime);
                List<String> errors = ru.setReport(request);
                if(errors.size() > 0) {
                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("report", ru.getReport());
                    request.setAttribute("errors", errors);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
                    rd.forward(request, response);
                } else {
                    request.getPart("avatar").write(FileUtils.getFilePath(this.getServletContext(), currentTime));
                    ru.setSearchdata(FileUtils.getFilePath(this.getServletContext(), currentTime));
                    EntityManager em = DBUtil.createEntityManager();
                    em.getTransaction().begin();
                    em.persist(ru.getReport());
                    em.getTransaction().commit();
                    em.close();
                    request.getSession().setAttribute("flush", "登録が完了しました。");
                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}