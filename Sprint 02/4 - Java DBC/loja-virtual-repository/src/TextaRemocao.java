import factory.ConnectionFactory;

import java.sql.*;

public class TextaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        pstm.setInt(1, 2);
        pstm.execute();

        Integer linhasModificadas = pstm.getUpdateCount();

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

        connection.close();

    }
}
