package xyz.scopped.zenkitpvp.utility;

public class Strings {

	public static String formatNumber(int number) {
		return formatNumber(number, false);
	}

	public static String formatNumber(int number, boolean lowercase) {
		String[] prefixes = {"", "K", "M", "B", "T", "Q", "Qq", "Sx", "Sp", "Oc", "No", "Dc", "E"};

		if (number < 1000) {
			return String.valueOf(number);
		}

		int exponent = (int) (Math.log10(number) / 3);
		exponent = Math.min(exponent, prefixes.length - 1);

		double value = number / Math.pow(1000, exponent);
		String prefix = prefixes[exponent];

		if (lowercase) {
			prefix = prefix.toLowerCase();
		}

		return String.format("%.1f%s", value, prefix);
	}

	public static String formatTimer(long millis) {
		return String.format("%.1f", millis / 1000.0);
	}

	public static String replace(String message, Object... params) {
		if (params.length % 2 != 0)
			throw new IllegalArgumentException("Parameters should be in key-value pairs.");

		for (int i = 0; i < params.length; i += 2) {
			message = message.replace(params[i].toString(), params[i + 1].toString());
		}

		return message;
	}

	public static String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}

	public static String enumToHumanName(Enum<?> convert) {
		return enumToHumanName(convert.toString());
	}

	public static String enumToHumanName(String convert) {
		String[] parts = convert.split("_");

		if (parts.length == 0) return "";

		StringBuilder human = new StringBuilder(capitalize(parts[0]));

		for (int i = 1; i < parts.length; i++) {
			human.append(" ").append(capitalize(parts[i]));
		}

		return human.toString();
	}

	public static boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
