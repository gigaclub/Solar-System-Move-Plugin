package net.gigaclub.solarsystemmoveplugin;

import net.gigaclub.solarsystemmoveplugin.helper.*;
import net.gigaclub.solarsystemmoveplugin.planets.Planet;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Main extends JavaPlugin {

    private Main plugin;

    public ArrayList<Planet> planets;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        planets = createPlanets();

        for (Planet planet : planets) {
            importSchematics.createDirectory(planet.getName());
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        setPlugin(null);
    }

    public Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    public ArrayList<Planet> createPlanets() {
        ArrayList<Planet> planets = new ArrayList<Planet>();

        planets.add(new Planet("Sonne", 1));
        planets.add(new Planet("Merkur", 1));
        planets.add(new Planet("Venus", 1));
        planets.add(new Planet("Erde", 1));
        planets.add(new Planet("Mars", 1));
        planets.add(new Planet("Jupiter", 1));
        planets.add(new Planet("Saturn", 1));
        planets.add(new Planet("Uranus", 1));
        planets.add(new Planet("Neptun", 1));
        planets.add(new Planet("Pluto", 1));

        return planets;
    }


}
