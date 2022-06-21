import java.util.ArrayList;
import java.util.Map;
import java.security.SecureRandom;
import java.text.DecimalFormat;

public abstract class Character{ // Each character can wield (hold)
    // ...a single weapon only and wear a single armor only.
    // The characters will also have an inventory and will be able to carry items.
    // The sum of the weights of the items a character carries cannot
    // ...be greater than the character’s strength value.

    private double strength; // This basically affects how strong they can hit.
    // The sum of the weights of the items a character carries cannot be greater
    // ...than the character’s strength value.
    private double vitality; // Vitality determines their health.
    private double intelligence; // Intelligence is used for their special abilities.
    private double maxHP; // has not set method
    // It cannot be healed more than that.
    private double currentCharacterHP;
    private String name;
    private Weapon weapon;

    protected abstract double getCurrentTotalHP();
    protected abstract Clothe getClothe();
    protected abstract Inventory getInventory();
    protected abstract void setClothe(Clothe currentClothe);

    private static final DecimalFormat df = new DecimalFormat();

    public double getStrength(){
        return this.strength;
    }
    public double getVitality(){
        return this.vitality;
    }
    public double getIntelligence(){
        return this.intelligence;
    }
    public double getmaxHP(){
        this.maxHP = 0.7 * vitality + 0.2 * strength + 0.1 * intelligence;
        return this.maxHP;
    }
    public double getCurrentCharacterHP(){
        return this.currentCharacterHP;
    }
    public String getName(){
        return this.name;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }


    public void setStrength(double currentStrength){
        this.strength = currentStrength;
    }
    public void setVitality(double currentVitality){
        this.vitality = currentVitality;
    }
    public void setIntelligence(double currentIntelligence){
        this.intelligence = currentIntelligence;
    }
    public void setCurrentCharacterHP(double newCurrentHP){
        this.currentCharacterHP = newCurrentHP;
    }
    public void setName(String currentName){
        this.name = currentName;
    }
    public void setWeapon(Weapon currentWeapon){
        this.weapon = currentWeapon;
    }

    public Character(){
        this.strength = 0;
        this.vitality = 0;
        this.intelligence = 0;
        this.currentCharacterHP = 0;
        this.name = "nothing";
        this.weapon = new Weapon();
    }

    public Character(double strengthParam,double vitalityParam, double intelligenceParam, String nameParam){
        this.strength = strengthParam;
        this.vitality = vitalityParam;
        this.intelligence = intelligenceParam;
        this.maxHP = (0.7 * vitality + 0.2 * strength + 0.1 * intelligence);
        this.currentCharacterHP = this.maxHP;
        this.name = nameParam;
    }

    public static void Attack(Character attackerWarrior, EnemySoldier whichEnemyTakesAction, ArrayList<EnemySoldier> enemySoldierArrayList, ArrayList<String> namesOfLivingEnemySoldiers, Map<String, Integer> whoAndHowManyTurnsForSpecialPowerOfSword){  // The attack method, which is the most functional and comprehensive algorithm of the game

        double damageOfAttackerWarrior = attackerWarrior.getWeapon().getSpecialDamage(attackerWarrior);  // Attacker1's damage with a weapon is calculated specifically for the weapon.
        double TotalHPOfEnemy = whichEnemyTakesAction.getCurrentTotalHP();  // Enemy's total HP. Since the enemy does not have a shield, the current character's health is brought directly.
        String whichEnemyIsFighting = whichEnemyTakesAction.getName();  // Brings the name of the enemy
        String nameOfAttackerWarrior = attackerWarrior.getName();  // Brings the warrior's name

        // The above variables are introduced in an explanatory manner to reduce complexity in the algorithm below.


        System.out.print(nameOfAttackerWarrior + " does " + df.format(damageOfAttackerWarrior) + " damage. "); // The warrior's damage is printed on the screen.

        if(damageOfAttackerWarrior >= TotalHPOfEnemy) {  // If the warrior's damage is greater than the enemy's health, the enemy dies.
            enemySoldierArrayList.remove(whichEnemyTakesAction);  // The dead enemy is deleted from the list. The enemy in the first index is always fightable.
            namesOfLivingEnemySoldiers.remove(whichEnemyIsFighting);
            Action.howManyEnemiesWereKilled += 1;  // The game score depends on the enemy killed.
            System.out.print(whichEnemyIsFighting + " is dead. ");  // The dead enemy is printed on the screen.
            Action.whichItemWasDropped = dropRandomItem();  // A random item is fetched from the list containing the string of all items. There is a possibility that nothing was returned because there is also an item in the list without parameters (null).
            System.out.println(whichEnemyIsFighting + " drops a " + Action.whichItemWasDropped.getName());  // What the dead enemy dropped is printed on the screen.
            //if(enemySoldierArrayList.size() == 0){  // The round can be passed when there are no enemies left on the enemy list.
                ////}
        } else {
            whichEnemyTakesAction.setCurrentCharacterHP(TotalHPOfEnemy - damageOfAttackerWarrior);
            System.out.println(whichEnemyIsFighting + " has " + df.format(whichEnemyTakesAction.getCurrentCharacterHP()) + " HP left." );
        }
    }

