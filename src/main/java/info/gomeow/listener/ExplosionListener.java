package info.gomeow.listener;

import info.gomeow.Depositchest;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    public Depositchest plugin;

    public ExplosionListener(Depositchest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        List<Block> blocks = event.blockList();
        synchronized(blocks) {
            Iterator<Block> iterator = blocks.iterator();
            while(iterator.hasNext()) {
                Block block = iterator.next();
                int id = block.getTypeId();
                if(id == 54) {
                    if(block.getState() instanceof Chest) {
                        Chest eventChest = (Chest) block.getState();
                        Location chestLoc = eventChest.getLocation();
                        String world = chestLoc.getWorld().getName();
                        Integer x = chestLoc.getBlockX();
                        Integer y = chestLoc.getBlockY();
                        Integer z = chestLoc.getBlockZ();
                        for(String nameKey:plugin.chestConfig.getChests().getKeys(false)) {
                            for(String key:plugin.chestConfig.getChests().getConfigurationSection(nameKey).getKeys(false)) {
                                String world1 = plugin.chestConfig.getChests().getString(nameKey + "." + key + ".World");
                                Integer x1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".X");
                                Integer y1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".Y");
                                Integer z1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".Z");
                                String loc1 = world + ", " + x.toString() + ", " + y.toString() + ", " + z.toString();
                                String loc2 = world1 + ", " + x1.toString() + ", " + y1.toString() + ", " + z1.toString();
                                if(loc1.equalsIgnoreCase(loc2)) {
                                    iterator.remove();
                                }
                            }
                        }
                    } else if(block.getState() instanceof DoubleChest) {
                        DoubleChest eventChest = (DoubleChest) block.getState();
                        Location chestLoc = eventChest.getLocation();
                        String world = chestLoc.getWorld().getName();
                        Integer x = chestLoc.getBlockX();
                        Integer y = chestLoc.getBlockY();
                        Integer z = chestLoc.getBlockZ();
                        for(String nameKey:plugin.chestConfig.getChests().getKeys(false)) {
                            for(String key:plugin.chestConfig.getChests().getConfigurationSection(nameKey).getKeys(false)) {
                                String world1 = plugin.chestConfig.getChests().getString(nameKey + "." + key + ".World");
                                Integer x1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".X");
                                Integer y1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".Y");
                                Integer z1 = plugin.chestConfig.getChests().getInt(nameKey + "." + key + ".Z");
                                String loc1 = world + ", " + x.toString() + ", " + y.toString() + ", " + z.toString();
                                String loc2 = world1 + ", " + x1.toString() + ", " + y1.toString() + ", " + z1.toString();
                                if(loc1.equalsIgnoreCase(loc2)) {
                                    iterator.remove();
                                } else {
                                    for(BlockFace bf:plugin.faces) {
                                        Location chestLoc2 = eventChest.getLocation().getBlock().getRelative(bf).getLocation();
                                        String world2 = chestLoc2.getWorld().getName();
                                        Integer x2 = chestLoc2.getBlockX();
                                        Integer y2 = chestLoc2.getBlockY();
                                        Integer z2 = chestLoc2.getBlockZ();
                                        String loc3 = world1 + ", " + x1.toString() + ", " + y1.toString() + ", " + z1.toString();
                                        String loc4 = world2 + ", " + x2.toString() + ", " + y2.toString() + ", " + z2.toString();
                                        if(loc3.equalsIgnoreCase(loc4)) {
                                            iterator.remove();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}