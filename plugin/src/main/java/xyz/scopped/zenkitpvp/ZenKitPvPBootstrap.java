package xyz.scopped.zenkitpvp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ZenKitPvPBootstrap extends JavaPlugin {

    private ZenKitPvP plugin;

    @Override
    public void onEnable() {
        try {
            this.plugin = new ZenKitPvP(this);
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Failed to enable ZenKitPvP!", ex);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if(this.plugin != null) this.plugin.disable();
    }
}
