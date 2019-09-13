package net.gigaclub.solarsystemmoveplugin.helper;

import java.io.*;
import java.util.Map;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

public class importSchematics {

    public static Schematic importSchematics(String path) {

        if(path.contains(".schematic")) {
            try {
                File f = new File(path);
                FileInputStream fis = new FileInputStream(f);
                NBTInputStream nbt = new NBTInputStream(fis);
                CompoundTag backuptag = (CompoundTag) nbt.readTag();
                Map<String, Tag> tagCollection = backuptag.getValue();

                short width = (Short)getChildTag(tagCollection, "Width", ShortTag.class).getValue();
                short height = (Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue();
                short length = (Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue();

                byte[] blocks = (byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue();
                byte[] data = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

                nbt.close();
                fis.close();

                return new Schematic(blocks, data, width, length, height);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
        Tag tag = items.get(key);
        return tag;
    }

    public static Schematic basicSchematic() {
        return new Schematic(1,1,1,1,1);
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
                pw.print("Bitte importiere eine " + planetName + ".schematic um einen Planet zu importieren!");
                pw.close();
            }
        } catch (Exception e) {

        }

    }

}
