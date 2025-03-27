package xyz.scopped.zenkitpvp.player.loader;

import it.ytnoos.loadit.api.DataLoader;
import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.player.PlayerData;
import xyz.scopped.zenkitpvp.services.database.DatabaseService;

import java.util.Optional;
import java.util.UUID;

public class PlayerDataLoader implements DataLoader<PlayerData> {

	private final ZenKitPvP plugin;

	public PlayerDataLoader(ZenKitPvP plugin) {
		this.plugin = plugin;
	}

	@Override
	public Optional<PlayerData> getOrCreate(UUID uuid, String s) {
		return plugin.databaseService().loadByUuidAndName(uuid, s);
	}

	@Override
	public Optional<PlayerData> load(UUID uuid) {
		return plugin.databaseService().loadByUuid(uuid);
	}

	@Override
	public Optional<PlayerData> load(String s) {
		return plugin.databaseService().loadByName(s);
	}
}
