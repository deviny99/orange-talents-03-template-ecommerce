package br.com.zup.global.config.security;

import br.com.zup.usuario.data.domain.Usuario;
import br.com.zup.usuario.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(username);

        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }

        throw new UsernameNotFoundException("Não foi possivel autenticar");
    }
}