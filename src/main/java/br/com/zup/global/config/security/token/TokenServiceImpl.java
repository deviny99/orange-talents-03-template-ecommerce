package br.com.zup.global.config.security.token;

import br.com.zup.global.config.security.token.interfac.TokenService;
import br.com.zup.usuario.data.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${mercado-livre.jwt.secret}")
    private String secretKey;
    @Value("${mercado-livre.jwt.expiration}")
    private String expiration;

    @Override
    public String gerarToken(Authentication userAutenticado) {

        Usuario usuario  = (Usuario) userAutenticado.getPrincipal();
        Date today = new Date();
        Date dtExpiration = new Date(today.getTime()+Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Mercado Livro")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(today)
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