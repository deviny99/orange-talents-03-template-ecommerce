package br.com.zup.mercadolivre.imagem.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "uri", nullable = false)
    private String uri;

    @Deprecated
    public Imagem(){}

    public Imagem(Long id, @NotBlank String uri) {
        this.id = id;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagem)) return false;
        Imagem imagem = (Imagem) o;
        return Objects.equals(id, imagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
