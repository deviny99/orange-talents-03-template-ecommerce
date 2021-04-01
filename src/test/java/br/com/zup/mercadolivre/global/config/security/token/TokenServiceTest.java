package br.com.zup.mercadolivre.global.config.security.token;

import br.com.zup.mercadolivre.global.config.security.token.interfac.TokenService;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;

@ActiveProfiles("test")
public class TokenServiceTest {

    private TokenService tokenService;

    private static final String SECRET_KEY = "secretTest";
    private static final String EXPIRATION_VALID = "90000";
    private static final String EXPIRATION_INVALID = "0";

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        this.tokenService = new TokenServiceImpl(SECRET_KEY,EXPIRATION_VALID);
    }

    private Usuario getUserAutenticado(){
        return new
                Usuario(1L,"email@email.com","1234",new ArrayList<>());
    }

    private String gerarToken(TokenService tokenService){
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(this.getUserAutenticado(),null);

        String token = tokenService.gerarToken(authenticationToken);
        return token;
    }

    @Test
    void deveGerarToken(){
        String token = this.gerarToken(this.tokenService);
        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }

    @Test
    void tokenValido(){
        boolean retorno = this.tokenService.isTokenValido(this.gerarToken(this.tokenService));
        Assertions.assertTrue(retorno);
    }

    @Test
    void tokenInvalido(){
        TokenService service = new TokenServiceImpl(SECRET_KEY,EXPIRATION_INVALID);
        boolean retorno = this.tokenService.isTokenValido(this.gerarToken(service));
        Assertions.assertFalse(retorno);
    }

    @Test
    void deveRecuperarETratarToken(){
        String tokenTratado = this.tokenService.recuperarETratarToken("Bearer "+this.gerarToken(this.tokenService));
        Assertions.assertNotNull(tokenTratado);
        Assertions.assertFalse(tokenTratado.contains("Bearer "));
    }

    @Test
    void naoDeveRecuperarETratarToken(){
        String tokenTratado = this.tokenService.recuperarETratarToken("Token bem Invalido!");
        Assertions.assertNull(tokenTratado);
    }

    @Test
    void deveRetornarIdDoUsuarioPresenteNoCorpoDoToken(){
        Long idRetornado = this.tokenService.retornarIdDoUsuarioPresenteNoCorpoDoToken(this.gerarToken(this.tokenService));
        Assertions.assertNotNull(idRetornado);
        Assertions.assertEquals(1,idRetornado);
    }

    @Test
    void deveRetornarIdVazioDoUsuarioPresenteNoCorpoDoToken(){
        try{
            Long idRetornado = this.tokenService.retornarIdDoUsuarioPresenteNoCorpoDoToken("Token bem Invalido!");
            Assertions.fail("Não era para chegar nesse ponto.");
        }catch (Exception e){
            Assertions.assertNotNull(e.getMessage());
        }
    }

}
