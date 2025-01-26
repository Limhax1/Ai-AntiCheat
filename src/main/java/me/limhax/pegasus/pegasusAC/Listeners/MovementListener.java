package me.limhax.pegasus.pegasusAC.Listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.limhax.pegasus.pegasusAC.AiAntiCheat;
import me.limhax.pegasus.pegasusAC.checks.SimulationEngine;
import me.limhax.pegasus.pegasusAC.data.PlayerData;
import org.bukkit.entity.Player;

public class MovementListener implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING
                || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION
                || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION
                || event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION) {

            Player player = (Player) event.getPlayer();
            PlayerData data = AiAntiCheat.getInstance().getPlayerDataManager().getData(player);
            WrapperPlayClientPlayerFlying flyingPacket = new WrapperPlayClientPlayerFlying(event);

            // Frissítsd a játékos pozícióját
            data.setPosition(flyingPacket.getLocation().getX(), flyingPacket.getLocation().getY(), flyingPacket.getLocation().getZ());
            data.setOnGround(flyingPacket.isOnGround());

            // Szimuláld a mozgást
            SimulationEngine.simulateMovement(data, flyingPacket);
        }
    }
}
