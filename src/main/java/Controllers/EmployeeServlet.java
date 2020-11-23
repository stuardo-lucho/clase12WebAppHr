package Controllers;

import Beans.Employee;
import Beans.Job;
import Daos.EmployeeDao;
import Daos.JobDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        EmployeeDao employeeDao = new EmployeeDao();
        JobDao jobDao = new JobDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaEmpleados", employeeDao.listarEmpleados());
                view = request.getRequestDispatcher("employees/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                request.setAttribute("listaJefes",employeeDao.listarEmpleados());
                view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Employee emp = employeeDao.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                        request.setAttribute("empleado", emp);
                        view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }

                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Employee emp = employeeDao.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        employeeDao.borrarEmpleado(employeeId);
                    }
                }

                response.sendRedirect("EmployeeServlet");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        Employee employee;
        EmployeeDao employeeDao = new EmployeeDao();

        switch (action) {
            case "guardar":
                employee = new Employee();
                employee.setFirstName(request.getParameter("first_name"));
                employee.setLastName(request.getParameter("last_name"));
                employee.setEmail(request.getParameter("email"));
                employee.setPhoneNumber(request.getParameter("phone"));
                employee.setHireDate(request.getParameter("hire_date"));
                Job job = new Job();
                job.setJobId(request.getParameter("job_id"));
                employee.setJob(job);
                employee.setSalary(new BigDecimal(request.getParameter("salary")));
                employee.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));
                Employee manager = new Employee();
                manager.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
                employee.setManager(manager);
                employee.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
                employeeDao.guardarEmpleado(employee);

                response.sendRedirect("EmployeeServlet");
                break;
            case "actualizar":
                employee = new Employee();
                employee.setEmployeeId(Integer.parseInt(request.getParameter("employee_id"))); //no olvidar que para actualizar se debe enviar el ID
                employee.setFirstName(request.getParameter("first_name"));
                employee.setLastName(request.getParameter("last_name"));
                employee.setEmail(request.getParameter("email"));
                employee.setPhoneNumber(request.getParameter("phone"));
                employee.setHireDate(request.getParameter("hire_date"));
                Job jjob = new Job();
                jjob.setJobId(request.getParameter("job_id"));
                employee.setJob(jjob);
                employee.setSalary(new BigDecimal(request.getParameter("salary")));
                employee.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));
                Employee manager2 = new Employee();
                manager2.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
                employee.setManager(manager2);
                employee.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
                employeeDao.actualizarEmpleado(employee);

                response.sendRedirect("EmployeeServlet?action=lista");

                break;
        }
    }

}
