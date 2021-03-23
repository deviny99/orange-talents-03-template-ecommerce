package br.com.zup.categoria.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoriaSuper;

    public Categoria(Long id, String nome, Categoria categoriaSuper) {
        this.id = id;
        this.nome = nome;
        this.categoriaSuper = (this.isMyself(categoriaSuper))?null:categoriaSuper;
    }

    public Categoria(String nome, Categoria categoriaSuper) {
        this.nome = nome;
        this.categoriaSuper = (this.isMyself(categoriaSuper))?null:categoriaSuper;
    }

    public Categoria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaSuper() {
        return categoriaSuper;
    }

    /*
        verifica se a referencia passada para categoria mãe não é ela mesma.
        precisamos dessa validação quando for fazer um update.
    */
    private boolean isMyself(Categoria categoria){

        if (categoria == null || categoria.equals(this))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}