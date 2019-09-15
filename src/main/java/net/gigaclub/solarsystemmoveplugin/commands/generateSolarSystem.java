package net.gigaclub.solarsystemmoveplugin.commands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import net.gigaclub.solarsystemmoveplugin.Main;
import net.gigaclub.solarsystemmoveplugin.planets.Planet;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.awt.datatransfer.Clipboard;

public class generateSolarSystem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("SSMgenerateSolarSystem")) {
            World voidWorld = Main.createVoidWorld();

            for (Planet planet : Main.planets) {

                if(planet.getSchematic() != null) {

                    Clipboard clipboard = (Clipboard) planet.getSchematic();

                    try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession((com.sk89q.worldedit.world.World) voidWorld, -1)) {
                        Operation operation = new ClipboardHolder((com.sk89q.worldedit.extent.clipboard.Clipboard) clipboard)
                                .createPaste(editSession)
                                .to(BlockVector3.at(planet.getX(), planet.getY(), planet.getZ()))
                                .ignoreAirBlocks(false)
                                .build();
                        Operations.complete(operation);
                        System.out.println("Completed " + planet.getName());
                    } catch (WorldEditException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Nichts gefunden!");
                }

            }
        }

        return false;
    }
}
