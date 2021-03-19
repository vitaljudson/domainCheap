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

import com.domaincheap.crud.dominio.Usuario;
import com.domaincheap.crud.repository.UsuarioRepository;

/** Esta classe contém os métodos para buscar e remover um usuário.*/
@Controller
@RequestMapping("/usuarios")
public class BuscaUsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  /** Esta método é para buscar um usuário.*/
  @GetMapping("/busca")
  public String entrarBusca() {
    return "usuario/busca";
  }
  @Transactional
  @GetMapping("/buscar")
  public String buscar(
    @RequestParam(name = "nome", required = false) String nome,
    @RequestParam(name = "mostrarTodosDados", required = false) Boolean mostrarTodosDados,
    HttpSession sessao, ModelMap model) {

    List < Usuario > usuariosEncontrados = usuarioRepository.findByNome(nome);

    model.addAttribute("usuariosEncontrados", usuariosEncontrados);

    if (mostrarTodosDados != null) {
      model.addAttribute("mostrarTodosDados", true);
    }

    return "usuario/busca";

  }

  /** Esta método é para remover um usuário.*/
  @SuppressWarnings("unchecked")
  @GetMapping("/remover/{id}")
  public String remover(
    @PathVariable("id") Integer idUsuario, HttpSession sessao, RedirectAttributes attr
  ) {

    usuarioRepository.deleteById(idUsuario);
    attr.addFlashAttribute("msgSucesso", "Removido com sucesso!");

    return "redirect:/usuarios/buscar";
  }
}