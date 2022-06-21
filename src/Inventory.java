import java.text.DecimalFormat;
import java.util.ArrayList;

public class Inventory {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    private ArrayList<Item> itemInventory = new ArrayList<>();
    double howMuchInventoryCapacityWasUsed;

    public ArrayList<Item> getItemArrayList(){
        return this.itemInventory;
    }

    // item setting method with control chart
    public boolean setItem(Character character){

        if((howMuchInventoryCapacityWasUsed + Action.whichItemWasDropped.getWeight() + character.getWeapon().getWeight() + character.getClothe().getWeight()) > character.getStrength()){
            System.out.println("This item cannot be pick. Inventory is not enough.");
            System.out.println("In order to add this item to your inventory, you have to remove something from your inventory.");
            System.out.println("But you can't delete it because this feature is not available at the moment, so pick the weapons you want to add your inventory carefully.");
            return false;
        }else{
            this.itemInventory.add(Action.whichItemWasDropped);
            howMuchInventoryCapacityWasUsed += Action.whichItemWasDropped.getWeight();
            System.out.println("This item has been added to your inventory. You can now use it.");
            return true;
        }
    }

    public Inventory(){
        this.itemInventory = new ArrayList<>();
    }

    public static void listInventory(ArrayList<Character> listOfLivingWarriors, String nameOfTargetWarrior){  // method for listing items in inventory and on hand

        Character targetWarrior = null;

        for(Character warrior: listOfLivingWarriors){
            if(warrior.getName().equals(nameOfTargetWarrior)){
                targetWarrior = warrior;
                break;
            }
        }

        String weaponOfTargetWarrior = targetWarrior.getWeapon().getName();
        String armorOfTargetWarrior = targetWarrior.getClothe().getName();
        ArrayList<Item> itemArrayListOfTargetWarrior = targetWarrior.getInventory().getItemArrayList();

        System.out.println(nameOfTargetWarrior + " wields " + weaponOfTargetWarrior + ", wears " + armorOfTargetWarrior + ".");
        System.out.println("****   Inventory   ****");

        if(itemArrayListOfTargetWarrior.size() == 0){
            System.out.println("Nothing.");
        } else {
            for(Item itemOfTargetWarrior: itemArrayListOfTargetWarrior){
                System.out.println(itemOfTargetWarrior.getName() + ", ");
            }
            System.out.println();
        }
    }

    public static void examine(Character whichWarriorTakesAction, String action2){  // To inspect the item, the method prints all the properties of the target item.

        if(Action.whichItemWasDropped.getName().toString().equals(action2)) {
            if(Action.whichItemWasDropped.getClass().getName().equals("Clothe")){
                System.out.println(Action.whichItemWasDropped.getName() + " has " + df.format(Action.whichItemWasDropped.getArmorScore()) + " armor score, and " + df.format(Action.whichItemWasDropped.getWeight()) + " unit(s) of weight.");
            } else {
                System.out.println(Action.whichItemWasDropped.getName() + " has " + df.format(Action.whichItemWasDropped.getSpecialDamage(whichWarriorTakesAction)) + " damage, and " + df.format(Action.whichItemWasDropped.getWeight()) + " unit(s) of weight.");
            }
        } else {

            boolean isThisItemInInventory = false;

            for(Item item: whichWarriorTakesAction.getInventory().getItemArrayList()){
                if(item.getName().equals(action2)){
                    if(item.getClass().getName().equals("Clothe")){
                        System.out.println(item.getName() + " has " + df.format(item.getArmorScore()) + " armor score, and " + df.format(item.getWeight()) + " unit(s) of weight.");
                    } else {
                        System.out.println(item.getName() + " has " + df.format(item.getSpecialDamage(whichWarriorTakesAction)) + " damage, and " + df.format(item.getWeight()) + " unit(s) of weight.");
                    }
                    isThisItemInInventory = true;
                }
            }
    
            if(!isThisItemInInventory){
                if(whichWarriorTakesAction.getWeapon().getName().equals(action2)){
                    Item item = whichWarriorTakesAction.getWeapon();
                    System.out.println(item.getName() + " has " + df.format(item.getSpecialDamage(whichWarriorTakesAction)) + " damage, and " + df.format(item.getWeight()) + " unit(s) of weight.");
                } else if ( whichWarriorTakesAction.getClothe().getName().equals(action2)){
                    Item item = whichWarriorTakesAction.getClothe();
                    System.out.println(item.getName() + " has " + df.format(item.getArmorScore()) + " armor score, and " + df.format(item.getWeight()) + " unit(s) of weight.");
                } else {
                    System.out.println("The item you want to inspect is neither in your inventory, nor on you, nor on the ground.");
                }
            }
        }
    }

