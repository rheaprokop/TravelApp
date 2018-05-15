package lokalspots.auth.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lokalspots.auth.models.User;
import lokalspots.auth.services.SecurityService;
import lokalspots.auth.services.UserService;
import lokalspots.auth.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService; 

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		String userPwd = userForm.getPassword();
		if (bindingResult.hasErrors()) {
			return "registration";
		} 
		userForm.setUsername(userForm.getEmailAddress());
		userService.saveMember(userForm);
		  if (!securityService.autologin(userForm.getUsername(), userPwd)) { 
	        	return "redirect:/member/dashboard";
	        } else
	        {
	        	return "redirect:/";
	        }
		  
	}
	
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "site/index";
	    }
    
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginPage(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//    	String userPwd = userForm.getPassword();
//
//        if (!securityService.autologin(userForm.getUsername(), userPwd)) { 
//        	return "redirect:/member/dashboard";
//        }
//        System.out.println("test2");
//        return "site/index";
//    }
}
