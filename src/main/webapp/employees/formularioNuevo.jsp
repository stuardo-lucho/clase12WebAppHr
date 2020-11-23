<%@ page import="Beans.Job" %>
<%@ page import="Beans.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaTrabajos" type="java.util.ArrayList<Beans.Job>" scope="request"/>
<jsp:useBean id="listaJefes" type="java.util.ArrayList<Beans.Employee>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>Nuevo empleado</title>
    </head>
    <body>
        <div class='container'>
            <div class="row mb-4">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Nuevo usuario</h1>
                    <hr>
                    <form method="POST" action="EmployeeServlet?action=guardar">
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control form-control-sm" name="first_name">
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label>
                            <input type="text" class="form-control form-control-sm" name="last_name">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control form-control-sm" name="email">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="text" class="form-control form-control-sm" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="phone">Hire date</label>
                            <input type="text" class="form-control form-control-sm" name="hire_date">
                        </div>
                        <div class="form-group">
                            <label for="job">Job</label>
                            <select class="form-control" name="job_id">
                                <% for (Job job : listaTrabajos) { %>
                                <option value="<%=job.getJobId()%>"><%=job.getJobTitle()%>
                                </option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="salary">Salary</label>
                            <input type="text" class="form-control form-control-sm" name="salary">
                        </div>
                        <div class="form-group">
                            <label for="commission">Commision PCT</label>
                            <input type="text" class="form-control form-control-sm" name="commission">
                        </div>
                        <div class="form-group">
                            <label for="manager_id">Manager</label>
                            <select name="manager_id" class="form-control" id="manager_id">
                                <option value="-1">-- no tiene jefe --</option>
                                <% for (Employee employee : listaJefes) {%>
                                <option value="<%=employee.getEmployeeId()%>"><%=employee.getEmployeeId() + " - " + employee.getFirstName() + " " + employee.getLastName()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="department_id">Department ID</label>
                            <input type="text" class="form-control form-control-sm" name="department_id">
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
