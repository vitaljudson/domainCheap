package com.domaincheap.crud.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Esta classe contém os métodos para entrar em algumas páginas do site. */
@Controller
public class InicioController {

	@GetMapping("/")
	public String inicio() {
		return "Inicio";
	}
	
	@GetMapping("/hospedagem")
	public String hospedagem() {
		return "hospedagem";
	}
	
	@GetMapping("/dominio")
	public String dominio() {
		return "dominio";
	}
	
	@GetMapping("/login")
	public String login() {
	    return "usuario/login";
	}

	@GetMapping("/login-error")
    public String loginError(ModelMap model) {
	    model.addAttribute("msgErro", "Login ou senha incorreto. Tente novamente!");
	    return "usuario/login";
	 }
}
