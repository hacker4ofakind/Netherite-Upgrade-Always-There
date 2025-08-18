package net.lukbike;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import org.bukkit.plugin.java.JavaPlugin;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;

public class NetheriteUpgradeAlwaysThere extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NetheriteLootPlugin enabled");
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent event) {
        if (event.getLootTable() == null) return;

        if (event.getLootTable().getKey().equals(LootTables.BASTION_TREASURE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_BRIDGE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_HOGLIN_STABLE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_OTHER.getKey())) {

            Location chestLocation = event.getLootContext().getLocation();
            if (chestLocation != null) {
                RegionScheduler scheduler = getServer().getRegionScheduler();
                scheduler.execute(this, chestLocation, () -> {
                    event.getLoot().add(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE));
                });
            } else {
                // Fallback: if somehow location is null, just add directly
                event.getLoot().add(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE));
            }
        }
    }
}
