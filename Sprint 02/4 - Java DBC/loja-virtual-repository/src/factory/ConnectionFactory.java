package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("toor123");

        comboPooledDataSource.setMaxPoolSize(15); //Carregue o pool com 15 conexões disponíveis

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexao() throws SQLException {
       return this.dataSource.getConnection();
    }
}
