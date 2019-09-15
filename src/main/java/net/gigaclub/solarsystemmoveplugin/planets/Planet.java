package net.gigaclub.solarsystemmoveplugin.planets;

import com.sk89q.worldedit.extent.clipboard.Clipboard;

public class Planet {

    private Clipboard schematic;
    private double speed;
    private String name;
    private float x;
    private float y;
    private float z;

    public Planet(String name, double speed, float x, float y, float z){
        setName(name);
        setSpeed(speed);
        setX(x);
        setY(y);
        setZ(z);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clipboard getSchematic() {
        return schematic;
    }

    public void setSchematic(Clipboard schematic) {
        this.schematic = schematic;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
