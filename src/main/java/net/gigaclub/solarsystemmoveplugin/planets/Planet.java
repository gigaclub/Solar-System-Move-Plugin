package net.gigaclub.solarsystemmoveplugin.planets;

import com.sk89q.worldedit.extent.clipboard.Clipboard;

public class Planet {

    private Clipboard schematic;
    private double speed;
    private String name;

    public Planet(String name, double speed){
        setName(name);
        setSpeed(speed);
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
}
