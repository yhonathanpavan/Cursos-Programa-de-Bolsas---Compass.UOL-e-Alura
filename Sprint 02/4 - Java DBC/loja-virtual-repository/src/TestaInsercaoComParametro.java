import factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {


        public static void main(String[] args) throws SQLException {

            ConnectionFactory connectionFactory = new ConnectionFactory();
            try(Connection connection = connectionFactory.recuperarConexao()) {

                connection.setAutoCommit(false); //Informo ao JDBC que o commit da transação fica por minha responsabilidade

                try (
                        PreparedStatement pstm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                ) {
                    adicionarVariavel("SmartTV", "45 polegadas", pstm);
                    adicionarVariavel("Radio", "Radio de bateria", pstm);

                    connection.commit(); //Após todos os statements estiverem prontos, commit!

                    pstm.close();


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ROLLBACK EXECUTADO");
                    connection.rollback();
                }
            }



        }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement pstm) throws SQLException {
        pstm.setString(1, nome);
        pstm.setString(2, descricao);

        if(nome.equals("Radio")){
            throw new RuntimeException("Não foi possível adicionar o produto");
        }

        pstm.execute();

        try(ResultSet rst = pstm.getGeneratedKeys()) {

            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }

}



