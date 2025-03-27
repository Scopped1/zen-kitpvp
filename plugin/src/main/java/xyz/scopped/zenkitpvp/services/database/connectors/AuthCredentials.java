package xyz.scopped.zenkitpvp.services.database.connectors;

public record AuthCredentials(
		String host,
		int port,
		String database,
        String username,
        String password
) {

	public String jdbc() {
		return "jdbc:mysql://" + host + ":" + port + "/" + database;
	}
}
