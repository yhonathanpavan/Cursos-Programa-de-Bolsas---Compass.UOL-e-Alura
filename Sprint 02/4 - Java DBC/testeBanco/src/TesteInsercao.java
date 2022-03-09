import java.sql.*;

public class TesteInsercao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactor connectionFactor = new ConnectionFactor();
        Connection connection = connectionFactor.getConexao();

        String nome = "Reginaldo";
        String endereco = "Rua zika virus";

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO cliente (nome, endereco) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, nome);
        pstm.setString(2, endereco);

        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        while(rs.next()){
            Integer id = rs.getInt(1);
            System.out.println("Adicionado id: " + id);
        }

        connection.close();


    }
}
