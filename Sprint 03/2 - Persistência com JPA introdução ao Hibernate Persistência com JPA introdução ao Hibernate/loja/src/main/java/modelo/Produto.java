package modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //Digo que essa classe representa uma tabela
@Table(name = "produtos") //Digo que o nome da tabela tem um nome diferente no dataBase
public class Produto {

    @Id //Digo que esse é o id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    // @Column(name = "desc") ---> Desta forma eu "Ensino" o JPA que essa descrição tem nome diferente na tabela
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();

   //@Enumerated(EnumType.STRING) ---> Usava essa notação quando categoria era um ENUM, para ele pegar a string e não a posição
    @ManyToOne
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
