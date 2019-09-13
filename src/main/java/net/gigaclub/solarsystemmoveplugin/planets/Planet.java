package net.gigaclub.solarsystemmoveplugin.planets;

import net.gigaclub.solarsystemmoveplugin.helper.Schematic;

public class Planet {

    private Schematic schematic;
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

    public Schematic getSchematic() {
        return schematic;
    }

    public void setSchematic(Schematic schematic) {
        this.schematic = schematic;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
