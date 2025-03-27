package xyz.scopped.zenkitpvp.player.statistics;

import java.util.HashMap;
import java.util.Map;

public class UserStatistics {

	private final Map<StatisticType, Long> loadedStatistics = new HashMap<>();
	
	public long kills() {
		return loadedStatistics.get(StatisticType.KILLS);
	}

	public long deaths() {
		return loadedStatistics.get(StatisticType.DEATHS);
	}

	public long killStreak() {
		return loadedStatistics.get(StatisticType.KILLSTREAK);
	}

	public long maxKillStreak() {
		return loadedStatistics.get(StatisticType.MAX_KILLSTREAK);
	}

	public long playTime() {
		return loadedStatistics.get(StatisticType.PLAY_TIME);
	}

	public long sessionTime() {
		return loadedStatistics.get(StatisticType.SESSION_TIME);
	}

	public void statistic(StatisticType statistic, long amount) {
		loadedStatistics.put(statistic, amount);
	}

	public void add(StatisticType statistic, long amount) {
		loadedStatistics.put(statistic, loadedStatistics.get(statistic) + amount);
	}

	public void remove(StatisticType statistic, long amount) {
		loadedStatistics.put(statistic, loadedStatistics.get(statistic) - amount);
	}

	public Map<StatisticType, Long> loadedStatistics() {
		return loadedStatistics;
	}
}
