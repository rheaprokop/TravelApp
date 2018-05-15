package lokalspots.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {
	@RequestMapping("/site/service/add")
	public String add() {
		return "site/service/add";
	}
	
	@RequestMapping("/member/service/headline")
	public String memberServiceHeadline() { 
		return "member/service/headline";
	} 
	
	@RequestMapping("/member/service/detail")
	public String memberServiceDetail() { 
		return "member/service/detail";
	} 
	
	@RequestMapping("/member/service/location")
	public String memberServiceLocation() { 
		return "member/service/location";
	} 
	
	@RequestMapping("/member/service/description")
	public String memberServiceDescription() { 
		return "member/service/more-description";
	} 
	
	@RequestMapping("/member/service/photo")
	public String memberServiceUpload() {
		return "member/service/photo";
	}
	
	@RequestMapping("/member/service/notes")
	public String memberServiceNotes() { 
		return "member/service/notes";
	} 
}
