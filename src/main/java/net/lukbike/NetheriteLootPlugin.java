package net.lukbike;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import org.bukkit.plugin.java.JavaPlugin;

public class NetheriteLootPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NetheriteLootPlugin enabled");
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent event) {
        if (event.getLootTable() == null) return;

        // Check if the loot table belongs to any bastion chest type
        if (event.getLootTable().getKey().equals(LootTables.BASTION_TREASURE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_BRIDGE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_HOGLIN_STABLE.getKey())
                || event.getLootTable().getKey().equals(LootTables.BASTION_OTHER.getKey())) {

            // Always add the Netherite Upgrade Smithing Template (100% chance)
            event.getLoot().add(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE));
        }
    }
}
