package xyz.scopped.zenkitpvp.player.statistics;

import java.util.Arrays;

public enum StatisticType {

	KILLS("kills"),
	DEATHS("deaths"),
	KILLSTREAK("killstreak"),
	MAX_KILLSTREAK("max_killstreak"),
	PLAY_TIME("play_time"),
	SESSION_TIME("session_time"),
	;

	private final String column;
	private final boolean persist;

	public static final StatisticType[] PERSIST_VALUES;

	static {
		PERSIST_VALUES = Arrays.stream(values())
				.filter(StatisticType::persist)
				.toArray(StatisticType[]::new);
	}

	StatisticType(String column, boolean persist) {
		this.column = column;
		this.persist = persist;
	}

	StatisticType(String column) {
		this.column = column;
		this.persist = true;
	}

	public String column() {
		return column;
	}

	public boolean persist() {
		return persist;
	}
}
