package br.com.zup.mercadolivre.global.config.security.token;

import br.com.zup.mercadolivre.global.config.security.token.interfac.AutenticacaoTokenFilter;
import br.com.zup.mercadolivre.global.config.security.token.interfac.TokenService;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import br.com.zup.mercadolivre.usuario.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Profile({"prod","test"})
@Service
public class AutenticacaoTokenFilterImpl extends OncePerRequestFilter implements AutenticacaoTokenFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoTokenFilterImpl(TokenService tokenService,UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public void autenticarUsuario(String token) {
        Long idUsuario = this.tokenService.retornarIdDoUsuarioPresenteNoCorpoDoToken(token);
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isPresent()){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuarioOptional.get(),
                            null, usuarioOptional.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenTratado = this.tokenService.recuperarETratarToken(httpServletRequest.getHeader("Authorization"));
        if(this.tokenService.isTokenValido(tokenTratado)){
            this.autenticarUsuario(tokenTratado);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
