package Controllers;

import Beans.Job;
import Daos.JobDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", urlPatterns = {"/JobServlet"})
public class JobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        JobDao jobDao = new JobDao();
        RequestDispatcher view;

        if (action.equalsIgnoreCase("crear")) {
            String jobId = request.getParameter("jobId");
            String jobTitle = request.getParameter("jobTitle");
            int minSalary = Integer.parseInt(request.getParameter("minSalary"));
            int maxSalary = Integer.parseInt(request.getParameter("maxSalary"));

            Job bJob = jobDao.obtenerTrabajo(jobId);
            if (bJob == null) {
                jobDao.crearTrabajo(jobId, jobTitle, minSalary, maxSalary);
            } else {
                jobDao.actualizarTrabajo(jobId, jobTitle, minSalary, maxSalary);
            }
        }

        response.sendRedirect(request.getContextPath() + "/JobServlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        JobDao jobDao = new JobDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                ArrayList<Job> listaTrabajos = jobDao.obtenerListaTrabajos();
                request.setAttribute("lista", listaTrabajos);
                view = request.getRequestDispatcher("jobs/listaTrabajos.jsp");
                view.forward(request, response);
                break;
            case "formCrear":
                view = request.getRequestDispatcher("jobs/nuevoTrabajo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String jobId = request.getParameter("id");
                Job bJob = jobDao.obtenerTrabajo(jobId);
                if (bJob == null) {
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } else {
                    request.setAttribute("trabajo", bJob);
                    view = request.getRequestDispatcher("jobs/editarTrabajo.jsp");
                    view.forward(request, response);
                }
                break;
            case "borrar":
                String jobID = request.getParameter("id");
                if (jobDao.obtenerTrabajo(jobID) != null) {
                    jobDao.borrarTrabajo(jobID);
                }
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;

        }


    }
}
