package com.domaincheap.crud.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.domaincheap.crud.dominio.Arquivo;
import com.domaincheap.crud.dominio.Usuario;
import com.domaincheap.crud.repository.UsuarioRepository;
import com.domaincheap.crud.repository.ArquivoRepository;

/** Esta classe armazena os métodos para cadastrar e editar um usuário.*/
@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ArquivoRepository arquivoRepository;

  /** Este método é para entrar cadastro.*/
  @GetMapping("/cadastro")
  public String entrarCadastro(ModelMap model) {
    model.addAttribute("usuario", new Usuario());

    return "usuario/cadastro";
  }

  /** Este método é para salvar o usuário no BD.*/
  @PostMapping("/salvar")
  @Transactional(readOnly = false)
  public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr,
    @RequestParam("file") MultipartFile arquivo) {

    try {
      if (arquivo != null && !arquivo.isEmpty()) {

        String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());

        Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());

        arquivoRepository.save(arquivoBD);

        if (usuario.getFoto() != null && usuario.getFoto().getId() != null && usuario.getFoto().getId() > 0) {
          arquivoRepository.delete(usuario.getFoto());
        }

        usuario.setFoto(arquivoBD);

      } else {
        usuario.setFoto(null);
      }

      if (result.hasErrors()) {
        return "usuario/cadastro";
      }

      String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
      usuario.setSenha(senhaCriptografada);

      usuarioRepository.save(usuario);
      attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/usuarios/cadastro";
  }

  /** Este método é para editar o usuário.*/

  @GetMapping("/editar/{id}")
  public String iniciarEdicao(@PathVariable("id") Integer idUsuario, ModelMap model) {
    model.addAttribute("usuario", usuarioRepository.findById(idUsuario));
    return "usuario/cadastro";
  }

}