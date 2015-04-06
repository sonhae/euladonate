package euladonate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	public static List<donateplayer> list;
	public static HashMap<String, item> buytype = new HashMap<>();
	public static HashMap<String, Object> buyinfo = new HashMap<>();
	public donateplayer loadplayer(String s)
	{
		if (getConfig().getConfigurationSection("").contains(s))
		{
			donateplayer d = new donateplayer(getConfig().getLong(s+".part"),getConfig().getString(s+".nick"),s);
			list.add(d);
			return d;
		}
		getConfig().set(s + ".part", 0);
		getConfig().set(s + ".nick", "");
		donateplayer d = new donateplayer(s);
		return d;
	}
	public static Inventory getMenu()
	{
		Inventory inv = Bukkit.createInventory(null, 9, "캐시샵");
		{
			ItemStack i = new ItemStack(Material.BOOK);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("§e닉네임 변경 I");
			List<String> sl = new ArrayList<>();
			sl.add("§f닉네임을 1회 변경합니다.");
			sl.add("§f지나친 특수문자는 삼가해주세요.");
			sl.add("§a가격: 5000캐시");
			im.setLore(sl);
			i.setItemMeta(im);
			inv.setItem(0, i);
		}
		{
			ItemStack i = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("§e닉네임 변경 II");
			List<String> sl = new ArrayList<>();
			sl.add("§f닉네임을 1회 변경합니다.");
			sl.add("§d색 코드를 입력할수 있습니다. (&색코드)");
			sl.add("§f지나친 특수문자는 삼가해주세요.");
			sl.add("§a가격: 6000캐시");
			im.setLore(sl);
			i.setItemMeta(im);
			inv.setItem(1, i);
		}
		{
			ItemStack i = new ItemStack(Material.NETHER_STAR);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("§a파티클 효과(기간제)");
			List<String> sl = new ArrayList<>();
			sl.add("§f여러가지 파티클을 이용할 수 있습니다. (/파티클 입력)");
			sl.add("§a1일: 700캐시");
			sl.add("§a3일 : 2000캐시");
			sl.add("§a7일: 4200캐시");
			sl.add("§a15일: 9000캐시");
			sl.add("§a30일: 18000캐시");
			im.setLore(sl);
			i.setItemMeta(im);
			inv.setItem(2, i);
		}
		return inv;
	}
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		saveConfig();
		super.onDisable();
	}
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		saveDefaultConfig();
		getCommand("cashshop").setExecutor(this);
		getCommand("캐시샵").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(new invlisten(), this);
		Bukkit.getPluginManager().registerEvents(new playerlisten(this), this);
		list = new ArrayList<>();
		super.onEnable();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player)
		{
			((Player) sender).openInventory(getMenu());
		}
		return super.onCommand(sender, command, label, args);
	}
	
}
