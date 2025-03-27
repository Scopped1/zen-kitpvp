package xyz.scopped.zenkitpvp.player;

import it.ytnoos.loadit.api.UserData;
import org.jetbrains.annotations.NotNull;
import xyz.scopped.zenkitpvp.languages.Languages;
import xyz.scopped.zenkitpvp.player.statistics.UserStatistics;

import java.util.UUID;

public class PlayerData extends UserData {

	private final int id;
	private final UserStatistics statistics;

	private Languages language;

	public PlayerData(int id, @NotNull UUID uuid, @NotNull String name) {
		super(uuid, name);
		this.id = id;
		this.statistics = new UserStatistics();
		this.language = Languages.IT;
	}

	public UserStatistics statistics() {
        return statistics;
    }

	public int id() {
		return id;
	}

	public Languages language() {
		return language;
	}
}
