package lokalspots.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
	@RequestMapping("/")
    public String index() {
        return "site/index";
    }
	
	@RequestMapping("/explore")
    public String explore(Model model) {
		
        return "site/explore";
    }
	
	@RequestMapping("/property")
    public String property() {
        return "site/property";
    }
	
	@RequestMapping("/experience")
    public String experience() {
        return "site/experience";
    }
}
