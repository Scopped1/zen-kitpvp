package xyz.scopped.zenkitpvp.services.database.connectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.config.type.ConfigType;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolManager {

	private final ZenKitPvP plugin;
	private final AuthCredentials credentials;

	private HikariDataSource dataSource;

	public ConnectionPoolManager(ZenKitPvP plugin) {
		this.plugin = plugin;

		FileConfiguration config = plugin.configProvider().find(ConfigType.SETTINGS);
		this.credentials = new AuthCredentials(
				config.getString("database.host"),
                config.getInt("database.port"),
                config.getString("database.database"),
                config.getString("database.username"),
                config.getString("database.password")
		);

		initializePool();
	}

	public void initializePool() {
		HikariConfig config = new HikariConfig();

		config.setJdbcUrl(credentials.jdbc());
		config.setUsername(credentials.username());
		config.setPassword(credentials.password());

		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtCacheSize", 250);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		config.addDataSourceProperty("useUnicode", true);
		config.addDataSourceProperty("useSSL", false);

		config.setPoolName("tridentbox-pool");

		dataSource = new HikariDataSource(config);
	}

	public void stop() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}

	public Connection connection() throws SQLException {
		return dataSource.getConnection();
	}
}
