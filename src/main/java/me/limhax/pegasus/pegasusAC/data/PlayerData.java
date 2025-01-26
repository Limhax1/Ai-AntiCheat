package me.limhax.pegasus.pegasusAC.data;

import com.github.retrooper.packetevents.util.Vector3d; // PacketEvents Vector3d
import org.bukkit.entity.Player;

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
    public void setPosition(Vector3d position) { // Javítva: Vector3d paraméter
        this.lastPosition = this.position;
        this.position = position;
    }
    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    public Vector3d getVelocity() { return velocity; }
    public void setVelocity(Vector3d velocity) { this.velocity = velocity; }
    public long getLastTeleportTime() { return lastTeleportTime; }
    public void setLastTeleportTime(long time) { this.lastTeleportTime = time; }
    public Player getPlayer() { return player; }
}