public class Weapon extends Item implements IWeapon {  // this class is a subclass of Item Class and has implemented the IWeapon interface.
    // Can be wielded by characters.
    // Any character can wield any weapon,
    // ...however their damage changes depending on the attributes of the character.
    // Each weapon has an attack action.

    // Each weapon has a special action:
    // Wands heal,
    // Shields stun enemies (temporary inactivity of a character)
    // Swords can keep the enemies away for a single turn,
    // ...giving the character that wields it to not take damage,
    // ...but also not to cause any damage for a few turns.
    
    private double defDamage;  // variable for default weapon damage

    public double getDefDamage(){  // get method for default damage
        return this.defDamage;  // return this defDamage variable
    }

    public void setDamage(double currentDefDamage){  // set method for current default damage (we enter the values ​​externally as they may need to change)
        this.defDamage = currentDefDamage;  // equal this defDamage variable to currentDefDamage
    }

    public Weapon(){  // non-parameterized constructor
        super();  // using super to call the superclass of Weapon (subclass)
        this.defDamage = 0;  // defDamage variable is zero if no parameter
    }

    public Weapon(String nameParam, double weightParam, double defDamageParam){  // parameterized constructor
        super(nameParam, weightParam);  // using super to call the superclass of Weapon (subclass)
        this.defDamage = defDamageParam;  // equal this defDamage variable to defDamageParam
    }

	@Override  // override token
	public double getSpecialDamage(Character character) {
        return 0; 
    //  The character class has a combination of weapons used.
    //  We needed to access subclasses of the Weapon class from the Character class and then pull this method used in subclasses
    //  If we made the Weapon class an abstract class, we wouldn't be able to use composition.
    //  For this reason, we applied the IWeapon interface to the Weapon class to access this method over Weapon in Weapon's subclasses.
    //  We override this method in the Weapon class and again in the Weapon class subclasses.
    //  In this way, we were able to use this method specifically for each Weapon subclass used in the Character class.


    //  Why didn't we write the method on the Weapon class?

    //  Because this method performs a special task for each Weapon subclass (Shield, Wand, Sword).
    //  However, in the Character class, we had to access these special mission methods through the Weapon class.
    //  Because we were using the getWeapon() method in a method in the Character class, and this method would bring either Sword or Shield or Wand.
    //  In other words, a common method name used in Weapon had to somehow do a special operation for each class in subclasses and we had to be able to use it over Weapon.
    }

	@Override  // override token
	public void specialPower() {}
}