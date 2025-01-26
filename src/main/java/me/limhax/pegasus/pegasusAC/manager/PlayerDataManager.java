package me.limhax.pegasus.pegasusAC.manager;

import me.limhax.pegasus.pegasusAC.data.PlayerData;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager {
    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData getData(Player player) {
        return playerDataMap.computeIfAbsent(player, k -> new PlayerData(player));
    }

    public void removeData(Player player) {
        playerDataMap.remove(player);
    }
}