import factory.ConnectionFactory;

import java.sql.*;


public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement pstm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO"); //Cláusula SELECT.. etc -> São statements

        //Quando o retorno do statement for uma lista, retorna true
        pstm.execute();

        //Para pegar os resultados da lista
        ResultSet rst = pstm.getResultSet();

        while(rst.next()) { //Enquanto tiver um "próximo"

            Integer id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String descricao = rst.getString("DESCRICAO");
            System.out.println(descricao);

        }



        connection.close();

    }

}
