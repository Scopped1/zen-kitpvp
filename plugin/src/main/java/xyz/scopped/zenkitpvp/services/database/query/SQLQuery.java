package xyz.scopped.zenkitpvp.services.database.query;

public interface SQLQuery {

	String CREATE_PLAYER_TABLE = """
            CREATE TABLE IF NOT EXISTS `kitpvp_players` (
                `id`        INT             PRIMARY KEY AUTO_INCREMENT,
                `uuid`      VARCHAR(36)     NOT NULL,
                `name`      VARCHAR(16)     NOT NULL    UNIQUE
            )
            """;

	String FIND_PLAYER_BY_UUID = """
            SELECT `id`, `name`
            FROM `kitpvp_players`
            WHERE `uuid` = ?
            """;
	
	String FIND_PLAYER_BY_NAME = """
            SELECT `id`, `uuid`
            FROM `kitpvp_players`
            WHERE `name` = ?
            """;
	
}
