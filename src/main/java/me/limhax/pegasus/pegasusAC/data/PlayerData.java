package me.limhax.pegasus.pegasusAC.data;

import org.bukkit.entity.Player;
import org.joml.Vector3d;

public class PlayerData {
    private final Player player;
    private Vector3d position;
    private Vector3d lastPosition;
    private Vector3d velocity;
    private boolean onGround;
    private long lastTeleportTime;

    public PlayerData(Player player) {
        this.player = player;
        this.position = new Vector3d(0, 0, 0);
        this.velocity = new Vector3d(0, 0, 0);
    }

    // Getters & Setters
    public Vector3d getPosition() { return position; }
    public void setPosition(double x, double y, double z) {
        this.lastPosition = this.position;
        this.position = new Vector3d(x, y, z);
    }
    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    public Vector3d getVelocity() { return velocity; }
    public void setVelocity(Vector3d velocity) { this.velocity = velocity; }
    public long getLastTeleportTime() { return lastTeleportTime; }
    public void setLastTeleportTime(long time) { this.lastTeleportTime = time; }
}