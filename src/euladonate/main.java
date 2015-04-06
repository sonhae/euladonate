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
		Inventory inv = Bukkit.createInventory(null, 9, "ĳ�ü�");
		{
			ItemStack i = new ItemStack(Material.BOOK);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("��e�г��� ���� I");
			List<String> sl = new ArrayList<>();
			sl.add("��f�г����� 1ȸ �����մϴ�.");
			sl.add("��f����ģ Ư�����ڴ� �ﰡ���ּ���.");
			sl.add("��a����: 5000ĳ��");
			im.setLore(sl);
			i.setItemMeta(im);
			inv.setItem(0, i);
		}
		{
			ItemStack i = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("��e�г��� ���� II");
			List<String> sl = new ArrayList<>();
			sl.add("��f�г����� 1ȸ �����մϴ�.");
			sl.add("��d�� �ڵ带 �Է��Ҽ� �ֽ��ϴ�. (&���ڵ�)");
			sl.add("��f����ģ Ư�����ڴ� �ﰡ���ּ���.");
			sl.add("��a����: 6000ĳ��");
			im.setLore(sl);
			i.setItemMeta(im);
			inv.setItem(1, i);
		}
		{
			ItemStack i = new ItemStack(Material.NETHER_STAR);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("��a��ƼŬ ȿ��(�Ⱓ��)");
			List<String> sl = new ArrayList<>();
			sl.add("��f�������� ��ƼŬ�� �̿��� �� �ֽ��ϴ�. (/��ƼŬ �Է�)");
			sl.add("��a1��: 700ĳ��");
			sl.add("��a3�� : 2000ĳ��");
			sl.add("��a7��: 4200ĳ��");
			sl.add("��a15��: 9000ĳ��");
			sl.add("��a30��: 18000ĳ��");
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
		getCommand("ĳ�ü�").setExecutor(this);
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
