import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class Action {  // main class

    private static Scanner sc = new Scanner(System.in);
    private static SecureRandom sr = new SecureRandom();
    private static final DecimalFormat df = new DecimalFormat("0.00");  // to print double numbers in 0.00 format

    public static int howManyEnemiesWereKilled = 0;
    public static boolean haveTheCharactersEverHurtEachOther = false;

    public static ArrayList<Sword> swordArrayList = new ArrayList<>();  // create an empty Sword ArrayList to hold all Swords
    public static ArrayList<Shield> shieldArrayList = new ArrayList<>();  // create an empty Shield ArrayList to hold all Shields
    public static ArrayList<Wand> wandArrayList = new ArrayList<>();  // create an empty Wand ArrayList to hold all Wands
    public static ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    public static ArrayList<Clothe> clotheArrayList = new ArrayList<>();  // create an empty Clothe ArrayList to hold all Clothes
    public static ArrayList<String> actionArrayList = new ArrayList<>();  // create an empty String ArrayList to hold all action names
    public static ArrayList<String> namesOfLivingWarriors = new ArrayList<>();  // create an empty String ArrayList to hold all warrior names
    public static ArrayList<String> itemArrayList = new ArrayList<>();  // create an empty String ArrayList to hold all item names
    public static ArrayList<Item> allItemArrayList = new ArrayList<>();  // create an empty Item ArrayList to hold all Items (Swords, Shields, Wands, Clothes)
    public static ArrayList<Character> listOfLivingWarriors = new ArrayList<>();  // create an empty listOfLivingArrayList ArrayList to hold all living warrior(s)
    private static int level = 0;  // variable for level, first level is => level 0

    public static Item whichItemWasDropped = new Item();  // variable for to hold the dropped item, item drop probability is 3/5
    public static Map<String, Integer> whoAndHowManyTurnsForSpecialPowerOfSword = new HashMap<>();
    
    public static void main(String[] args) {

        printHowIsTheGamePlayed();
        System.out.println("--------------------------------------------");

        DevRoom.createItems();  // create the items with the createItems method in the DevRoom class and add them into the related ArrayLists (via method)
        DevRoom.createCommand();  // create the possible inputs with the createCommand method in the DevRoom class and add them into the related ArrayLists (via method)

        allItemArrayList.addAll(swordArrayList);  // add SwordArrayList ArrayList contents into the allItemArrayList ArrayList
        allItemArrayList.addAll(shieldArrayList);  // add shieldArrayList ArrayList contents into the allItemArrayList ArrayList
        allItemArrayList.addAll(wandArrayList);  // add wandArrayList ArrayList contents into the allItemArrayList ArrayList
        allItemArrayList.addAll(clotheArrayList);  // add clotheArrayList ArrayList contents into the allItemArrayList ArrayList
        //System.out.println(allItemArrayList);

        weaponArrayList.addAll(swordArrayList);
        weaponArrayList.addAll(shieldArrayList);
        weaponArrayList.addAll(wandArrayList);
        
        Fighter fighter = createFighter();  // create a fighter with createFighter method
        Tank tank = createTank();  // create a tank with createTank method
        Healer healer = createHealer();  // create a healer with createHealer method

        addCharactersToArrayList(fighter, tank, healer);  // add all warriors into the listOfLivingWarriors ArrayList with addCharactersToArrayList method
        printCharacters(fighter, tank, healer);  // print all warriors and their attributes with printCharacters method

        while(true){  // while loop for level

            ArrayList<EnemySoldier> enemySoldierArrayList = new ArrayList<>();  // create an empty enemySoldierArrayList to hold all living enemies
            ArrayList<String> namesOfLivingEnemySoldiers = new ArrayList<>();
            
            int areThereHowManyEnemy = ((int) Math.pow(2, level));  // calculate how many enemies need to be created for the current level
            
            for(int i = 0; i < areThereHowManyEnemy; i++){  // for loop for create enemy and add enemies into the enemySoldierArrayList ArrayList
                String nameOfEnemySoldier = "Enemy" + (i + 1);  // give the created enemy a name, for example 'Enemy9'
                EnemySoldier enemySoldier = new EnemySoldier(sr.nextInt(1, 6), sr.nextInt(1, 6), sr.nextInt(1, 6), nameOfEnemySoldier, swordArrayList, shieldArrayList, wandArrayList);  // create EnemySoldier character and give it some attributes(randomly), origin inclusive bound exclusive
                enemySoldierArrayList.add(enemySoldier);  // add enemy soldier into the enemySoldierArrayList ArrayList
                namesOfLivingEnemySoldiers.add(nameOfEnemySoldier);
            }

            System.out.println("Creating level " + level + ", with " + areThereHowManyEnemy + " enemy soldier.");  // print how many enemies the level was created with
            System.out.print("Entering level " + level + "; ");  // print those who entered the level

            for(String characterOfLivingWarrior: namesOfLivingWarriors){  // for loop for to print characters in the characterOfLivingWarrior ArrayList
                System.out.print(characterOfLivingWarrior + " enters. ");  // get and print the name of the living character
            }
            for(String characterOfEnemy: namesOfLivingEnemySoldiers){  // for loop for to print enemies in the enemySoldierArrayList ArrayList
                System.out.print(characterOfEnemy + " enters. ");  // get and print the name of the living enemies
            }

            //boolean isItNextable = false;
            System.out.println();

            System.out.println("--------------------------------------------");

            while(true){  // while loop for round

                String[] action = takeTheMove(namesOfLivingEnemySoldiers);  // get a correct action from the player and assign it to the action variable
                System.out.println("--------------------------------------------");

                if(action[0].equals("NEXT")){  // condition to pass level
                    level += 1;  // If the level is passable, increase the level variable by one
                    whichItemWasDropped = new Item();  // variable for to hold the dropped item, item drop probability is 3/5
                    whoAndHowManyTurnsForSpecialPowerOfSword = new HashMap<>();
                    for(Character warrior: listOfLivingWarriors){  // for loop to increase the characteristics of the characters before moving on to the next level 
                        warrior.setStrength(warrior.getStrength() * (4 / 3));  // multiply the warrior's strength by 4/3 and set
                        warrior.setVitality(warrior.getVitality() * (4 / 3));  // multiply the warrior's vitality by 4/3 and set
                        warrior.setIntelligence(warrior.getIntelligence() * (4 / 3));  // multiply the warrior's intelligence by 4/3 and set
                    }
                    System.out.println("--------------------------------------------");
                    break;  // break while loop created for round
                }

                Character whichWarriorTakesAction = null;  // variable for to keep which character takes action

                for(Character warrior: listOfLivingWarriors) {
                    if(warrior.getName().equals(action[0])) {
                        whichWarriorTakesAction = warrior;
                        break;
                    }
                }

                EnemySoldier whichEnemyTakesAction = null;

                for(EnemySoldier enemy: enemySoldierArrayList){
                    if(enemy.getName().equals(action[2])){
                        whichEnemyTakesAction = enemy;
                        break;
                    }
                }

                switch(action[1]) {  // algorithms that will work according to actions

                    case "Attack":
                    case "attack": {

                        if(whoAndHowManyTurnsForSpecialPowerOfSword.containsKey(action[0])){ //
                            System.out.println(action[0] + " is non-functional. The protection period continues.");
                            System.out.println("You don't attack with " + action[0] + ".");
                            System.out.println("--------------------------------------------");
                            continue;
                        }

                        if (whoAndHowManyTurnsForSpecialPowerOfSword.containsKey(action[2])){  // lock the move to your teammate
                            System.out.println("You wanted to attack your friend, but he is now under protection. Who knows, maybe he just took himself under protection because he didn't trust you :)");
                            System.out.println("--------------------------------------------");
                            continue;
                        }

                        ArrayList<Integer> howManyTurn = new ArrayList<>(whoAndHowManyTurnsForSpecialPowerOfSword.values());
                        ArrayList<String> whichWarrior = new ArrayList<>(whoAndHowManyTurnsForSpecialPowerOfSword.keySet());
                        int size = whoAndHowManyTurnsForSpecialPowerOfSword.size();

                        for(int i = 0; i < size; i++){
                            if(howManyTurn.get(i) > 0){
                                int x = howManyTurn.get(i);
                                x--;
                                whoAndHowManyTurnsForSpecialPowerOfSword.replace(whichWarrior.get(i), x);
                                if(x == 0){

                                    whoAndHowManyTurnsForSpecialPowerOfSword.remove(whichWarrior.get(i));

                                }
                            }
                        }

                        if(itemArrayList.contains(action[2])){

                            System.out.println("You cannot attack an item!");

                        } else if (namesOfLivingWarriors.contains(action[2])){

                            Character dude = null;
                            for(Character warrior: listOfLivingWarriors){
                                if(warrior.getName().equals(action[2])){
                                    dude = warrior;
                                    break;
                                }
                            }
                            whoAndHowManyTurnsForSpecialPowerOfSword = Character.attackFriendlyFire(whichWarriorTakesAction, dude, whoAndHowManyTurnsForSpecialPowerOfSword);
                            if(!(enemySoldierArrayList.size() > 0)){
                                System.out.println("Be like when you attack your friend at the end of the tour. There are no more enemies to attack the warriors. Either kill each other or go to the next round.");
                            } else {
                                Character.enemyAttack(enemySoldierArrayList, namesOfLivingEnemySoldiers, enemySoldierArrayList.get(0), whoAndHowManyTurnsForSpecialPowerOfSword);
                            }
                            whichItemWasDropped = new Item();
                            System.out.println("If there is an item that fell on the ground in the last round, it can no longer be used.");
                        
                        } else {  // So the target is definitely a living enemy. Because in the exceptions, it was checked whether the enemy was alive or not.

                            int index = enemySoldierArrayList.indexOf(whichEnemyTakesAction);

                            boolean isTheLastEnemy = false;

                            if(index + 1 == enemySoldierArrayList.size()){
                                isTheLastEnemy = true;
                            }

                            Character.Attack(whichWarriorTakesAction, whichEnemyTakesAction, enemySoldierArrayList, namesOfLivingEnemySoldiers, whoAndHowManyTurnsForSpecialPowerOfSword);
                            
                            if(listOfLivingWarriors.size() == whoAndHowManyTurnsForSpecialPowerOfSword.size()){
                                System.out.println(listOfLivingWarriors.get(0).getName()+ " has now been disabled as there is only one warrior left. Otherwise the round can never be triggered.");
                                whoAndHowManyTurnsForSpecialPowerOfSword.remove(namesOfLivingWarriors.get(0));
                            }
                            
                            if(enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(whichEnemyTakesAction.getName())){
                                Character.enemyAttack(enemySoldierArrayList, namesOfLivingEnemySoldiers, whichEnemyTakesAction, whoAndHowManyTurnsForSpecialPowerOfSword);
                            } else if (!(enemySoldierArrayList.size() > 0)){
                                System.out.println("All enemies are destroyed, there are no more enemies to attack you.");
                            } else if (isTheLastEnemy){
                                Character.enemyAttack(enemySoldierArrayList, namesOfLivingEnemySoldiers, enemySoldierArrayList.get(index - 1), whoAndHowManyTurnsForSpecialPowerOfSword);
                            } else {
                                Character.enemyAttack(enemySoldierArrayList, namesOfLivingEnemySoldiers, enemySoldierArrayList.get(index), whoAndHowManyTurnsForSpecialPowerOfSword);
                            }
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }

                    case "specialaction":
                    case "SpecialAction": {
                    
                        if(whoAndHowManyTurnsForSpecialPowerOfSword.containsKey(action[0])) { //
                            System.out.println(action[0] + " is non-functional. The protection period continues.");
                            System.out.println("You don't attack with " + action[0] + ".");
                            System.out.println("--------------------------------------------");
                            continue;
                        }  

                        ArrayList<Integer> howManyTurn = new ArrayList<>(whoAndHowManyTurnsForSpecialPowerOfSword.values());
                        ArrayList<String> whichWarrior = new ArrayList<>(whoAndHowManyTurnsForSpecialPowerOfSword.keySet());
                        int size = whoAndHowManyTurnsForSpecialPowerOfSword.size();

                        for(int i = 0; i < size; i++){
                            if(howManyTurn.get(i) > 0){
                                int x = howManyTurn.get(i);
                                x--;
                                whoAndHowManyTurnsForSpecialPowerOfSword.replace(whichWarrior.get(i), x);
                                if(x == 0){

                                    whoAndHowManyTurnsForSpecialPowerOfSword.remove(whichWarrior.get(i));

                                }
                            }
                        }

                        String weapon = whichWarriorTakesAction.getWeapon().getClass().getName();

                        if(itemArrayList.contains(action[2])){

                            System.out.println("You can not use special action on item!");

                        } else if (namesOfLivingWarriors.contains(action[2])){

                            if(weapon.equals("Wand")){
                                
                                Character characterToBeHealed = null;
                                for(Character warrior: listOfLivingWarriors){
                                    if(warrior.getName().equals(action[2])){
                                        characterToBeHealed = warrior;
                                        break;
                                    }
                                }

                                if(enemySoldierArrayList.size() == 0){
                                    System.out.println("There are no more enemies to damage. The heal special power will be active when you reach the next level.");
                                } else {
                                    Wand.specialPower(characterToBeHealed, whichWarriorTakesAction);
                                    Character.enemyAttack(enemySoldierArrayList, namesOfLivingEnemySoldiers, enemySoldierArrayList.get(0),  whoAndHowManyTurnsForSpecialPowerOfSword);
                                    whichItemWasDropped = new Item();
                                    System.out.println("If there is an item that fell on the ground in the last round, it can no longer be used.");
                                }
                                
                            } else {
                                System.out.println("The special power of the sword or shield cannot be used on a warrior, it can be used on the enemy.");
                            }
                        } else {  // If it is not an item or a warrior on whom the special action will be applied, it is definitely the enemy at index 0.

                            if(weapon.equals("Sword")){

                                if(listOfLivingWarriors.size() == 1){
                                    System.out.println("When only one warrior remains, he cannot protect himself. It's just to delay death.");
                                    System.out.println("Make that damn shit and LET'S GET SOME GODDAMN GAINS!");
                                } else if (listOfLivingWarriors.size() - 1 == whoAndHowManyTurnsForSpecialPowerOfSword.size()){
                                    System.out.println("Not all warriors can use the power of the sword at the same time.");
                                } else if (enemySoldierArrayList.size() == 0){
                                    System.out.println("The tour can be passed. Using special power is pointless.");
                                } else {

                                    whoAndHowManyTurnsForSpecialPowerOfSword.put(action[0], level + 1);  // key => which  character inactive, value => how many turns inactive, this is sword's special power
        
                                    System.out.println("This turn, the enemies are completely immobile. They will be active in the next round.");
                                    whichItemWasDropped = new Item();
                                    System.out.println("If there is an item that fell on the ground in the last round, it can no longer be used.");
                                }
                            }
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }

                    case "listinventory":
                    case "ListInventory": {

                        if(itemArrayList.contains(action[2])){
                            System.out.println("An item has no inventory! Please show the target whose inventory you want to see!");
                        } else if(enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(action[2])) {
                            System.out.println("You cannot see an enemy's inventory!");
                        } else {  // action[2] is absolutely a living warrior
                            Inventory.listInventory(listOfLivingWarriors, action[2]);
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }

                    case "Examine":
                    case "examine": {

                        if(namesOfLivingWarriors.contains(action[2])){
                            System.out.println("Examining an warrior is unreasonable.");
                        } else if (enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(action[2])){
                            System.out.println("Examining an enemy is unreasonable.");
                        } else {
                            Inventory.examine(whichWarriorTakesAction, action[2]);
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }
                    
                    case "Pick":
                    case "pick": {

                        if(namesOfLivingWarriors.contains(action[2])) {
                            System.out.println("Picking an warrior is unreasonable.");
                        } else if(enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(action[2])) {
                            System.out.println("Picking an enemy is unreasonable.");
                        } else {
                            boolean x = Inventory.pick(whichWarriorTakesAction, action[2]);
                            if(x){
                                System.out.println( whichItemWasDropped.getName() + " was taken by " + whichWarriorTakesAction.getName() + ".");
                                whichItemWasDropped = new Item();
                            } else {
                                System.out.println(whichItemWasDropped.getName() + " is still on the ground.");
                            }
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }

                    case "Wield":
                    case "wield": {
                        if(namesOfLivingWarriors.contains(action[2])) {
                            System.out.println("Wielding an warrior is unreasonable.");
                        } else if (enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(action[2])) {
                            System.out.println("Wielding an enemy is unreasonable.");
                        } else {  // action[2] is absolutely item

                            Inventory.wield(whichWarriorTakesAction, action[2]);
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }

                    case "Wear":
                    case "wear": {
                        if(namesOfLivingWarriors.contains(action[2])) {
                            System.out.println("Wielding an warrior is unreasonable.");
                        } else if (enemySoldierArrayList.size() > 0 && namesOfLivingEnemySoldiers.contains(action[2])) {
                            System.out.println("Wielding an enemy is unreasonable.");
                        } else {  // action[2] is absolutely item

                            Inventory.wear(whichWarriorTakesAction, action[2]);
                        }
                        System.out.println("--------------------------------------------");
                        break;
                    }
                }
            }
        }
    }

    private static Fighter createFighter(){  // this method creates a Fighter
        
        return new Fighter(sr.nextInt(6, 11), sr.nextInt(3, 8), sr.nextInt(1, 6), "Fighter", swordArrayList);  // origin inclusive bound exclusive

    }

    private static Tank createTank(){  // this method creates a Tank
        
        return new Tank(sr.nextInt(1, 6), sr.nextInt(6, 11), sr.nextInt(3, 8), "Tank", shieldArrayList);  // origin inclusive bound exclusive
    }

    private static Healer createHealer(){  // this method creates a Healer
        
        return new Healer(sr.nextInt(3, 8), sr.nextInt(1, 6), sr.nextInt(6, 11), "Healer", wandArrayList);  // origin inclusive bound exclusive
    }

    private static void addCharactersToArrayList(Fighter fighter, Tank tank, Healer healer){  // this method adds characters to arraylist
        listOfLivingWarriors.add(fighter);
        listOfLivingWarriors.add(tank);
        listOfLivingWarriors.add(healer);
    }

    private static void printCharacters(Fighter fighter, Tank tank, Healer healer){  // this method prints the characters in the arraylist
        System.out.println("Fighter created with S:" + df.format(fighter.getStrength()) + ", V:" + df.format(fighter.getVitality()) + ", I:" + df.format(fighter.getIntelligence()) +". Fighter wields " + fighter.getWeapon().getName() + " with " + df.format(fighter.getWeapon().getSpecialDamage(fighter)) + " damage and " + df.format(fighter.getWeapon().getWeight()) + " unit(s) of weight.");
        System.out.println("Tank created with S:" + df.format(tank.getStrength()) + ", V:" + df.format(tank.getVitality()) + ", I:" + df.format(tank.getIntelligence()) +". Tank wields " + tank.getWeapon().getName() + " with " + df.format(tank.getWeapon().getSpecialDamage(tank)) + " damage and " + df.format(tank.getWeapon().getWeight()) + " unit(s) of weight.");
        System.out.println("Healer created with S:" + df.format(healer.getStrength()) + ", V:" + df.format(healer.getVitality()) + ", I:" + df.format(healer.getIntelligence()) +". Healer wields " + healer.getWeapon().getName() + " with " + df.format(healer.getWeapon().getSpecialDamage(healer)) + " damage and " + df.format(healer.getWeapon().getWeight()) + " unit(s) of weight.");
        System.out.println();
    }

    private static String[] takeTheMove(ArrayList<String> namesOfLivingEnemySoldiers){  // this method catches all exceptions and returns an appropriate action
        while(true){
            
            System.out.println("Please enter your move: ");
            String move = sc.nextLine();
            move.trim();

            String[] action = move.split("\\s");

            if(move.equals("NEXT")){
                if(namesOfLivingEnemySoldiers.size() == 0){ 
                    return action;
                }
                else System.out.println("The enemies are not yet completely destroyed. You must completely destroy the enemies before you can move on to the next level.");
                System.out.println("--------------------------------------------");
                continue;
            }

            if(!((move.split(" ").length - 1) == 2)){
                System.out.println("Please enter in 'Fighter attack Enemy2' or 'Fighter ListInventory Healer' format!");
                System.out.println("--------------------------------------------");
                continue;
            }

            String firstCommand = action[0];

            if(!(firstCommand.equals("Fighter") || firstCommand.equals("Healer") || firstCommand.equals("Tank"))){
                System.out.println("Your first word was entered incorrectly! Remember, you can only write 'Fighter', 'Tank' or 'Healer' in the first word!");
                System.out.println("--------------------------------------------");
                continue;
            }

            if(!(namesOfLivingWarriors.contains(firstCommand))){
                System.out.println("The " + action[0] + " is dead. You can't make moves with it.");
                System.out.println("--------------------------------------------");
                continue;
            }

            String secondCommand = action[1];
            
            if(!actionArrayList.contains(secondCommand)){
                System.out.println("Your second word was entered incorrectly! Remember, you can only write 'A(a)ttack', 'SpecialAction', 'specialaction',P(p)ick', 'W(w)ield', 'W(w)ear', 'E(e)xamine' , 'ListInventory', and 'listinventory' in the second word!");
                System.out.println("--------------------------------------------");
                continue;
            }

            String thirdCommand = action[2];

            if((thirdCommand.equals("Fighter") || thirdCommand.equals("Healer") || thirdCommand.equals("Tank"))){
                if(!namesOfLivingWarriors.contains(thirdCommand)){
                    System.out.println(thirdCommand + " is dead. You can't target him.");
                    System.out.println("--------------------------------------------");
                    continue;
                } else {
                    return action;
                }
            }

            ArrayList<String> potentialThirdCommand = new ArrayList<>();

            potentialThirdCommand.addAll(itemArrayList);
            potentialThirdCommand.addAll(namesOfLivingEnemySoldiers);

            //if(enemySoldierArray.size() > 0) {
                //potentialThirdCommand.add(enemySoldierArray.get(0).getName());
            //}

            if(!potentialThirdCommand.contains(thirdCommand)){
                potentialThirdCommand.addAll(namesOfLivingWarriors);
                System.out.print("Your third word was incorrectly! Remember, you can only write");
                for(String potentialCommand: potentialThirdCommand){
                    System.out.print(" '" + potentialCommand + "'");
                }
                System.out.println(" in the the third word!");
                System.out.println("--------------------------------------------");
                continue;
            }
            return action;
        }
    }

    private static void printHowIsTheGamePlayed(){
        System.out.println("How is the game played?");
        System.out.println();
        System.out.println("The game is a simple rpg game where you have 3 characters and you will encounter 2^n enemies in each turn.");
        System.out.println();
        System.out.println("In the game, you must write your moves in 3 words.");
        System.out.println("The first word can only be the character doing the action or 'NEXT'");
        System.out.println(" --->>> 'Fighter', 'Healer' or 'Tank', 'NEXT'");
        System.out.println(" The first word can only be written as shown above.");
        System.out.println();
        System.out.println("The second word should be an action.");
        System.out.println(" --->>> 'Attack', 'SpecialAction', 'Examine', 'Pick', 'Wield', 'Wear', 'ListInventory' second word");
        System.out.println(" if it's a single word, it can be capitalized (eg attack or attack)");
        System.out.println(" if it is two words, either all letters can be lowercase or initials capitalized, but they should be written together in all cases (for example, ListInventory or listinventory)");
        System.out.println();
        System.out.println("The third word should be the target.");
        System.out.println(" --->>> One of the living enemies (eg 'Enemy4'), any item ('LongSword', 'ShortSword', 'Scimitar', 'Buckler', 'SmallShield', 'TowerShield', 'WoodWand', 'BoneWand' ', 'FireWand', 'LightArmor', 'MediumArmor', 'HardArmor') or a warrior('Fighter', 'Tank', 'Healer').");
        System.out.println(" The third word can only be written as shown above.");
        System.out.println();
        System.out.println("Attack => This action allows you to attack the enemy, your teammate or yourself.  You will get different feedbacks depending on the target.");
        System.out.println();
        System.out.println("SpecialAction => This action allows you to use the special action of your character's weapon.");
        System.out.println("The special action of the sword is to immobilize enemies for 1 turn and level + 1 turn the character wielding the sword cannot take damage (the enemy can hit this character, but takes no damage. So if the enemy hits this character, it is to the player's advantage) and rendering it invulnerable.");
        System.out.println("The wand's special action is to inflict a certain amount of health on the target warrior.");
        System.out.println("The health given does not exceed the maximum health.");
        System.out.println("The shield is passive.  The shield has no special action.");
        System.out.println();
        System.out.println("Examine => With this action, you can examine the item on the ground, in your hand or in your inventory.");
        System.out.println();
        System.out.println("Pick => With this action, you can pick up the item on the ground.  If the item on the ground is not in your possession or inventory, it goes to your inventory.");
        System.out.println();
        System.out.println("Wield => With this action you can keep the weapon in your inventory.");
        System.out.println();
        System.out.println("Wear => With this action you can wear the clothing in your inventory.");
        System.out.println();
        System.out.println("List Inventory => With this action, you can see the properties of the items in your inventory and in your possession/on you.");
        System.out.println();
        System.out.println();
        System.out.println("NOTE: If you are going to the next round, just write 'NEXT'.");
        System.out.println();
        System.out.println();
        System.out.println("Once you pick up an item from the ground, you cannot leave it back.");
        System.out.println("When you change your weapon, the weapon in your hand goes to the inventory.");
        System.out.println("You cannot carry more weapons than your total strength.");
        System.out.println("That's why you should choose the weapons you buy very carefully.");
        System.out.println("Otherwise, at some point, you may become unable to pick up a weapon from the ground.");
    }
}