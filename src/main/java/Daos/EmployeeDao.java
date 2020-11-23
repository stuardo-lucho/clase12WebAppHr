package Daos;

import Beans.Employee;
import Beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao extends BaseDao {

    public ArrayList<Employee> listarEmpleados() {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try (Connection conn = getConnectionMysql();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees e inner join jobs j on e.job_id = j.job_id order by employee_id;");) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setPhoneNumber(rs.getString(5));
                employee.setHireDate(rs.getString(6));

                Job job = new Job();
                job.setJobId(rs.getString(7));
                job.setJobTitle(rs.getString("job_title"));
                employee.setJob(job);

                employee.setSalary(rs.getBigDecimal(8));
                employee.setCommissionPct(rs.getBigDecimal(9));
                employee.setManagerId(rs.getInt(10));
                employee.setDepartmentId(rs.getInt(11));

                listaEmpleados.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEmpleados;
    }

    public Employee obtenerEmpleado(int employeeId) {

        Employee employee = null;

        String sql = "SELECT * FROM employees e " +
                "inner join jobs j on e.job_id = j.job_id " +
                "WHERE employee_id = ?";
        try (Connection conn = getConnectionMysql();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setPhoneNumber(rs.getString(5));
                    employee.setHireDate(rs.getString(6));
                    Job job = new Job();
                    job.setJobId(rs.getString(7));
                    job.setJobTitle(rs.getString("job_title"));
                    employee.setJob(job);
                    employee.setSalary(rs.getBigDecimal(8));
                    employee.setCommissionPct(rs.getBigDecimal(9));
                    employee.setManagerId(rs.getInt(10));
                    employee.setDepartmentId(rs.getInt(11));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public void guardarEmpleado(Employee employee) {


        String sql = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnectionMysql();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getHireDate());
            pstmt.setString(6, employee.getJob().getJobId());
            pstmt.setBigDecimal(7, employee.getSalary());
            if (employee.getCommissionPct() == null) {
                pstmt.setNull(8, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(8, employee.getCommissionPct());
            }

            if (employee.getManagerId() == -1) {
                pstmt.setNull(9, Types.INTEGER);
            } else {
                pstmt.setInt(9, employee.getManagerId());
            }

            pstmt.setInt(10, employee.getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarEmpleado(Employee employee) {

        String sql = "UPDATE employees "
                + "SET first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phone_number = ?, "
                + "hire_date = ?, "
                + "job_id = ?, "
                + "salary = ?, "
                + "commission_pct = ?, "
                + "manager_id = ?, "
                + "department_id = ? "
                + "WHERE employee_id = ?";

        try (Connection conn = getConnectionMysql();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getHireDate());
            pstmt.setString(6, employee.getJob().getJobId());
            pstmt.setBigDecimal(7, employee.getSalary());
            if (employee.getCommissionPct() == null) {
                pstmt.setNull(8, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(8, employee.getCommissionPct());
            }
            pstmt.setInt(9, employee.getManagerId());
            pstmt.setInt(10, employee.getDepartmentId());
            pstmt.setInt(11, employee.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarEmpleado(int employeeId) {
        try (Connection conn = getConnectionMysql();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employees WHERE employee_id = ?");) {

            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
