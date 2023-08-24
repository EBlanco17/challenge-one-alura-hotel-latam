package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionFactory {
    private DataSource dataSource;
    public ConexionFactory() {
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://sql10.freemysqlhosting.net/sql10642101?userTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("sql10642101");
        pooledDataSource.setPassword("PK2lfxSxAc");
        pooledDataSource.setMaxPoolSize(10);

        this.dataSource = pooledDataSource;
    }
    public Connection getConexion(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
