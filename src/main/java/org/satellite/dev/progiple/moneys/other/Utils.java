package org.satellite.dev.progiple.moneys.other;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.stream.Collectors;

@UtilityClass
public class Utils {
    @SuppressWarnings("deprecation")
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public double getMultiplier(Player player) {
        double value = 1.0;
        for (String perm : player.getEffectivePermissions().stream()
                .map(PermissionAttachmentInfo::getPermission)
                .filter(p -> p.contains("moneys.multiplier.")).collect(Collectors.toList())) {
            String[] splited = perm.split("\\.");
            if (splited.length < 3) continue;

            double permValue = Double.parseDouble(splited[2] + (splited.length >= 4 ? "." + splited[3] : ""));
            if (value < permValue) value = permValue;
        }
        return value;
    }
}
