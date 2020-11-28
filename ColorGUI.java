package life.steeze.simplehcf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ColorGUI {

    public static Inventory colors;
    public static void initGui(){
        colors = Bukkit.createInventory(null, 18, ChatColor.BLUE + "Colors");
        colors.addItem(createGuiItem(Material.WHITE_WOOL, ChatColor.RESET + "White"));
        colors.addItem(createGuiItem(Material.LIGHT_GRAY_WOOL, ChatColor.RESET + "Light Gray"));
        colors.addItem(createGuiItem(Material.GRAY_WOOL, ChatColor.RESET + "Dark Gray"));
        colors.addItem(createGuiItem(Material.PURPLE_WOOL, ChatColor.RESET + "Purple"));
        colors.addItem(createGuiItem(Material.MAGENTA_WOOL, ChatColor.RESET + "Magenta"));
        colors.addItem(createGuiItem(Material.BLUE_WOOL, ChatColor.RESET + "Blue"));
        colors.addItem(createGuiItem(Material.LIGHT_BLUE_WOOL, ChatColor.RESET + "Light Blue"));
        colors.addItem(createGuiItem(Material.CYAN_WOOL, ChatColor.RESET + "Cyan"));
        colors.addItem(createGuiItem(Material.LIME_WOOL, ChatColor.RESET + "Lime Green"));
        colors.addItem(createGuiItem(Material.GREEN_WOOL, ChatColor.RESET + "Green"));
        colors.addItem(createGuiItem(Material.RED_WOOL, ChatColor.RESET + "Red"));
        colors.addItem(createGuiItem(Material.YELLOW_WOOL, ChatColor.RESET + "Yellow"));

    }

    private static ItemStack createGuiItem(final Material material, final String name) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(null);

        item.setItemMeta(meta);

        return item;
    }
}
