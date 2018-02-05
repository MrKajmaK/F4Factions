package me.f4dev.f4factions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public final class F4factions extends JavaPlugin {
    public F4factions plugin = this;

    // Configuration file variables
    public FileConfiguration config;

    // MySQL variables
    Connection connection;
    public Statement statement;
    private String host, database, username, password;
    private int port;

    @Override
    public void onEnable() {
        getLogger().info("F4Factions has been enabled.");

        // Configuration file
        saveDefaultConfig();
        getDataFolder().mkdir();
        config = getConfig();

        // MySQL setting variables
        host = config.getString("mysql.host");
        port = config.getInt("mysql.port");
        database = config.getString("mysql.database");
        username = config.getString("mysql.username");
        password = config.getString("mysql.password");

        // MySQL connect
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if(openConnection() && !connection.isClosed()) {
                        getLogger().info("Successfully connected to database.");

                        DatabaseMetaData metaData = connection.getMetaData();

                        // Check if "factions" table exists
                        ResultSet factionsTable = metaData.getTables(null, null, "factions", null);

                        if(!factionsTable.next()) {
                            // Create table if don't exists
                            statement.executeUpdate("CREATE TABLE factions (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, tag VARCHAR(12) NOT NULL UNIQUE, leader VARCHAR(25) NOT NULL UNIQUE, allies JSON NOT NULL, enemies JSON NOT NULL)");
                            getLogger().info("Created table 'factions'");
                        }
                    } else {
                        getLogger().info("Bad connection to database.");
                        getLogger().info("Disabling plugin.");

                        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                    }
                } catch (SQLException | ClassNotFoundException e) {

                }
            }
        };
    }

    @Override
    public void onDisable() {
        getLogger().info("F4Factions has been disabled.");
    }

    private boolean openConnection() throws SQLException, ClassNotFoundException {
        if(connection != null && !connection.isClosed()) {
            return false;
        }

        synchronized (this) {
            if(connection != null && !connection.isClosed()) {
                return false;
            }

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);

            return true;
        }
    }
}
