import java.security.SecureRandom;
import java.util.ArrayList;

public class EnemySoldier extends Character {

    protected double getCurrentTotalHP(){
        return getCurrentCharacterHP();
    }

    @Override
    protected Clothe getClothe() {
        return null;
    }

    @Override
    protected Inventory getInventory(){
        return null;
    }
    
    @Override
    protected void setClothe(Clothe currentClothe) {}

    public EnemySoldier(){
        super();
    }

    public EnemySoldier(double strengthParam,double vitalityParam, double intelligenceParam, String nameParam,
    ArrayList<Sword> swordArrayListParam, ArrayList<Shield> shieldArrayListParam,
    ArrayList<Wand> wandArrayListParam){
        
        super(strengthParam, vitalityParam, intelligenceParam, nameParam);

        SecureRandom sr = new SecureRandom();

        // 80% sword, 10% shield, 10% wand
    
        int number = sr.nextInt(11); // 0 origin 11 bound

        if(number >= 0 & number <= 8){
            int index = sr.nextInt(swordArrayListParam.size());
            setWeapon(swordArrayListParam.get(index));
        } else if (number == 9){
            int index = sr.nextInt(shieldArrayListParam.size());
            setWeapon(shieldArrayListParam.get(index));
        } else if (number == 10){
            int index = sr.nextInt(wandArrayListParam.size());
            setWeapon(wandArrayListParam.get(index));
        }
    }
}