public class Sword extends Weapon{

    // Sword => Default Damage * Strength Of Character

    public double getSpecialDamage(Character character){
        return character.getStrength() * getDefDamage();
    }

    public void specialPower(){
        
    }

    public Sword(){
        super();
    }

    public Sword(String nameParam, double weightParam, double defDamageParam){
        super(nameParam, weightParam, defDamageParam);
    }
}