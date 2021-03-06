package net.nunnerycode.bukkit.mythicdrops.loaders;

import com.conventnunnery.libraries.config.ConventConfiguration;
import net.nunnerycode.bukkit.mythicdrops.MythicDrops;
import net.nunnerycode.bukkit.mythicdrops.api.utils.MythicLoader;
import org.bukkit.configuration.file.FileConfiguration;

public class MythicLanguageLoader implements MythicLoader {

    private final MythicDrops plugin;

    public MythicLanguageLoader(final MythicDrops plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        getPlugin().getLanguageManager().getMessages().clear();
		ConventConfiguration c = getPlugin().getLanguageYAML();
		if (c == null) {
			return;
		}
        FileConfiguration configuration = c.getFileConfiguration();
        for (String key : configuration.getKeys(true)) {
            if (configuration.isConfigurationSection(key) || key.equalsIgnoreCase("version")) {
                continue;
            }
            if (configuration.isList(key)) {
                getPlugin().getLanguageManager().getMessages().put(key,
                        configuration.getStringList(key).toString().replace("[", "").replace("]", "").replace(", ",
                                "^"));
            } else if (configuration.isString(key)) {
                getPlugin().getLanguageManager().getMessages().put(key, configuration.getString(key, key));
            }
        }
    }

    public MythicDrops getPlugin() {
        return plugin;
    }
}