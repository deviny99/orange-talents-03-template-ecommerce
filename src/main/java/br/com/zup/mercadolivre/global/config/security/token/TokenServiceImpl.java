package br.com.zup.mercadolivre.global.config.security.token;

import br.com.zup.mercadolivre.global.config.security.token.interfac.TokenService;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Profile({"prod","test"})
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${mercado-livre.jwt.secret}")
    private String secretKey;
    @Value("${mercado-livre.jwt.expiration}")
    private String expiration;

    @Deprecated
    public TokenServiceImpl(){ }

    public TokenServiceImpl(String secretKey, String expiration){
        this.secretKey = secretKey;
        this.expiration = expiration;
    }

    @Override
    public String gerarToken(Authentication userAutenticado) {

        Usuario usuario  = (Usuario) userAutenticado.getPrincipal();
        Date today = new Date();
        Date dtExpiration = new Date(today.getTime()+Long.parseLong(expiration));

        return tokenBuilder("API do Mercado Livre",
                usuario.getId().toString(),
                secretKey,
                today,
                dtExpiration);

    }

    private String tokenBuilder(String nameAPI, String subject, String secretKey, Date issuadAt, Date dtExpiration){
        return Jwts.builder()
                .setIssuer(nameAPI)
                .setSubject(subject)
                .setIssuedAt(issuadAt)
                .setExpiration(dtExpiration)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    @Override
    public String recuperarETratarToken(String token) {
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }

    @Override
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Long retornarIdDoUsuarioPresenteNoCorpoDoToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}