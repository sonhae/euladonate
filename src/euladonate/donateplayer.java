package euladonate;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class donateplayer {
 private long particle;
 private String nickname;

private String name;
public long getParticle() {
	return particle;
}
public void setParticle(long particle) {
	this.particle = particle;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getName() {
	return name;
}
public void expired()
{
	PermissionUser pu =  PermissionsEx.getUser(this.name);
	pu.removePermission("ParticleMenu.open");
	pu.removePermission("ParticleMenu.use.*");
	this.particle = 0;
}
public void particleadd(){
	PermissionUser pu =  PermissionsEx.getUser(this.name);
	pu.addPermission("ParticleMenu.open");
	pu.addPermission("ParticleMenu.use.*");
}
public donateplayer(String name) {
	super();
	this.name = name;
	this.nickname = "";
	this.particle = 0;
}
public donateplayer(long particle, String nickname, String name) {
	super();
	this.particle = particle;
	this.nickname = nickname;
	this.name = name;
}

}
