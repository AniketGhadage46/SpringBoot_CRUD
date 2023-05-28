package com.crudoperations.CRUDproj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

	@RequestMapping("getemp")
	ArrayList<Employee> getempinfo() throws SQLException {
		Connection con = Config.getconn();
		Statement stmt = con.createStatement();
		String sql = "select * from employee";
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<Employee> al = new ArrayList<>();
		while (rs.next()) {

			int id = rs.getInt(1);
			String name = rs.getString(2);
			String company = rs.getString(3);
			int salary = rs.getInt(4);
			Employee emp = new Employee(id, name, company, salary);
			al.add(emp);

		}
		return al;
	}

	@PostMapping("insert")
	ArrayList<Employee> insertEmp(@RequestParam int id, String name, String company, int salary) throws SQLException {
		Connection con = Config.getconn();
		PreparedStatement ps = con.prepareStatement("insert into employee (id, name, company, salary) values (?,?,?,?)");
		ArrayList<Employee> al = new ArrayList<>();
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, company);
		ps.setDouble(4, salary);
		ps.executeUpdate();

		Employee emp = new Employee(id, name, company, salary);
		al.add(emp);
		return al;
	}

	@RequestMapping("update/{id}/{name}")
	String updateEmp(@PathVariable int id, String name) throws SQLException {
		Connection con = Config.getconn();

		PreparedStatement ps = con.prepareStatement("update employee set name=? where id =?");
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.executeUpdate();

		return "updated name : " + " " + name;

	}

	@RequestMapping("del/{id}")
	String deleteEmp(@PathVariable int id) throws SQLException {
		Connection con = Config.getconn();
		PreparedStatement ps = con.prepareStatement("delete from employee where id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		return "delete data : " + id;
	}

}
