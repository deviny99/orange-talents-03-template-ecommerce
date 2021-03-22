package br.com.zup.usuario.data.domain;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "niveis_acesso")
public class NivelAcesso implements GrantedAuthority, Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Roles role;


    public NivelAcesso(Roles role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.toString();
    }


    public Roles getRole(){
        return this.role;
    }
}
