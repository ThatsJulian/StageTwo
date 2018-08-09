package com.goodgaming.stagetwo.economy;

import com.goodgaming.stagetwo.StageTwo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Integer balance = StageTwo.getInstance().getEcoDatabase().getBalance(((Player) sender).getUniqueId());
        sender.sendMessage("Your current balance: " + ChatColor.YELLOW + balance);
        return false;
    }
}
