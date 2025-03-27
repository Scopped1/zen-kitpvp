package xyz.scopped.zenkitpvp.message;

import it.ytnoos.loadit.api.UserData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.scopped.zenkitpvp.ZenKitPvP;
import xyz.scopped.zenkitpvp.config.ConfigProvider;
import xyz.scopped.zenkitpvp.languages.Languages;
import xyz.scopped.zenkitpvp.player.PlayerData;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MessageProvider {

	public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
	public static final Languages DEFAULT_Languages = Languages.IT;

	private final Map<Languages, Map<String, String>> loadedLanguages = new HashMap<>();
	private final ZenKitPvP plugin;

	public MessageProvider(ZenKitPvP plugin) {
		this.plugin = plugin;
	}

	public void registerLanguages(Languages Languages, String directory) {
		var map = new HashMap<String, String>();
		loadedLanguages.put(Languages, map);

		FileConfiguration config = ConfigProvider.load(plugin, directory);

		for (var key : config.getKeys(true)) {
			map.put(key, config.getString(key));
		}
	}

	public Languages langFromPlayer(Player player) {
		PlayerData data = plugin.container().getCached(player);
		return data == null ? fromSPigot(player.spigot().getLocale()) : data.language();
	}

	public Languages fromSPigot(String locale) {
		return switch (locale) {
			case "it_IT" -> Languages.IT;
			default -> Languages.EN;
		};
	}

	public String getMessage(Languages Languages, String id) {
		var messages = loadedLanguages.get(Languages);
		return messages.getOrDefault(id, "Missing " + id);
	}

	public String getMessage(Player player, String id) {
		Languages Languages = langFromPlayer(player);
		var messages = loadedLanguages.get(Languages);
		return messages.getOrDefault(id, "Missing " + id);
	}

	public Component translate(String message) {
		return MINI_MESSAGE.deserialize(message);
	}

	public void console(String id, Map<String, String> replacements) {
		String message = getMessage(DEFAULT_Languages, id);

		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			message = message.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
		}

		plugin.adventure().console().sendMessage(translate(message));
	}

	public void console(Component component) {
		plugin.adventure().console().sendMessage(component);
	}

	public void sendMessage(String id, CommandSender sender, Map<String, String> replacements) {
		String message = getMessage(DEFAULT_Languages, id);

		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			message = message.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
		}

		plugin.adventure().sender(sender).sendMessage(translate(message));
	}

	public void sendMessage(String id, Player player, Map<String, String> replacements) {
		String message = getMessage(player, id);

		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			message = message.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
		}

		plugin.adventure().player(player).sendMessage(translate(message));
	}

	public void sendMessage(String id, Player player) {
		String message = getMessage(player, id);

		plugin.adventure().player(player).sendMessage(translate(message));
	}

	public void sendMessage(Component component, Player player) {
		plugin.adventure().player(player).sendMessage(component);
	}

	public void sendActionbar(String id, Player player, Map<String, String> replacements) {
		String message = getMessage(player, id);

		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			message = message.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
		}

		plugin.adventure().player(player).sendActionBar(translate(message));
	}

	public void sendActionbar(Component component, Player player) {
		plugin.adventure().player(player).sendActionBar(component);
	}

	public void sendTitle(String title, String subTitle, Player player, Map<String, String> replacements) {
		String configTitle = getMessage(player, title);
		String configSubTitle = getMessage(player, subTitle);

		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			configTitle = configTitle.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
			configSubTitle = configSubTitle.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
		}

		var adventureTitle = Title.title(
				translate(title),
				translate(subTitle),
				Title.Times.times(
						Duration.ofMillis(2500),
						Duration.ofMillis(3000),
						Duration.ofMillis(2500)
				)
		);

		plugin.adventure().player(player).showTitle(adventureTitle);
	}

	public void sendTitle(Component title, Component subTitle, Player player) {
		var adventureTitle = Title.title(
				title,
				subTitle,
				Title.Times.times(
						Duration.ofMillis(2500),
						Duration.ofMillis(3000),
						Duration.ofMillis(2500)
				)
		);

		plugin.adventure().player(player).showTitle(adventureTitle);
	}

	public void broadcast(String id, Map<String, String> replacements) {
		console(id, replacements);

		for (Player online : Bukkit.getOnlinePlayers()) {
			sendMessage(id, online, replacements);
		}
	}

}
