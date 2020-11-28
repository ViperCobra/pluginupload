package life.steeze.simplehcf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.*;
import java.util.List;

public class HCFMain extends JavaPlugin implements CommandExecutor {



    public static List<Faction> factions = new ArrayList<>();
    public static Faction getFacByName(String name){
        for(Faction f : factions){
            if(f.getName().equalsIgnoreCase(name)){
                return f;
            }
        }
        return null;
    }

    public static HashMap<Player, Faction> Fplayers = new HashMap<>(), invites = new HashMap<>();
    public static ArrayList<Selection> positions = new ArrayList<>();

    public static List<ChatColor> usableColors = new ArrayList<>(Arrays.asList(ChatColor.AQUA,ChatColor.DARK_AQUA,ChatColor.BLACK,ChatColor.BLUE,ChatColor.DARK_BLUE,ChatColor.DARK_GRAY,ChatColor.DARK_GREEN,ChatColor.DARK_PURPLE,ChatColor.DARK_RED,ChatColor.GOLD,ChatColor.GRAY,ChatColor.GREEN,ChatColor.LIGHT_PURPLE,ChatColor.RED,ChatColor.WHITE,ChatColor.YELLOW));

    public FCommand fCommand = new FCommand();
    public PosCommands posCommands = new PosCommands();
    public EventHandling eventHandling = new EventHandling();

    public static String chatFormat,noTeamFormat;
    public static int minimumClaimWidth, maximumClaimCornerDistance;
    public static int maxDescriptionLength;
    public static boolean formatChat, mobSpawnInClaim;

    public static Plugin inst;
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.getCommand("faction").setExecutor(fCommand);
        this.getCommand("pos1").setExecutor(posCommands);
        this.getCommand("pos2").setExecutor(posCommands);
        inst = this;
        getServer().getPluginManager().registerEvents(eventHandling, inst);
        ConfigurationSerialization.registerClass(Faction.class);
        ConfigurationSerialization.registerClass(Claim.class);

        FactionsFile.loadFactions();

        ColorGUI.initGui();
        formatChat = getConfig().getBoolean("format-chat");
        chatFormat = getConfig().getString("formatted-chat");
        noTeamFormat = getConfig().getString("formatted-chat-no-team-found-for-player");
        minimumClaimWidth = getConfig().getInt("minimumClaimWidth");
        maximumClaimCornerDistance = getConfig().getInt("maximumClaimCornerDistance");
        maxDescriptionLength = getConfig().getInt("maxDescriptionLength");
        mobSpawnInClaim = getConfig().getBoolean("aggressiveMobSpawningInClaims");
        //In case of server reload add online players to Fplayers
        for(Player p : Bukkit.getOnlinePlayers()){
            for(Faction f : factions){
                if(f.getMembers().contains(p.getUniqueId()) || f.getLeader().equals(p.getUniqueId())){
                    HCFMain.Fplayers.put(p, f);
                }
            }
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                for(Faction f : factions){
                    f.regen();
                }
            }
        }.runTaskTimer(inst, getConfig().getLong("dtr-regen"), Long.MAX_VALUE);
    }
    @Override
    public void onDisable(){
        FactionsFile.saveFactions();
    }


}
