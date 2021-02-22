package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;
import utils.FileUtils;
import utils.ReportUtils;

/**
 * Servlet implementation class ReportsUpdateServlet
 */
@WebServlet("/reports/update")
@MultipartConfig(location="C:\\pleiades\\workspace\\academic_management\\tmp", maxFileSize=1048576000)
public class ReportsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String _token = (String)request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {

                ReportUtils ru = new ReportUtils();

                EntityManager em = DBUtil.createEntityManager();
                List<String> errors = ru.setReport(em.find(Report.class, (Integer)(request.getSession().getAttribute("report_id"))), request);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("report", ru.getReport());
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp");
                    rd.forward(request, response);

                } else {
                    request.getPart("avatar").write(FileUtils.getFilePath(this.getServletContext(), ru.getReport().getCreated_at()));
                    ru.setSearchdata(FileUtils.getFilePath(this.getServletContext(), ru.getReport().getCreated_at()));
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();

                    request.getSession().setAttribute("flush", "更新が完了しました。");
                    request.getSession().removeAttribute("report_id");

                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}