    public static boolean pick(Character whichWarriorTakesAction, String action2){  // Method to pick up the item from the ground, you can't wear or hold the item without picking it up. If the item is in your inventory or in your possession, you still cannot wear or keep it.

        if(Action.whichItemWasDropped.getName().equals("nothing")){
            System.out.println("Enemy did not drop any items or the dropped item was received by your teammate");
            return true;
        } else if (!action2.equals(Action.whichItemWasDropped.getName())) {
            System.out.println("The item that fell to the ground in the last round is not the item you wrote. " + Action.whichItemWasDropped.getName() + " item dropped to the ground in the last round.");
            return false;
        } else {  // The item written is the item that fell on the exact ground.

            if(whichWarriorTakesAction.getWeapon().getName().equals(Action.whichItemWasDropped.getName())){
                System.out.println("You are already wielding this item.");
                return false;
            } else if (whichWarriorTakesAction.getClothe().getName().equals(Action.whichItemWasDropped.getName())){
                System.out.println("You are already wearing this item.");
                return false;
            } else {

                for(Item item: whichWarriorTakesAction.getInventory().getItemArrayList()){
                    if(item.getName().equals(Action.whichItemWasDropped.getName())){
                        System.out.println("This item is already in your inventory.");
                        return false;
                    }
                }

                return whichWarriorTakesAction.getInventory().setItem(whichWarriorTakesAction);
            } 
        }
    }

    public static void wield(Character whichWarriorTakesAction, String action2){  // method for wielding the weapon in your inventory

        ArrayList<Item> envanter = whichWarriorTakesAction.getInventory().getItemArrayList();

        boolean doesItContainInventoryThisItem = false;

        for(Item item: envanter){

            if((item.getName().equals(action2)) && (item.getClass().getName().equals("Clothe"))){
                System.out.println(action2 + " can be worn, can not be wielded.");
                doesItContainInventoryThisItem = true; // just a lock
                break;
            } else if (item.getName().equals(action2)){  // if this is true, action[2] is absolutely weapon

                Weapon oldWeapon = whichWarriorTakesAction.getWeapon();

                whichWarriorTakesAction.getInventory().getItemArrayList().remove(item);

                for(Weapon weapon: Action.weaponArrayList){
                    if(weapon.getName().equals(action2)){
                        whichWarriorTakesAction.setWeapon(weapon);
                        break;
                    }
                }
                
                whichWarriorTakesAction.getInventory().getItemArrayList().add(oldWeapon);

                System.out.println(whichWarriorTakesAction.getName()+ " is now wielding " + action2 + ". " + action2 + " has been removed from inventory, and the weapon you wield, " + oldWeapon.getName() + ",(maybe nothing) has been put in the inventory." );
                
                doesItContainInventoryThisItem = true;

                break;
                
            }
        }

        if(!doesItContainInventoryThisItem) {
            System.out.println("This item is not in your inventory. If it's on the ground, you may have forgotten to pick it up.");
        }
    }

    public static void wear(Character whichWarriorTakesAction, String action2){  // method for wielding the weapon in your inventory

        ArrayList<Item> envanter = whichWarriorTakesAction.getInventory().getItemArrayList();

        boolean doesItContainInventoryThisItem = false;

        for(Item item: envanter){

            String itemClassName = item.getClass().getName();

            if((item.getName().equals(action2)) && (itemClassName.equals("Sword") || itemClassName.equals("Shield") || itemClassName.equals("Wand"))){
                System.out.println(action2 + " can be wielded, can not be worn.");
                doesItContainInventoryThisItem = true;  // just for lock
                break;
            } else if (item.getName().equals(action2)) {  // if this is true, action[2] is absolutely clothe

                Clothe oldClothe = whichWarriorTakesAction.getClothe();

                whichWarriorTakesAction.getInventory().getItemArrayList().remove(item);

                for(Clothe clothe: Action.clotheArrayList){
                    if(clothe.getName().equals(action2)){
                        whichWarriorTakesAction.setClothe(clothe);
                        break;
                    }
                }

                if(!oldClothe.getName().equals("nothing")){
                    whichWarriorTakesAction.getInventory().getItemArrayList().add(oldClothe);
                }

                System.out.println(item.getName() + " is now wearing " + action2 + ". " + action2 + " has been removed from inventory, and the clothe you wield, " + oldClothe.getName() + "(maybe nothing), has been put in the inventory." );
                
                doesItContainInventoryThisItem = true;

                break;
                
            }
        }

        if(!doesItContainInventoryThisItem) {
            System.out.println("This item is not in your inventory. If it's on the ground, you may have forgotten to pick it up.");
        }
    }
}