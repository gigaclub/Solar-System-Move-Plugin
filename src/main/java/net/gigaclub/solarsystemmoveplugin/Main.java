package net.gigaclub.solarsystemmoveplugin;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.Capability;
import net.gigaclub.solarsystemmoveplugin.commands.generateSolarSystem;
import net.gigaclub.solarsystemmoveplugin.helper.*;
import net.gigaclub.solarsystemmoveplugin.planets.Planet;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class Main extends JavaPlugin {

    private static Main plugin;

    public static ArrayList<Planet> planets;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        planets = createPlanets();

        for (Planet planet : planets) {
            importSchematics.createReadMeFiles(planet.getName());
        }

        importSchematics.createReadMeFiles("basic");

        getPlugin().saveConfig();

        importSchematics.writeSchematics();

        getPlugin().saveConfig();

        Objects.requireNonNull(getCommand("generateSolarSystem")).setExecutor(new generateSolarSystem());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        setPlugin(null);
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static void setPlugin(Main pl) {
        plugin = pl;
    }

    public ArrayList<Planet> createPlanets() {
        ArrayList<Planet> planets = new ArrayList<Planet>();

        planets.add(new Planet("Sonne", 1, 0, 127, 0));
        planets.add(new Planet("Merkur", 1, 300, 127, 0));
        planets.add(new Planet("Venus", 1, 600, 127, 0));
        planets.add(new Planet("Erde", 1, 900, 127, 0));
        planets.add(new Planet("Mars", 1, 1200, 127, 0));
        planets.add(new Planet("Jupiter", 1, 1500, 127, 0));
        planets.add(new Planet("Saturn", 1, 1800, 127, 0));
        planets.add(new Planet("Uranus", 1, 2100, 127, 0));
        planets.add(new Planet("Neptun", 1, 2400, 127, 0));
        planets.add(new Planet("Pluto", 1, 2700, 127, 0));

        return planets;
    }

    public static World createVoidWorld() {

        return Bukkit.createWorld(new WorldCreator("VoidWorld")
                                .generator(new VoidChunkGenerator())
                                .type(WorldType.CUSTOMIZED));
    }

        static class VoidChunkGenerator extends ChunkGenerator {

            @Override
            public List<BlockPopulator> getDefaultPopulators(World world) {
                return Collections.<BlockPopulator>emptyList();
            }

            @Override
            public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
                ChunkData chunkData = super.createChunkData(world);

                // Set biome.
                for(int x = 0; x < 16; x++) {
                    for(int z = 0; z < 16; z++) {
                        biome.setBiome(x, z, Biome.PLAINS);
                    }
                }

                // Return the new chunk data.
                return chunkData;
            }

            @Override
            public boolean canSpawn(World world, int x, int z) {
                return true;
            }

            @Override
            public Location getFixedSpawnLocation(World world, Random random) {
                return new Location(world, 0, 100, 0);
            }
        }

    }
