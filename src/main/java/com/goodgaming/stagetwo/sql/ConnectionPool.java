package com.goodgaming.stagetwo.sql;

import com.goodgaming.stagetwo.StageTwo;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionPool {

    public static final DataSource TEST = openDataSource("TEST", "root", "");

    private static DataSource openDataSource(String db, String username, String password) {
        HikariDataSource hikariDataSource = StageTwo.getInstance().getHikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + db);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setMaximumPoolSize(25);
        hikariDataSource.setMaxLifetime(28740000);
        hikariDataSource.setMinimumIdle(5);
        hikariDataSource.setLeakDetectionThreshold(15000);
        hikariDataSource.setConnectionTimeout(34000);
        hikariDataSource.setIdleTimeout(28740000);
        hikariDataSource.addDataSourceProperty("autoReconnect", "true");

        return hikariDataSource;
    }
}
