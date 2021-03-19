package com.domaincheap.crud.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.domaincheap.crud.dominio.Dominio;
import com.domaincheap.crud.repository.DominioRepository;

/** Esta classe armazena os métodos para cadastrar e editar um domínio.*/
@Controller
@RequestMapping("/dominio")
public class CadastroDominioController {

  @Autowired
  private DominioRepository dominioRepository;

  /** Este método é para entrar compraDominio.*/
  @GetMapping("/compraDominio")
  public String entrarCadastroDominio(ModelMap model) {
    model.addAttribute("dominio", new Dominio());
    return "dominio/compraDominio";
  }

  /** Este método é para salvar o domínio no BD.*/
  @PostMapping("/salvar")
  public String salvar(@Valid Dominio dominio, BindingResult result, RedirectAttributes attr) {
    if (result.hasErrors())
      return "dominio/compraDominio";
    dominioRepository.save(dominio);
    attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
    return "redirect:/dominio/compraDominio";
  }

  /** Este método é para editar o domínio.*/
  @GetMapping("/editar/{id}")
  public String iniciarEdicao(@PathVariable("id") Integer idDominio, ModelMap model) {
    model.addAttribute("dominio", dominioRepository.findById(idDominio));
    return "dominio/compraDominio";
  }
}