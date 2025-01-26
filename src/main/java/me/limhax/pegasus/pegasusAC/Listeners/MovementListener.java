package me.limhax.pegasus.pegasusAC.Listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.limhax.pegasus.pegasusAC.AiAntiCheat;
import me.limhax.pegasus.pegasusAC.checks.SimulationEngine;
import me.limhax.pegasus.pegasusAC.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MovementListener extends PacketListenerCommon implements PacketListener {
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING
                || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION
                || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION
                || event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION) {

            Player player = Bukkit.getPlayer(event.getUser().getUUID());
            if (player == null) return;

            PlayerData data = AiAntiCheat.getInstance().getPlayerDataManager().getData(player);
            WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerFlying(event);

            // Csak pozíció frissítése, ha van pozíció adat
            if (wrapper.hasPositionChanged()) {
                Vector3d position = wrapper.getLocation().getPosition();
                data.setPosition(position);
            }
            data.setOnGround(wrapper.isOnGround());

            SimulationEngine.simulateMovement(data, data.getPosition());
        }
    }
}