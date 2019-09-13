package net.gigaclub.solarsystemmoveplugin.helper;

import java.io.*;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import net.gigaclub.solarsystemmoveplugin.Main;
import net.gigaclub.solarsystemmoveplugin.planets.Planet;

public class importSchematics {

    public static Clipboard importSchematics(String path) {

        File file = new File(path);
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            Clipboard clipboard = reader.read();
            return clipboard;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void writeSchematics() {
        for(Planet planet : Main.planets) {
            if(new File("/" + planet.getName() + "/" + planet.getName() + ".schem").exists()) {
                planet.setSchematic(importSchematics(planet.getName() + ".schem"));
            } else {
                basicSchematic();
            }
        }
    }

    public static Clipboard basicSchematic() {
        createDirectory("basic");
        File file = new File("basic.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            Clipboard clipboard = reader.read();
            return clipboard;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createDirectory(String planetName) {
        File directory = new File("/" + planetName);
        if(!directory.exists()) {
            if(directory.mkdir()) {
                System.out.println("Ordner für " + planetName + " wurde erstellt!");
                createReadMeFiles(planetName);
            }
        }
    }

    public static void createReadMeFiles(String planetName) {
        try {
            File readMe = new File("readMe" + planetName + ".txt");
            if(readMe.createNewFile()) {
                FileWriter fw = new FileWriter(planetName + ".txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.print("Bitte importiere eine " + planetName + ".schem um einen Planet zu importieren!");
                pw.close();
            }
        } catch (Exception e) {

        }

    }

}
