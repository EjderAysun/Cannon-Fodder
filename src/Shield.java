public class Shield extends Weapon{
    // Shield => Default Damage * Vitality Of Character

    public double getSpecialDamage(Character character){
        return character.getVitality() * getDefDamage();
    }

    public void specialPower(){
    }

    public Shield(){
        super();
    }

    public Shield(String nameParam, double weightParam, double defDamageParam){
        super(nameParam, weightParam, defDamageParam);
    }
}