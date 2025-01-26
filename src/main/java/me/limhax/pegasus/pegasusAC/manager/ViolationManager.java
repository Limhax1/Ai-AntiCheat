package me.limhax.pegasus.pegasusAC.manager;

import me.limhax.pegasus.pegasusAC.AiAntiCheat;
import org.bukkit.entity.Player;

public class ViolationManager {

    public void flag(Player player, String checkType) {
        // Példa: Küldj egy üzenetet a játékosnak
        player.sendMessage("§cAiAntiCheat > Gyanús tevékenység észlelve: " + checkType);

        // Naplózáshoz:
        AiAntiCheat.getInstance().getLogger().warning(
                "[Violation] " + player.getName() + " triggered " + checkType
        );
    }
}