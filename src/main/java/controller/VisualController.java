package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bean.VisualInfoBean;
import framework.GetMapping;
import framework.ModelAndView;
import framework.PostMapping;

public class VisualController {

	@GetMapping("/visual")
	public ModelAndView visualization(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return new ModelAndView("/project.html", "user", user);
	}

	@PostMapping("/visual/inquiry")
	public ModelAndView doInquiry(VisualInfoBean bean, HttpServletResponse response, HttpSession session)
			throws IOException, SQLException {
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		if (bean.first_index == "" && bean.country_name == "" && bean.year == "") {
			pw.write("{\"error\":\"Query Demand not Complete\"}");
		} 
		else {
			// TODO：前端已经杜绝了上位概念有，而下位概念缺失的情况，暂不处理
			String result = mysql.function.inquireRecords(bean.first_index, bean.second_index, bean.third_index, bean.country_name, bean.year);
			pw.write(String.format("{\"result\":\"Success!\", \"data\":%s}", result));
		}
		pw.flush();
		return null;
	}

	@PostMapping("/visual/update")
	public ModelAndView doUpdate(VisualInfoBean bean, HttpServletResponse response, HttpSession session)
			throws IOException, SQLException {
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		if (bean.country_name == "" && bean.year == "" && bean.data_value == -1) {
			pw.write("{\"error\":\"Index not Complete\"}");
		} else if (bean.record_id < 0) {
			pw.write("{\"error\":\"Record ID Illegal\"}");
		} else {
			response.setContentType("application/json");
			mysql.function.modifyRecord(bean.record_id, bean.country_name, bean.year, bean.data_value);
			pw.write("{\"result\":\"Success!\"}");
		}
		pw.flush();
		return null;
	}

	@PostMapping("/visual/add")
	public ModelAndView doAdd(VisualInfoBean bean, HttpServletResponse response, HttpSession session)
			throws IOException, SQLException {
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		if (bean.first_index == "" || bean.second_index == "" || bean.third_index == "" || bean.country_name == ""
				|| bean.year == "" || bean.data_value == -1) {
			pw.write("\"error\":\"Index not Complete\"");
		} else {
			response.setContentType("application/json");
			String result = mysql.function.addRecord(bean.first_index, bean.second_index, bean.third_index, bean.record_id,
					bean.data_value, bean.country_name, bean.year);
				pw.write(String.format("{\"result\":\"Success!\", \"data\":%s}", result));
			}
		return null;
	}

	@PostMapping("/visual/delete")
	public ModelAndView doDelete(VisualInfoBean bean, HttpServletResponse response, HttpSession session)
		throws IOException, SQLException {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			if (bean.record_id != -1) {
				response.setContentType("application/json");
				mysql.function.deleteRecord(bean.record_id);
				pw.write("{\"result\":\"Success!\"}");
				System.out.println(bean.record_id);
			} else if (bean.first_index == "" && bean.country_name == "" && bean.year == "") {
				pw.write("\"error\":\"Index not Complete\"");
			} else {
				response.setContentType("application/json");
				mysql.function.deleteRecords(bean.first_index, bean.second_index, bean.third_index, bean.country_name, bean.year);
				pw.write("{\"result\":\"Success!\"}");
			}
			return null;
		}

	@GetMapping("/visual/refresh")
	public ModelAndView doRefresh(HttpServletResponse response, HttpSession session)
		throws IOException, SQLException {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			String result = mysql.function.inquireRecords();
			pw.write(String.format("\"data\":%s", result));
			pw.flush();
			return null;
		}

}
