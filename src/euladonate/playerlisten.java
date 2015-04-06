package euladonate;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import com.gmail.jjcdhk.Cash.Cash;

public class playerlisten implements Listener{
	private main pl;
 public playerlisten(main pl) {
		super();
		this.pl = pl;
	}
@EventHandler
 public void playerjoin(PlayerJoinEvent e)
 {
	donateplayer dp = pl.loadplayer(e.getPlayer().getName());
	if (dp.getParticle() <= System.currentTimeMillis() && dp.getParticle() != 0)
	{
		e.getPlayer().performCommand("particlemenu stop");
		dp.expired();
		e.getPlayer().sendMessage("��c��ƼŬ ȿ���� �Ⱓ�� ����Ǿ����ϴ�.");
	}
	if (!dp.getNickname().equals(""))
	{
		e.getPlayer().setDisplayName(dp.getNickname());
	}
 }

@EventHandler
 public void playerchat(AsyncPlayerChatEvent e)
 {
	 Player p = e.getPlayer();
	 donateplayer dp = pl.loadplayer(e.getPlayer().getName());
	 if (main.buytype.containsKey(p.getName()) && !main.buyinfo.containsKey(p.getName()))
	 {
		 item buytype = main.buytype.get(p.getName());
		 e.setCancelled(true);
		 if (e.getMessage().equals("^���"))
		 {
			 main.buytype.remove(p.getName());
			 main.buyinfo.remove(p.getName());
			 p.sendMessage("���Ÿ� ����մϴ�.");
			 return;
		 }
		 if (buytype == item.NICKII)
		 {
			 String s = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			 main.buyinfo.put(p.getName(), s);
			 p.sendMessage("��c���� �г����� ��r" + s+  "��c�� �ٲٽðڽ��ϱ�?");
			 p.sendMessage("��cȮ���� ���� \"yes\"�� �Է����ּ���. �̿��� ���ڿ��� ��ҷ� �����մϴ�.");
			 return;
		 }
		 if (buytype == item.PARTICLE)
		 {
		        if (!e.getMessage().matches("^1$|^3$|^7$|^15$|^30$"))
		        {
		          p.sendMessage("��c�ϼ���  1/3/7/15/30���̸�, �ڿ����� �Է��ؾ� �մϴ�");
		          return;
		        }
			 int a = Integer.parseInt(e.getMessage());
			 p.sendMessage("��c���� ��ƼŬ�� " + a+"�ϵ��� ����Ͻðڽ��ϱ�?");
			 p.sendMessage("��cȮ���� ���� \"yes\"�� �Է����ּ���. �̿��� ���ڿ��� ��ҷ� �����մϴ�.");
			 main.buyinfo.put(p.getName(), a);
			 return;
		 }
		 main.buyinfo.put(p.getName(), e.getMessage());
		 p.sendMessage("��c���� �г����� ��r" + e.getMessage()+  "��c�� �ٲٽðڽ��ϱ�?");
		 p.sendMessage("��cȮ���� ���� \"yes\"�� �Է����ּ���. �̿��� ���ڿ��� ��ҷ� �����մϴ�.");
		 return;
	 }
	 if (main.buytype.containsKey(p.getName()) && main.buyinfo.containsKey(p.getName()))
	 {
		 e.setCancelled(true);
		 if(e.getMessage().equalsIgnoreCase("yes"))
		 {
			 item buytype = main.buytype.get(p.getName());
			 Object o = main.buyinfo.get(p.getName());
			 switch (buytype) {
			case NICKI:
				if (!Cash.minus(p.getName(), 5000))
				{
					p.sendMessage("ĳ���� �����մϴ�.");
					 main.buytype.remove(p.getName());
					 main.buyinfo.remove(p.getName());
					return;
				}
				pl.getConfig().set(p.getName() + ".nick", o + "");
				p.setDisplayName(o + "");
				p.sendMessage("��a���Կ� �����߽��ϴ�. ĳ�ð� 5000ĳ�� �����˴ϴ�.");
				break;
			case NICKII:
				if (!Cash.minus(p.getName(), 6000))
				{
					p.sendMessage("ĳ���� �����մϴ�.");
					 main.buytype.remove(p.getName());
					 main.buyinfo.remove(p.getName());
					return;
				}
				pl.getConfig().set(p.getName() + ".nick", o + "");
				p.setDisplayName(o + "");
				p.sendMessage("��a���Կ� �����߽��ϴ�. ĳ�ð� 6000ĳ�� �����˴ϴ�.");
				break;
			case PARTICLE:
				int x = (int)o;
				int a = x * 600;
				if (x == 1)a=700;
				if (!Cash.minus(p.getName(), a))
				{
					p.sendMessage("ĳ���� �����մϴ�.");
					 main.buytype.remove(p.getName());
					 main.buyinfo.remove(p.getName());
					return;
				}
				int day = 1000*3600*24;
				if (dp.getParticle() == 0)
				{
					pl.getConfig().set(p.getName() + ".part", System.currentTimeMillis() + (day*x));
				}
				else
				{
					pl.getConfig().set(p.getName() + ".part", dp.getParticle()+ (day*x));
				}
				dp.particleadd();
				p.sendMessage("��a���Կ� �����߽��ϴ�. ĳ�ð� "+a+"ĳ�� �����˴ϴ�.");
				break;
			default:
				break;
			}
			 main.buytype.remove(p.getName());
			 main.buyinfo.remove(p.getName());
		 }
		 else
		 {
			 main.buytype.remove(p.getName());
			 main.buyinfo.remove(p.getName());
			 p.sendMessage("���Ÿ� ����մϴ�.");
			 return;
		 }
	 }
 }
}
