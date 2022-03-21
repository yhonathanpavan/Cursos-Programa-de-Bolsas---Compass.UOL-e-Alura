package dao;

import modelo.Categoria;
import modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection){
        this.connection = connection;
    }



    public List<Categoria> listar() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a query de listar categoria");

        String sql = "SELECT * FROM CATEGORIA";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.execute();

            try(ResultSet rs = ps.getResultSet()){
                while(rs.next()) {
                    Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
                    categorias.add(categoria);
                }
            }

        }

        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException {
        Categoria ultima = null;

        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a query de listar categoria");

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.execute();

            try(ResultSet rs = ps.getResultSet()){
                while(rs.next()) {
                    if(ultima == null || !ultima.getNome().equals(rs.getString(2))){

                        Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));

                        ultima = categoria;

                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(rs.getInt(3), rs.getString(4), rs.getString(5));
                    ultima.adicionar(produto);
                }
            }

        }

        return categorias;
    }
}
