package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public void atualizar(Produto produto){
        this.em.merge(produto);
    }

    public void remover(Produto produto){
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList(); //O createQuery não dispara no banco, so monta, pra disparar é preciso o getResultList
    }                              //Tipo da classe que vai ser devolvida nessa query ---> vai devolver uma lista de produto.


    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; //:nome é um parametro dinamico pra query //named parameter //Para parametro posicional, seria ?1, ?2, ?3... e no set seria (1, nome)
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList(); //Seto o parâmetro acima, com o nome do parametro e o valor
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) { //Pegando um valor específico (preco) do banco, sem selecionar todos os dados
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult(); //Método que carrega um unico resultado, não uma lista
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1"; //A JPA entenderá que a categoria é um atributo da classe produto e, neste caso, um relacionamento. Então, ele quer filtrar por um atributo dentro do relacionamento, desta maneira, a JPA automaticamente gerará um join, isto é, ela já sabe que deve filtrar pelo relacionamento e faz o join automaticamente, evitando que seja necessário fazer manualmente, como seria no SQL.
        return em.createQuery(jpql, Produto.class).setParameter(1, nome).getResultList(); //Seto o parâmetro acima, com o nome do parametro e o valor
    }
}
