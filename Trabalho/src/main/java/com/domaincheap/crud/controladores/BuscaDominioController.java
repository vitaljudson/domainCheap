package com.domaincheap.crud.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.domaincheap.crud.dominio.Dominio;
import com.domaincheap.crud.repository.DominioRepository;

/** Esta classe contém os métodos para buscar e remover um domínio.*/
@Controller
@RequestMapping("/dominio")
public class BuscaDominioController {

  @Autowired
  private DominioRepository dominioRepository;

  /** Esta método é para entrar na página buscaDominio.*/
  @GetMapping("/buscaDominio")
  public String entrarBusca() {
    return "dominio/buscaDominio";
  }

  /** Esta método é para buscar um domínio.*/
  @Transactional
  @GetMapping("/buscar")
  public String buscar(@RequestParam(name = "nome", required = false) String nome, ModelMap model) {
    List < Dominio > dominiosEncontrados = dominioRepository.findByNome(nome);
    model.addAttribute("dominiosEncontrados", dominiosEncontrados);
    return "dominio/buscaDominio";
  }

  /** Esta método é para remover um domínio.*/
  @GetMapping("/remover/{id}")
  public String remover(@PathVariable("id") Integer idDominio, RedirectAttributes attr) {
    dominioRepository.deleteById(idDominio);
    return "redirect:/dominio/buscar";
  }
}