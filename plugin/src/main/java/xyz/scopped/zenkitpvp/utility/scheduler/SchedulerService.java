package xyz.scopped.zenkitpvp.utility.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import xyz.scopped.zenkitpvp.ZenKitPvP;

import java.util.concurrent.Executor;

public class SchedulerService {

	private final ZenKitPvP plugin;
	private final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

	public final Executor SYNC;
	public final Executor ASYNC;

	public SchedulerService(ZenKitPvP plugin) {
		this.plugin = plugin;
		SYNC = (cmd -> submit(false, cmd));
		ASYNC = (cmd -> submit(true, cmd));
	}

	public BukkitTask later(boolean async, BukkitRunnable runnable, long ticks) {
		return
				async ?
						runnable.runTaskLaterAsynchronously(plugin.bootstrap(), ticks) :
						runnable.runTaskLater(plugin.bootstrap(), ticks);
	}

	public BukkitTask timer(boolean async, BukkitRunnable runnable, long ticks) {
		return timer(async, runnable, ticks, ticks);
	}

	public BukkitTask timer(boolean async, BukkitRunnable runnable, long ticks, long delay) {
		return
				async ?
						runnable.runTaskTimerAsynchronously(plugin.bootstrap(), ticks, delay) :
						runnable.runTaskTimer(plugin.bootstrap(), ticks, delay);
	}

	public BukkitTask submit(boolean async, BukkitRunnable runnable) {
		return
				async ?
						runnable.runTaskAsynchronously(plugin.bootstrap()) :
						runnable.runTask(plugin.bootstrap());
	}

	public BukkitTask later(boolean async, Runnable runnable, long ticks) {
		return
				async ?
						SCHEDULER.runTaskLaterAsynchronously(plugin.bootstrap(), runnable, ticks) :
						SCHEDULER.runTaskLater(plugin.bootstrap(), runnable, ticks);
	}

	public BukkitTask timer(boolean async, Runnable runnable, long ticks) {
		return timer(async, runnable, ticks, ticks);
	}

	public BukkitTask timer(boolean async, Runnable runnable, long ticks, long delay) {
		return
				async ?
						SCHEDULER.runTaskTimerAsynchronously(plugin.bootstrap(), runnable, ticks, delay) :
						SCHEDULER.runTaskTimer(plugin.bootstrap(), runnable, ticks, delay);
	}

	public BukkitTask submit(boolean async, Runnable runnable) {
		return
				async ?
						SCHEDULER.runTaskAsynchronously(plugin.bootstrap(), runnable) :
						SCHEDULER.runTask(plugin.bootstrap(), runnable);
	}

}
