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
		if (inv.getName().equals("ĳ�ü�"))
		{
			e.setCancelled(true);
			switch (e.getRawSlot()) {
			case 0:
				p.sendMessage("��a������ �г����� �Է��ϼ���. ������ �Է� �Ұ��Դϴ�! '^���'�� �Է��ϸ� ��ҵ˴ϴ�.");
				main.buytype.put(p.getName(), item.NICKI);
				break;
			case 1:
				p.sendMessage("��a������ �г����� �Է��ϼ���. ���� �Է� �����մϴ�! (&���ڵ�) '^���'�� �Է��ϸ� ��ҵ˴ϴ�.");
				p.sendMessage("��aa ��bb ��cc ��dd ��ee ��ff ��11 ��22 ��33 ��44 ��55 ��66");
				main.buytype.put(p.getName(), item.NICKII);
				break;
			case 2:
				p.sendMessage("��a�̿��� �Ⱓ�� �Է��ϼ���. (1/3/7/15/30��) '^���'�� �Է��ϸ� ��ҵ˴ϴ�.");
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
