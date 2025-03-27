package xyz.scopped.zenkitpvp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.config.type.ConfigType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigProvider {

	private final Map<ConfigType, FileConfiguration> loadedConfigurations = new HashMap<>();
	private final ZenKitPvP plugin;

	public ConfigProvider(ZenKitPvP plugin) {
		this.plugin = plugin;

		for (ConfigType value : ConfigType.values()) {
			loadedConfigurations.put(value, load(value));
		}
	}

	public static FileConfiguration load(ZenKitPvP plugin, String name) {
		File file = new File(plugin.folder(), name);

		if (!file.exists()) {
			plugin.bootstrap().saveResource(name, false);
		}

		return YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration find(ConfigType configType) {
		return loadedConfigurations.get(configType);
	}

	public FileConfiguration load(ConfigType configType) {
		return load(plugin, configType.name().toLowerCase() + ".yml");
	}
}