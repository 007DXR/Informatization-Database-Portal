package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import bean.User;
import framework.GetMapping;
import framework.ModelAndView;

public class IndexController {

	@GetMapping("/")
	public ModelAndView index(HttpSession session)
			throws SQLException {
		User user = (User) session.getAttribute("user");
		String result = mysql.function.inquireRecords();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user", user);
		paramMap.put("rawData", result);

		return new ModelAndView("/index.html", paramMap);
	}

	@GetMapping("/overview")
	public ModelAndView overview(HttpSession session)
		throws SQLException {
			User user = (User) session.getAttribute("user");
			String result = mysql.function.inquireRecords();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("user", user);
			paramMap.put("rawData", result);
			return new ModelAndView("/overview.html", paramMap);
		}

	@GetMapping("/hello")
	public ModelAndView hello(String name) {
		if (name == null) {
			name = "World";
		}
		return new ModelAndView("/hello.html", "name", name);
	}
}
