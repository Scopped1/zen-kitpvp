package xyz.scopped.zenkitpvp.services.database;

import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.player.PlayerData;
import xyz.scopped.zenkitpvp.services.database.connectors.AuthCredentials;
import xyz.scopped.zenkitpvp.services.database.connectors.ConnectionPoolManager;
import xyz.scopped.zenkitpvp.services.database.query.SQLQuery;

import java.util.Optional;
import java.util.UUID;

public class DatabaseService implements SQLQuery {

	private final ZenKitPvP plugin;

	private final ConnectionPoolManager connectionPoolManager;

	public DatabaseService(ZenKitPvP plugin) {
		this.plugin = plugin;
		this.connectionPoolManager = new ConnectionPoolManager(plugin);
	}

	public Optional<PlayerData> loadByUuid(UUID uuid) {
		return Optional.empty();
	}

	public Optional<PlayerData> loadByName(String name) {
		return Optional.empty();
	}

	public Optional<PlayerData> loadByUuidAndName(UUID uuid, String name) {
		return Optional.empty();
	}

	public void stop() {
		connectionPoolManager.stop();
	}

}
