package br.com.zup.produto.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "valor", nullable = false)
    private String valor;

    @Deprecated
    public Caracteristica() {}

    public Caracteristica(Long id, String nome, String valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Caracteristica(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caracteristica)) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
