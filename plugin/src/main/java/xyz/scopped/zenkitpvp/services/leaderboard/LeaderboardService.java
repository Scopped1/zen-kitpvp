package xyz.scopped.zenkitpvp.services.leaderboard;

import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.player.statistics.StatisticType;
import xyz.scopped.zenkitpvp.services.leaderboard.model.TopPlayer;
import xyz.scopped.zenkitpvp.utility.generic.Reloadable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardService implements Reloadable {

	private final ZenKitPvP plugin;
	private final Map<StatisticType, List<TopPlayer>> loadedLeaderboards = new HashMap<>();

	public LeaderboardService(ZenKitPvP plugin) {
		this.plugin = plugin;
		plugin.schedulerService().timer(false, this::reload, 3 * 60 * 60 * 20L); // 3 minutes
	}

	@Override
	public void reload() {
		loadedLeaderboards.clear();

		for(StatisticType statisticType : StatisticType.PERSIST_VALUES) {
			loadedLeaderboards.put(statisticType, new ArrayList<>()); //TODO: Async method to take the top 10 players of that statistic
		}
	}

	public Map<StatisticType, List<TopPlayer>> loadedLeaderboards() {
		return loadedLeaderboards;
	}
}
