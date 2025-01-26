package me.limhax.pegasus.pegasusAC.checks;

import com.github.retrooper.packetevents.util.Vector3d; // PacketEvents Vector3d
import me.limhax.pegasus.pegasusAC.AiAntiCheat;
import me.limhax.pegasus.pegasusAC.data.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class SimulationEngine {

    private static final double GRAVITY = 0.08;
    private static final double DRAG = 0.98;
    private static final double MOVEMENT_THRESHOLD = 0.001;

    public static void simulateMovement(PlayerData data, Vector3d receivedPosition) {
        Vector3d predictedPosition = calculatePredictedPosition(data);
        double discrepancy = predictedPosition.distance(receivedPosition);

        if (discrepancy > MOVEMENT_THRESHOLD) {
            AiAntiCheat.getInstance().getViolationManager().flag(data.getPlayer(), "INVALID_MOVEMENT");
        }
    }

    private static Vector3d calculatePredictedPosition(PlayerData data) {
        Vector3d currentPos = data.getPosition();
        Vector3d velocity = data.getVelocity();

        // Fizikai szimuláció
        velocity = new Vector3d(
                velocity.getX() * DRAG,
                (velocity.getY() - GRAVITY) * DRAG,
                velocity.getZ() * DRAG
        );

        Vector3d newPosition = currentPos.add(velocity);

        // Block collision (Bukkit API-val)
        World world = data.getPlayer().getWorld();
        int blockX = (int) Math.floor(newPosition.getX());
        int blockY = (int) Math.floor(newPosition.getY());
        int blockZ = (int) Math.floor(newPosition.getZ());

        if (world.isChunkLoaded(blockX >> 4, blockZ >> 4)) {
            Material blockType = new Location(world, blockX, blockY, blockZ).getBlock().getType();
            if (blockType.isSolid()) {
                velocity = new Vector3d(0, 0, 0); // Reset velocity
                newPosition = currentPos;
            }
        }

        data.setVelocity(velocity);
        return newPosition;
    }
}