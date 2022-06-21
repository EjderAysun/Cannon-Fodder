import java.text.DecimalFormat;

public class Wand extends Weapon{
    // Wand => Default Damage * Intelligence of Character

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double getSpecialDamage(Character character){
        return character.getIntelligence() * getDefDamage();
    }

    public static void specialPower(Character characterToBeHealed, Character healerCharacter){  // method for wand's special power

        double currentHPOfCharacterToBeHealed = characterToBeHealed.getCurrentCharacterHP();
        double healToBeGiven = healerCharacter.getIntelligence();  // Wand; can give life as much as the intelligence of the character using it
        double maxHPOfCharacterToBeHealed = characterToBeHealed.getmaxHP();
        
        if((healToBeGiven + currentHPOfCharacterToBeHealed) > maxHPOfCharacterToBeHealed) {
            characterToBeHealed.setCurrentCharacterHP(maxHPOfCharacterToBeHealed);
            System.out.println(characterToBeHealed.getName() + " was given " + df.format(maxHPOfCharacterToBeHealed - currentHPOfCharacterToBeHealed) + " heal.");
            System.out.println(characterToBeHealed.getName() + "'s health is maxed out as the health that can be given exceeds the max health.");
        } else {
            characterToBeHealed.setCurrentCharacterHP(currentHPOfCharacterToBeHealed + healToBeGiven);
            System.out.println(characterToBeHealed.getName() + " was given " + df.format(healToBeGiven) + " heal.");
        }
    }

    public Wand(){
        super();
    }

    public Wand(String nameParam, double weightParam, double defDamageParam){
        super(nameParam, weightParam, defDamageParam);
    }
}