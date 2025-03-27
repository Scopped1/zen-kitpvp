package xyz.scopped.zenkitpvp;

import it.ytnoos.loadit.Loadit;
import it.ytnoos.loadit.api.DataContainer;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;
import xyz.scopped.zenkitpvp.config.ConfigProvider;
import xyz.scopped.zenkitpvp.config.type.ConfigType;
import xyz.scopped.zenkitpvp.message.MessageProvider;
import xyz.scopped.zenkitpvp.player.PlayerData;
import xyz.scopped.zenkitpvp.player.loader.PlayerDataLoader;
import xyz.scopped.zenkitpvp.services.database.DatabaseService;
import xyz.scopped.zenkitpvp.utility.scheduler.SchedulerService;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ZenKitPvP {

	private final ZenKitPvPBootstrap bootstrap;
	private final BukkitAudiences adventure;

	public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

	private final SchedulerService schedulerService;

	private final ConfigProvider configProvider;
	private final MessageProvider messageProvider;

	private final DatabaseService databaseService;
	private final Loadit<PlayerData> loadit;
	private final DataContainer<PlayerData> container;

	public ZenKitPvP(ZenKitPvPBootstrap bootstrap) {
		this.bootstrap = bootstrap;
		this.adventure = BukkitAudiences.create(bootstrap);

		this.configProvider = new ConfigProvider(this);
		this.messageProvider = new MessageProvider(this);

		this.schedulerService = new SchedulerService(this);
		this.databaseService = new DatabaseService(this);

		this.loadit = Loadit.createInstance(bootstrap, new PlayerDataLoader(this));
		this.loadit.init();

		this.container = loadit.getContainer();
	}

	public void disable() {
		loadit.stop();
        databaseService.stop();
	}

	public ConfigProvider configProvider() {
		return configProvider;
	}

	public MessageProvider messageProvider() {
		return messageProvider;
	}

	public File folder() {
		return bootstrap.getDataFolder();
	}

	public Plugin bootstrap() {
		return bootstrap;
	}

	public DatabaseService databaseService() {
		return databaseService;
	}

	public DataContainer<PlayerData> container() {
		return container;
	}

	public SchedulerService schedulerService() {
		return schedulerService;
	}

	public <T>CompletableFuture<T> async(Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, EXECUTOR);
	}

	public BukkitAudiences adventure() {
		return adventure;
	}
}
