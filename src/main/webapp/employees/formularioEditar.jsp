<%@page import="Beans.Employee" %>
<%@ page import="Beans.Job" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="empleado" type="Employee" scope="request"/>
<jsp:useBean id="listaTrabajos" type="java.util.ArrayList<Beans.Job>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>Editar empleado</title>
    </head>
    <body>
        <div class='container'>
            <div class="row mb-4">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Editar usuario</h1>
                    <hr>
                    <form method="POST" action="EmployeeServlet?action=actualizar">
                        <input type="hidden" name="employee_id" value="<%= empleado.getEmployeeId()%>"/>
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control form-control-sm" id="first_name" name="first_name"
                                   value="<%= empleado.getFirstName() == null ? "" : empleado.getFirstName()%>">
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label>
                            <input type="text" class="form-control form-control-sm" id="last_name" name="last_name"
                                   value="<%= empleado.getLastName() == null ? "" : empleado.getLastName()%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control form-control-sm" id="email" name="email"
                                   value="<%= empleado.getEmail() == null ? "" : empleado.getEmail()%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="text" class="form-control form-control-sm" id="phone" name="phone"
                                   value="<%= empleado.getPhoneNumber() == null ? "" : empleado.getPhoneNumber()%>">
                        </div>
                        <div class="form-group">
                            <label for="hire_date">Hire date</label>
                            <input type="text" class="form-control form-control-sm" id="hire_date" name="hire_date"
                                   value="<%= empleado.getHireDate() == null ? "" : empleado.getHireDate()%>">
                        </div>
                        <div class="form-group">
                            <label for="job_id">Job ID</label>
                            <select class="form-control" name="job_id" id="job_id">
                                <% for (Job job : listaTrabajos) { %>

                                <option value="<%=job.getJobId()%>"
                                        <%=empleado.getJob().getJobId().equals(job.getJobId()) ? "selected" : "" %> ><%=job.getJobTitle()%>
                                </option>

                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="salary">Salary</label>
                            <input type="text" class="form-control form-control-sm" id="salary" name="salary"
                                   value="<%= empleado.getSalary().equals("0.0") ? "" : empleado.getSalary()%>">
                        </div>
                        <div class="form-group">
                            <label for="commission">Commision PCT</label>
                            <input type="text" class="form-control form-control-sm" id="commission" name="commission"
                                   value="<%= empleado.getCommissionPct() == null ? "" : empleado.getCommissionPct()%>">
                        </div>
                        <div class="form-group">
                            <label for="manager_id">Manager ID</label>
                            <input type="text" class="form-control form-control-sm" id="manager_id" name="manager_id"
                                   value="<%= empleado.getManagerId() == 0 ? "" : empleado.getManagerId()%>">
                        </div>
                        <div class="form-group">
                            <label for="department_id">Department ID</label>
                            <input type="text" class="form-control form-control-sm" id="department_id"
                                   name="department_id"
                                   value="<%= empleado.getDepartmentId() == 0 ? "" : empleado.getDepartmentId()%>">
                        </div>
                        <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                        <input type="submit" value="Guardar" class="btn btn-primary"/>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
