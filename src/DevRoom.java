public class DevRoom {

    // According to our research, in such RPG games, for example, in an online 3D RPG game,
    // all items are created at the very beginning of the game in a secret room at the bottom of the map.
    // This provides convenience to developers. During the game, the items are given to the players by sampling
    // the essence of the previously created items, if the conditions are met.

    // We tried to apply this logic here.

    public static void createItems(){
    
        Sword shortSword = new Sword("ShortSword", 0.1, 0.12);  //balance 0.14
        Sword longSword = new Sword("LongSword", 0.24, 0.25);  //balance 0.29
        Sword scimitar = new Sword("Scimitar", 0.42, 0.45);  //balance 0.57
    
        Action.swordArrayList.add(shortSword);
        Action.swordArrayList.add(longSword);
        Action.swordArrayList.add(scimitar);
    
        Shield buckler = new Shield("Buckler", 0.14, 0.06);  //balance
        Shield smallShield = new Shield("SmallShield", 0.3, 0.11);  //balance
        Shield towerShield = new Shield("TowerShield", 0.5, 0.16);  //balance
    
        Action.shieldArrayList.add(buckler);
        Action.shieldArrayList.add(smallShield);
        Action.shieldArrayList.add(towerShield);
    
        Wand woodWand = new Wand("WoodWand", 0.12, 0.09);  //balance
        Wand boneWand = new Wand("BoneWand", 0.28, 0.17);  //balance
        Wand fireWand = new Wand("FireWand", 0.46, 0.24);  //balance
    
        Action.wandArrayList.add(woodWand);
        Action.wandArrayList.add(boneWand);
        Action.wandArrayList.add(fireWand);
    
        Clothe lightArmor = new Clothe("LightArmor", 0.52, 5.0);  // balance
        Clothe mediumArmor = new Clothe("MediumArmor", 0.6, 10.0);  // balance
        Clothe hardArmor = new Clothe("HardArmor", 0.84, 15.0);  // balance
    
        Action.clotheArrayList.add(lightArmor);
        Action.clotheArrayList.add(mediumArmor);
        Action.clotheArrayList.add(hardArmor);
    }

    public static void createCommand(){

        Action.actionArrayList.add("attack");
        Action.actionArrayList.add("Attack");
        Action.actionArrayList.add("SpecialAction");
        Action.actionArrayList.add("specialaction");
        Action.actionArrayList.add("pick");
        Action.actionArrayList.add("Pick");
        Action.actionArrayList.add("wield");
        Action.actionArrayList.add("Wield");
        Action.actionArrayList.add("wear");
        Action.actionArrayList.add("Wear");
        Action.actionArrayList.add("examine");
        Action.actionArrayList.add("Examine");
        Action.actionArrayList.add("ListInventory");
        Action.actionArrayList.add("listinventory");
    
        Action.namesOfLivingWarriors.add("Fighter");
        Action.namesOfLivingWarriors.add("Tank");
        Action.namesOfLivingWarriors.add("Healer");
    
        Action.itemArrayList.add("ShortSword");
        Action.itemArrayList.add("LongSword");
        Action.itemArrayList.add("Scimitar");
        Action.itemArrayList.add("Buckler");
        Action.itemArrayList.add("SmallShield");
        Action.itemArrayList.add("TowerShield");
        Action.itemArrayList.add("WoodWand");
        Action.itemArrayList.add("BoneWand");
        Action.itemArrayList.add("FireWand");
        Action.itemArrayList.add("LightArmor");
        Action.itemArrayList.add("MediumArmor");
        Action.itemArrayList.add("HardArmor");

    }
}