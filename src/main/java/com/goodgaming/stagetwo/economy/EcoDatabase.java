package com.goodgaming.stagetwo.economy;

import com.goodgaming.stagetwo.sql.ConnectionPool;
import com.goodgaming.stagetwo.sql.Database;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EcoDatabase extends Database {

    private static String CREATE_ECO_TABLE = "CREATE TABLE IF NOT EXISTS `Players` (ID INT AUTO_INCREMENT PRIMARY KEY, Uuid VARCHAR(36) UNIQUE , Balance INT UNSIGNED)";
    private static String CHECK_CONTAIN_UUID = "SELECT Uuid FROM `Players` WHERE Uuid=?";
    private static String INSERT_PLAYER = "INSERT INTO Players(Uuid, Balance) VALUES (?,?)";
    private static String GET_BALANCE = "SELECT Balance FROM `Players` WHERE Uuid=?";
    private static String UPDATE_BALANCE = "UPDATE `Players` SET Balance=? WHERE Uuid=?";

    @Override
    protected void initialize() {
        Connection connection = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ECO_TABLE);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void update() { }


    public Boolean doesConatainPlayer(UUID uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(CHECK_CONTAIN_UUID);
            preparedStatement.setString(1, uuid.toString());
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addPlayer(UUID uuid) {
        if (doesConatainPlayer(uuid)) {
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(INSERT_PLAYER);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer getBalance(UUID uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(GET_BALANCE);
            preparedStatement.setString(1, uuid.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Balance");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addBalance(UUID uuid, Integer amount) {
        if (!doesConatainPlayer(uuid)) {
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
            preparedStatement.setInt(1, getBalance(uuid) + amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            Bukkit.getPluginManager().callEvent(new BalanceUpdateEvent(uuid, getBalance(uuid)));
        }
    }

    public void removeUserSouls(UUID uuid, Integer amount) {
        if (!doesConatainPlayer(uuid)) {
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
            preparedStatement.setInt(1, getBalance(uuid) - amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            Bukkit.getPluginManager().callEvent(new BalanceUpdateEvent(uuid, getBalance(uuid)));
        }
    }

    public void setUserSouls(UUID uuid, Integer souls) {
        if (!doesConatainPlayer(uuid)) {
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.TEST.getConnection();

            preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
            preparedStatement.setInt(1, souls);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            Bukkit.getPluginManager().callEvent(new BalanceUpdateEvent(uuid, getBalance(uuid)));
        }
    }
}
