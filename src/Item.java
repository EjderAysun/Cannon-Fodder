public class Item implements IItem { // Can be carried and used by the characters.

    private String name;
    private double weight;

    public String getName(){
        return this.name;
    }
    public double getWeight(){
        return this.weight;
    }

    public void setName(String currentName){
        this.name = currentName;
    }
    public void setWeight(double currentWeight){
        this.weight = currentWeight;
    }

    public Item(){
        this.name = "nothing";
        this.weight = 0;
    }

    public Item(String nameParam, double weightParam){
        this.name = nameParam;
        this.weight = weightParam;
    }

    @Override
    public double getSpecialDamage(Character character) {
        return 0;
    }

    @Override
    
    public double getArmorScore() {
        return 0;
    }
}