    public static void enemyAttack(ArrayList<EnemySoldier> enemySoldierArrayList, ArrayList<String> namesOfLivingEnemySoldiers, EnemySoldier whichEnemyTakesAction, Map<String, Integer> whoAndHowManyTurnsForSpecialPowerOfSword){  // another functional method, used when the enemy is about to attack

            double damageOfEnemy = whichEnemyTakesAction.getWeapon().getSpecialDamage(whichEnemyTakesAction);  // Attacker1's damage with a weapon is calculated specifically for the weapon.
            String whichEnemyIsFighting = whichEnemyTakesAction.getName();  // Brings the name of the enemy
            Character defenderWarrior = whichCharacterWillTheEnemyAttack();
            String nameOfDefenderWarrior = defenderWarrior.getName();
            double totalHPOfDefenderWarrior = defenderWarrior.getCurrentTotalHP();  // Brings the warrior's health, including the shield.
            double HPOfDefenderWarrior = defenderWarrior.getCurrentCharacterHP();  // Brings the warrior's health without the shield involved.
    
            System.out.println(whichEnemyIsFighting + " attacks " + nameOfDefenderWarrior);
            System.out.print(whichEnemyIsFighting + " does " + df.format(damageOfEnemy) + " damage. ");
    
            if(whoAndHowManyTurnsForSpecialPowerOfSword.keySet().contains(nameOfDefenderWarrior)){
                System.out.println("The enemy attacked " + nameOfDefenderWarrior + ". " + nameOfDefenderWarrior + "was not damaged as he was under protection. This tour is in our favor!");
            } else {
                if(damageOfEnemy >= totalHPOfDefenderWarrior){ 
                    System.out.println(nameOfDefenderWarrior + " is dead.");
                    Action.listOfLivingWarriors.remove(defenderWarrior);
                    Action.namesOfLivingWarriors.remove(defenderWarrior.getName());
                    if(Action.listOfLivingWarriors.size() == 0){
                        System.out.println("GAME OVER!");
                        System.out.println("Your score: " + Action.howManyEnemiesWereKilled);
                        System.out.println("The last warrior also said that as he died, the pain was a weakness leaving the body. ");
                        if(Action.haveTheCharactersEverHurtEachOther) System.out.println("Although they hurt each other, the three of them bravely defended their country together...");
                        else System.out.println("The three of them bravely defended their country together...");
                        System.out.println("THE SCREEN WILL TURN OFF AFTER 20 SECONDS.");
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        System.exit(0);
                    }
    
                } else {
        
                    double armorScoreOfDefenderWarrior = defenderWarrior.getClothe().getArmorScore();  // Brings the warrior's shield score.
        
                    if (armorScoreOfDefenderWarrior > 0){
                        if(damageOfEnemy >= armorScoreOfDefenderWarrior) {
                            defenderWarrior.getClothe().setArmorScore(0);
                            defenderWarrior.setCurrentCharacterHP(HPOfDefenderWarrior - (damageOfEnemy - armorScoreOfDefenderWarrior));
                            //System.out.println(whichEnemyIsFighting + " does " + damageOfEnemy + " damage. " + whichCharacterIsFighting + " has " + attacker1.getCurrentTotalHP() + " HP left.");
                        }else {
                            defenderWarrior.getClothe().setArmorScore(armorScoreOfDefenderWarrior - damageOfEnemy);
                            //System.out.println(whichEnemyIsFighting + " does " + damageOfEnemy + " damage. " + whichCharacterIsFighting + " has " + attacker1.getCurrentTotalHP() + " HP left.");
                        }
                    } else {
                        defenderWarrior.setCurrentCharacterHP(HPOfDefenderWarrior - damageOfEnemy);
                    }
                    System.out.println(nameOfDefenderWarrior + " has " + df.format(defenderWarrior.getCurrentTotalHP()) + " HP left.");
                }
            }
        }

    private static Character whichCharacterWillTheEnemyAttack(){  // this method returns which character the enemy will attack, if the tank is alive, the tank returns, if not, it returns a random character; if there is 1 character left, it returns this character
        
        for(Character warrior: Action.listOfLivingWarriors){
            if(warrior.getName().equals("Tank")){
                return warrior;
            }
        }

        SecureRandom sr = new SecureRandom();
        int size = Action.listOfLivingWarriors.size();

        if(size==2){
            int index = sr.nextInt(size);
            return Action.listOfLivingWarriors.get(index);
        } else {
            return Action.listOfLivingWarriors.get(0);
        }
    }

    private static Item dropRandomItem(){  // Returns one of 12 items at 60 percent, nothing at 40 percent
        SecureRandom sr = new SecureRandom();
        int possibility = sr.nextInt(1,6);
        if(possibility == 1 || possibility == 2){
            return new Item("nothing", 0);  // nothing

        } else {
            int index = sr.nextInt(Action.allItemArrayList.size());
            return Action.allItemArrayList.get(index);
        }
    }

