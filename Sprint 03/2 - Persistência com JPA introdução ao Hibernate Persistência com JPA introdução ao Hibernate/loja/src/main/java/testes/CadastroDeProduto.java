package testes;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import modelo.Categoria;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

       // List<Produto> todos = produtoDAO.buscarTodos();
        // List<Produto> todos = produtoDAO.buscarPorNome("Xiaomi Redmi");
        List<Produto> todos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println(precoDoProduto);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");


        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares);

        /* Antes de criar a classe JPAUtil para facilitar esse código, eu instanciava o EntityManager dessa forma:

        //Qualquer operação que eu queira fazer com o banco de dados, eu uso o EntityManager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
        EntityManager em = factory.createEntityManager();

        */

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);


        em.getTransaction().begin(); //Inicio a transação

        /*
        em.persist(celulares); //Entrando em estado managed
        celulares.setNome("XPTO"); //Realiza o update

        em.flush(); //Levo pro banco
        em.clear(); //Limpo, deixo em estado Detached

        celulares = em.merge(celulares); //Volto pro estado managed (tenho que atribuir a variavel esse valor pq o estado de merge retorna uma nova referencia)
        celulares.setNome("1234");
        em.flush();
        em.clear();
       em.remove(celulares); //Simulo o delete
        em.flush();
*/


        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        em.getTransaction().commit(); //Commito a transação
        em.close(); //Fecho a transação


    }
}
