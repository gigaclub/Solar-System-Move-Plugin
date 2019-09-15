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
            if(new File("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + planet.getName() + File.separator + planet.getName() + ".schem").exists()) {
                planet.setSchematic(importSchematics("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + planet.getName() + File.separator + planet.getName() + ".schem"));
            } else {
                if(new File("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + "basic" + File.separator + "basic.schem").exists()) {
                    planet.setSchematic(importSchematics("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + "basic" + File.separator + "basic.schem"));
                } else {
                    System.out.println("Keine Schematics existieren!");
                }

            }
        }
    }

    public static void createReadMeFiles(String planetName) {
        try {
            File readMe = new File("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + planetName + File.separator + "readMe" + planetName + ".txt");
            if(!readMe.exists()) {
                System.out.println("Die File für " + planetName + " existiert nicht!");
                File dir = readMe.getParentFile();
                if(dir.mkdirs()) {
                    System.out.println("Der Ordner für " + planetName + " wurde erstellt!");
                } else {
                    System.out.println("Der Ordner für " + planetName + " wurde nicht erstellt WAAAAAAAAAAARUUUUUUUUUUUUUUMMMMMMMMMM");
                }
                if(readMe.createNewFile()) {
                    FileWriter fw = new FileWriter("plugins" + File.separator + "Solar-System-Move-Plugin" + File.separator + planetName + File.separator + "readMe" + planetName + ".txt");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.print("Bitte importiere eine " + planetName + ".schem um einen Planet zu importieren!");
                    pw.close();
                } else {
                    System.out.println(planetName + " wird nicht erstellt WAAAAAAAAAAARUUUUUUUUUUUUUUMMMMMMMMMM");
                }
            }
        } catch (Exception e) {

        }

    }

}
