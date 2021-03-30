package br.com.zup.usuario.web.controller;

import br.com.zup.usuario.data.domain.NivelAcesso;
import br.com.zup.usuario.data.domain.Roles;
import br.com.zup.usuario.data.domain.Usuario;
import br.com.zup.usuario.data.repository.UsuarioRepository;
import br.com.zup.usuario.web.dto.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest)
    {
        List<NivelAcesso> niveisAcesso = new ArrayList<>();
        niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
        Usuario usuario = this.usuarioRepository.save(usuarioRequest.toModel(null,niveisAcesso));
        return ResponseEntity.ok("");
    }

}
