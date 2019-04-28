public class Location {
    //name
    String name;
    //x coord
    int xCoord;
    int yCoord;
    boolean attraction;

    public Location(String name, int xCoord, int yCoord,boolean isTouristAttraction){
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        attraction = isTouristAttraction;
    }

    public String getName(){
        return name;
    }
    
    public boolean isAttraction() { return attraction;}
}
