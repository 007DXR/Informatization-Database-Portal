package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
			throws IOException {

		if (bean.first_index == null || (bean.third_index != null && bean.second_index == null)) {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.write("{\"error\":\"指标缺级\"}");
			pw.flush();
		} else {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.write("{\"result\":\"查询成功\"}");
			pw.flush();
		}
		return null;
	}
}
