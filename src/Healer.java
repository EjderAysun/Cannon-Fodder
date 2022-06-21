import java.security.SecureRandom;
import java.util.ArrayList;

public class Healer extends Character {
    
    private Inventory inventory;
    private Clothe clothe;

    // recalculated every time it is called
    public double getCurrentTotalHP(){
        return clothe.getArmorScore() + getCurrentCharacterHP();
    }
    public Clothe getClothe(){
        return this.clothe;
    }
    public Inventory getInventory(){
        return this.inventory;
    }

    public void setClothe(Clothe currentClothe){
        this.clothe = currentClothe;
    }
    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Healer(){
        super();
        this.inventory = new Inventory();
    }

    public Healer(double strengthParam,double vitalityParam, double intelligenceParam, String nameParam,
    ArrayList<Wand> wandArrayListParam){
        super(strengthParam, vitalityParam, intelligenceParam, nameParam);

        // While creating the fighter, you are given a random weapon

        SecureRandom sr = new SecureRandom();
        int index = sr.nextInt(wandArrayListParam.size());
        setWeapon(wandArrayListParam.get(index));

        this.inventory = new Inventory();
        this.clothe = new Clothe();
    }
}