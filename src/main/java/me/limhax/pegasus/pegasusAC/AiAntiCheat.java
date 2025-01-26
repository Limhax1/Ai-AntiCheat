package me.limhax.pegasus.pegasusAC;

import com.github.retrooper.packetevents.PacketEvents;
import me.limhax.pegasus.pegasusAC.Listeners.MovementListener;
import me.limhax.pegasus.pegasusAC.manager.PlayerDataManager;
import me.limhax.pegasus.pegasusAC.manager.ViolationManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AiAntiCheat extends JavaPlugin {
    private static AiAntiCheat instance;
    private PlayerDataManager playerDataManager;
    private ViolationManager violationManager;

    @Override
    public void onEnable() {
        instance = this;
        playerDataManager = new PlayerDataManager();
        violationManager = new ViolationManager();

        // PacketEvents inicializálása
        PacketEvents.getAPI().init();
        PacketEvents.getAPI().getEventManager().registerListener(new MovementListener());
    }

    public static AiAntiCheat getInstance() { return instance; }
    public PlayerDataManager getPlayerDataManager() { return playerDataManager; }
    public ViolationManager getViolationManager() { return violationManager; }
}