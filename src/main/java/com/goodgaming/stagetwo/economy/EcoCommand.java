package com.goodgaming.stagetwo.economy;

import com.goodgaming.stagetwo.StageTwo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EcoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("testproject.command.eco")) return false;

        if(args.length < 3) {
            sender.sendMessage(ChatColor.RED+ "The correct usage is '/eco give <player> <amount>'");
        }
        if(!args[0].equalsIgnoreCase("give")) return false;
        if(!Bukkit.getPlayer(args[1]).isOnline()) return false;
        if(!isInteger(args[2])) return false;

        Player player = Bukkit.getPlayer(args[1]);
        Integer amount = Integer.parseInt(args[2]);

        if(amount < 0) return false;

        StageTwo.getInstance().getEcoDatabase().addBalance(player.getUniqueId(), amount);
        sender.sendMessage("You have sent " + player.getName() + " " + amount + " dollar(s)!");
        player.sendMessage("You have received " + amount + " dollar(s)!");
        return false;
    }

    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