    public static Map<String, Integer> attackFriendlyFire(Character attacker, Character dude, Map<String, Integer> whoAndHowManyTurnsForSpecialPowerOfSword){  // catches all exceptions in friendly fire, it is one of the most comprehensive methods and checks and returns the map of those who use the special power of the sword

        Map<String, Integer> whoAndHowManyTurnsForSpecialPowerOfSwordReturn = whoAndHowManyTurnsForSpecialPowerOfSword;

        boolean isDead = false;

        if(attacker.getName().equals(dude.getName())){
            System.out.println("I self-harm because I have mental problems!");
            System.out.println("Yes bro, I agree with you. You are totally problem.");
    
            double HPOfDude = dude.getCurrentCharacterHP();
            double damageOfAttacker = attacker.getWeapon().getSpecialDamage(attacker);
            String nameOfDude = dude.getName();
    
            if(damageOfAttacker >= HPOfDude){
                System.out.println("I think I killed myself.");
                Action.listOfLivingWarriors.remove(dude);
                Action.namesOfLivingWarriors.remove(nameOfDude);
                if(Action.listOfLivingWarriors.size() == whoAndHowManyTurnsForSpecialPowerOfSword.size()){
                    System.out.println(Action.listOfLivingWarriors.get(0).getName()+ " has now been disabled as there is only one warrior left. Otherwise the round can never be triggered.");
                    whoAndHowManyTurnsForSpecialPowerOfSwordReturn.remove(Action.namesOfLivingWarriors.get(0));
                }
                isDead = true;
            } else {
                System.out.println("I think I am enjoying it.");
                dude.setCurrentCharacterHP(HPOfDude - damageOfAttacker);
                System.out.println(nameOfDude + " has " + df.format(dude.getCurrentTotalHP()) + " HP left.");
                //System.out.println("OMG! Bro?");
            }
        } else {
            double damageOfAttacker = attacker.getWeapon().getSpecialDamage(attacker);
            double damageOfDude = dude.getWeapon().getSpecialDamage(dude) / 3;
            double HPOfDude = dude.getCurrentCharacterHP();
            double HPOfAttacker = attacker.getCurrentCharacterHP();
            String nameOfDude = dude.getName();
            String nameOfAttacker = attacker.getName();
        
            System.out.println("OMG! Bro?");
            System.out.println(nameOfAttacker + " does " + df.format(damageOfAttacker) + " damage.");
        
            if(damageOfAttacker >= HPOfDude){
                System.out.println("I'll be dead before the pain in my back is gone, but that's time enough for you to taste my gun!");
                Action.listOfLivingWarriors.remove(dude);
                Action.namesOfLivingWarriors.remove(dude.getName());
                if(Action.listOfLivingWarriors.size() == whoAndHowManyTurnsForSpecialPowerOfSword.size()){
                    System.out.println(Action.listOfLivingWarriors.get(0).getName()+ " has now been disabled as there is only one warrior left. Otherwise the round can never be triggered.");
                    whoAndHowManyTurnsForSpecialPowerOfSwordReturn.remove(Action.namesOfLivingWarriors.get(0));
                }
                isDead = true;
            } else {
                dude.setCurrentCharacterHP(HPOfDude - damageOfAttacker);
                System.out.println(nameOfDude + " has " + df.format(dude.getCurrentTotalHP()) + " HP left.");
                //System.out.println("OMG! Bro?");
                System.out.println("You will have the honor of tasting my gun!");
            }
        
            System.out.println(nameOfDude + " attacks " + nameOfAttacker);
            System.out.print(nameOfDude + " does " + df.format(damageOfDude) + " damage. ");
        
            if(damageOfDude >= HPOfAttacker ){  // Since the attacked character is injured, he can be hit with a third of his damage.
                System.out.println(nameOfAttacker + " is dead.");
                Action.listOfLivingWarriors.remove(attacker);
                Action.namesOfLivingWarriors.remove(attacker.getName());
                if(Action.listOfLivingWarriors.size() == whoAndHowManyTurnsForSpecialPowerOfSword.size()){
                    System.out.println(Action.listOfLivingWarriors.get(0).getName()+ " has now been disabled as there is only one warrior left. Otherwise the round can never be triggered.");
                    whoAndHowManyTurnsForSpecialPowerOfSwordReturn.remove(Action.namesOfLivingWarriors.get(0));
                }
            } else {
                //System.out.println(nameOfDude + " attacks " + nameOfAttacker);
                //System.out.print(nameOfDude + " does " + damageOfDude + " damage. ");
                attacker.setCurrentCharacterHP((HPOfAttacker - damageOfDude));
                System.out.println(nameOfAttacker + " has " + df.format(attacker.getCurrentTotalHP()) + " HP left." );
            }
    
            Action.haveTheCharactersEverHurtEachOther = true;
    
        }
    
        if(isDead){
            System.out.println(dude.getName() + " is dead. ");
        }

        return whoAndHowManyTurnsForSpecialPowerOfSwordReturn;
    }
}