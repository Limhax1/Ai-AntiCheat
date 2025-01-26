package me.limhax.pegasus.pegasusAC.checks;


import me.limhax.pegasus.pegasusAC.AiAntiCheat;
import me.limhax.pegasus.pegasusAC.data.PlayerData;
import org.joml.Vector3d;

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
        velocity.y -= GRAVITY;
        velocity.mul(DRAG);

        Vector3d newPosition = currentPos.add(velocity);

        // Block collision (egyszerűsítve)
        if (WorldUtil.isChunkLoaded(data.getPlayer().getWorld(), newPosition)) {
            if (WorldUtil.isSolid(data.getPlayer().getWorld(), newPosition)) {
                velocity.y = 0; // Megállítjuk a zuhanást
                newPosition = currentPos;
            }
        }

        return newPosition;
    }
}