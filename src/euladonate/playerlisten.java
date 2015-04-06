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
		e.getPlayer().sendMessage("§c파티클 효과의 기간이 만료되었습니다.");
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
		 if (e.getMessage().equals("^취소"))
		 {
			 main.buytype.remove(p.getName());
			 main.buyinfo.remove(p.getName());
			 p.sendMessage("구매를 취소합니다.");
			 return;
		 }
		 if (buytype == item.NICKII)
		 {
			 String s = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			 main.buyinfo.put(p.getName(), s);
			 p.sendMessage("§c정말 닉네임을 §r" + s+  "§c로 바꾸시겠습니까?");
			 p.sendMessage("§c확인을 위해 \"yes\"를 입력해주세요. 이외의 문자열은 취소로 간주합니다.");
			 return;
		 }
		 if (buytype == item.PARTICLE)
		 {
		        if (!e.getMessage().matches("^1$|^3$|^7$|^15$|^30$"))
		        {
		          p.sendMessage("§c일수는  1/3/7/15/30일이며, 자연수를 입력해야 합니다");
		          return;
		        }
			 int a = Integer.parseInt(e.getMessage());
			 p.sendMessage("§c정말 파티클을 " + a+"일동안 사용하시겠습니까?");
			 p.sendMessage("§c확인을 위해 \"yes\"를 입력해주세요. 이외의 문자열은 취소로 간주합니다.");
			 main.buyinfo.put(p.getName(), a);
			 return;
		 }
		 main.buyinfo.put(p.getName(), e.getMessage());
		 p.sendMessage("§c정말 닉네임을 §r" + e.getMessage()+  "§c로 바꾸시겠습니까?");
		 p.sendMessage("§c확인을 위해 \"yes\"를 입력해주세요. 이외의 문자열은 취소로 간주합니다.");
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
					p.sendMessage("캐쉬가 부족합니다.");
					 main.buytype.remove(p.getName());
					 main.buyinfo.remove(p.getName());
					return;
				}
				pl.getConfig().set(p.getName() + ".nick", o + "");
				p.setDisplayName(o + "");
				p.sendMessage("§a구입에 성공했습니다. 캐시가 5000캐시 차감됩니다.");
				break;
			case NICKII:
				if (!Cash.minus(p.getName(), 6000))
				{
					p.sendMessage("캐쉬가 부족합니다.");
					 main.buytype.remove(p.getName());
					 main.buyinfo.remove(p.getName());
					return;
				}
				pl.getConfig().set(p.getName() + ".nick", o + "");
				p.setDisplayName(o + "");
				p.sendMessage("§a구입에 성공했습니다. 캐시가 6000캐시 차감됩니다.");
				break;
			case PARTICLE:
				int x = (int)o;
				int a = x * 600;
				if (x == 1)a=700;
				if (!Cash.minus(p.getName(), a))
				{
					p.sendMessage("캐쉬가 부족합니다.");
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
				p.sendMessage("§a구입에 성공했습니다. 캐시가 "+a+"캐시 차감됩니다.");
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
			 p.sendMessage("구매를 취소합니다.");
			 return;
		 }
	 }
 }
}
