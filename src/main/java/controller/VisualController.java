package controller;

// import javax.servlet.http.HttpSession;

// import bean.User;
import framework.GetMapping;
import framework.ModelAndView;

public class VisualController {

	@GetMapping("/visualization")
	public ModelAndView visualization() {
		return new ModelAndView("/project.html");
	}
}
