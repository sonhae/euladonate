package euladonate;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class invlisten implements Listener{

	@EventHandler
	public void playerinv(InventoryClickEvent e)
	{	
		Inventory inv = e.getView().getTopInventory();
		Player p = (Player) e.getWhoClicked();
		if (inv.getName().equals("캐시샵"))
		{
			e.setCancelled(true);
			switch (e.getRawSlot()) {
			case 0:
				p.sendMessage("§a변경할 닉네임을 입력하세요. 색깔은 입력 불가입니다! '^취소'를 입력하면 취소됩니다.");
				main.buytype.put(p.getName(), item.NICKI);
				break;
			case 1:
				p.sendMessage("§a변경할 닉네임을 입력하세요. 색깔도 입력 가능합니다! (&색코드) '^취소'를 입력하면 취소됩니다.");
				p.sendMessage("§aa §bb §cc §dd §ee §ff §11 §22 §33 §44 §55 §66");
				main.buytype.put(p.getName(), item.NICKII);
				break;
			case 2:
				p.sendMessage("§a이용할 기간을 입력하세요. (1/3/7/15/30일) '^취소'를 입력하면 취소됩니다.");
				main.buytype.put(p.getName(), item.PARTICLE);
				break;
			default:
				break;
			}
			p.closeInventory();
		}
		else
		{
			
		}
	}
}
