package br.com.zup.usuario.web.controller;

import br.com.zup.global.config.security.token.interfac.TokenService;
import br.com.zup.usuario.web.dto.request.LoginFormRequest;
import br.com.zup.usuario.web.dto.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static br.com.zup.global.web.exception.ControllerException.forbidden;

@Profile({"prod","test"})
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager,TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginFormRequest loginForm){

        try {
            UsernamePasswordAuthenticationToken dadosLogin = loginForm.convertToUserAuthentication();
            Authentication authentication = this.authenticationManager.authenticate(dadosLogin);
            String tokenGerado = this.tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new JwtResponse("Bearer",tokenGerado));

        }catch (AuthenticationException e)
        {
            throw forbidden("Acesso negado");
        }
    }

}