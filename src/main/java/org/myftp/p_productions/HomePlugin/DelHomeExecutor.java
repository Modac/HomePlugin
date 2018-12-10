package org.myftp.p_productions.HomePlugin;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;
import org.myftp.p_productions.HomePlugin.exceptions.NoHomeFoundException;

import java.io.IOException;
import java.util.Arrays;

public class DelHomeExecutor implements CommandExecutor {

  Home plugin;

  public DelHomeExecutor(Home homePlugin) {
    plugin = homePlugin;
  }

  private static void printUsage(CommandSender sender, String label, boolean color,
      boolean printPlayer, boolean playerOptional, boolean printNumber) {

    String msg = "Delete a Home:\n" +
        "  /%s" + (printPlayer ? String.format(playerOptional ? " [%s]" : " <%s>", "player") : "")
        + (printNumber ? " [homeNumber]" : "");

    for (String line : msg.split("\n")) {
      sender.sendMessage(Messages.getInstance().getPrefix(color) + String.format(line, label));
    }
  }

  private static void printUsage(CommandSender sender, String label) {
    if (sender instanceof Player) {
      if (((Player) sender).hasPermission("homeplugin.delhome.other")) {
        printUsage(sender, label, true, true, true, true);
      } else {
        printUsage(sender, label, true, false, false, true);
      }
    } else {
      printUsage(sender, label, false, true, false, true);
    }
  }


  //@SuppressWarnings("deprecation")
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    new BukkitRunnable() {

      @Override
      public void run() {
        boolean playerSender = sender instanceof Player;

        if (!playerSender && args.length < 1) {
          sender.sendMessage(Messages.getInstance().getGetHomeCmdMissingPlayer(false));
          printUsage(sender, label);
          return;
        } else if (args.length > 2) {
          printUsage(sender, label);
          return;
        }

        boolean isOther = false;
        OfflinePlayer player = null;
        int number = -1;

        // If 2 arguments are given it doesn't matter if player or console issued the command
        if (args.length == 2) {
          // But if the player doesn't have the permission, fail
          if (playerSender && !((Player) sender).hasPermission("homeplugin.delhome.other")) {
            sender.sendMessage(Messages.getInstance().getNoPermission());
            return;
          }

          // get Player by Name
          player = plugin.getServer().getOfflinePlayer(args[0]);
          isOther = true;
          // If no valid player was found, fail
          if (!player.hasPlayedBefore()) {
            sender.sendMessage(Messages.getInstance().getGetHomeCmdPlayerNotFound(playerSender));
            return;
          }

          // Get home number, if param is no number, fail
          if (NumberUtils.isCreatable(args[1])) {
            number = NumberUtils.createNumber(args[1]).intValue();
          } else {
            sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(playerSender));
            printUsage(sender, label);
            return;
          }

          // if only one argument is given
        } else if (args.length == 1) {
          // if the player doesn't have the permission, fail
          if (playerSender && !((Player) sender).hasPermission("homeplugin.delhome")) {
            sender.sendMessage(Messages.getInstance().getNoPermission());
            return;
          }

          player = plugin.getServer().getOfflinePlayer(args[0]);
          isOther = true;
          // if no valid player was found
          if (!player.hasPlayedBefore()) {
            // and command was issued by console, fail
            if (!playerSender) {
              sender.sendMessage(Messages.getInstance().getGetHomeCmdPlayerNotFound(playerSender));
              return;
            }

            // else if the arg also isn't a number, fail
            if (!NumberUtils.isCreatable(args[0])) {
              sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(playerSender));
              printUsage(sender, label);
              return;
            } else {
              // arg is a number player is the sender
              player = (OfflinePlayer) sender;
              isOther = false;
              number = NumberUtils.createNumber(args[0]).intValue();
            }
            // if player is valid asume home number as 1
          } else {
            number = 1;
          }
        }

        if (number == -1 || player == null) {
          sender.sendMessage("Fail");
          return;
        }

        try {
          if (delHome(DelHomeExecutor.this.plugin, player, number)) {
            if (isOther) {
              sender.sendMessage(Messages.getInstance()
                  .getDelHomeCmdSuccess4Other(number, player.getName(), playerSender));
            } else {
              sender.sendMessage(Messages.getInstance().getDelHomeCmdSuccess4Self(number, true));
            }
          } else {
            if (isOther) {
              sender.sendMessage(Messages.getInstance()
                  .getDelHomeCmdNoHome4Other(number, player.getName(), playerSender));
            } else {
              sender.sendMessage(Messages.getInstance().getDelHomeCmdNoHome4Self(number, true));
            }
          }

        } catch (IOException e) {
          e.printStackTrace();
        }

      }

    }.runTaskAsynchronously(this.plugin);
    return true;

  }


  private boolean delHome(Home homeplugin, OfflinePlayer player, int number) throws IOException {

    String path = String.format(Home.homePath, player.getUniqueId(), number);
    if (!homeplugin.homeData.isSet(path)) {
      return false;
    }
    homeplugin.homeData.set(path, null);

    plugin.homeData.save(plugin.dataFile);
    return true;
  }

}
