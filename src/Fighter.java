import java.security.SecureRandom;
import java.util.ArrayList;

public class Fighter extends Character {
    
    private Inventory inventory;
    private Clothe clothe;

    // recalculated every time it is called
    public double getCurrentTotalHP(){
        return clothe.getArmorScore() + getCurrentCharacterHP();
    }
    protected Clothe getClothe(){
        return this.clothe;
    }
    protected Inventory getInventory(){
        return this.inventory;
    }

    public void setClothe(Clothe currentClothe){
        this.clothe = currentClothe;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Fighter(){
        super();
        this.inventory = new Inventory();
    }

    public Fighter(double strengthParam,double vitalityParam, double intelligenceParam, String nameParam,
    ArrayList<Sword> swordArrayListParam){
        super(strengthParam, vitalityParam, intelligenceParam, nameParam);

        // While creating the fighter, you are given a random weapon

        SecureRandom sr = new SecureRandom();
        int index = sr.nextInt(swordArrayListParam.size());
        setWeapon(swordArrayListParam.get(index));

        this.inventory = new Inventory();
        this.clothe = new Clothe();
    }
}