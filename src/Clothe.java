public class Clothe extends Item { // Can be worn by characters and they provide protection.

    private double defArmorScore;

    public double getArmorScore(){
        return defArmorScore;
    }

    public void setArmorScore(double currentDefArmorScore){
        this.defArmorScore = currentDefArmorScore;
    }

    public Clothe(){
        super();
        defArmorScore = 0.0;
    }

    public Clothe(String nameParam, double weightParam, double defArmorScoreParam){
        super(nameParam, weightParam);
        this.defArmorScore = defArmorScoreParam;
    }
}