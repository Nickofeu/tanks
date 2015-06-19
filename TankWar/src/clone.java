public class clone {
	public clone( Tank tank1, Tank tank2){
		tank2.setCannon(tank1.getCannon());
		tank2.setDammage(tank1.getDammage());
		tank2.setEssence(tank1.getEssence());
		tank2.setNom(tank1.getNom());
		tank2.setPv(tank1.getPv());
		tank2.setTaille(tank1.getTaille());
		tank2.setX(tank1.getX());
		tank2.setY(tank1.getY());
	}
}
