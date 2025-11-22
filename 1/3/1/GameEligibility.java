import java.util.ArrayList;
import java.util.Arrays;
class GameElibility {
  // using age and score instead of playerAge
  // as the scope is declared in syntax as the class
  enum Level {
    JUNIOR,
    SENIOR,
    ADVANCED,
    PREMIUM,
    NEEDS_IMPROVEMENT,
  }
  static class Player {
    int age;
    int score;
    boolean isPremiumMember;
    ArrayList<Level> levels = new ArrayList<>();
    Player(int age,int score,boolean isPremiumMember){
      this.age = age;
      this.score = score;
      this.isPremiumMember = isPremiumMember;
      // there are overlapping conditions here i believe. not my problem though,
      // thats what the assignment wants
      // i tried to write this using a switch statement but unfortunately
      // java's matching syntax is too weak to do anything cool
      // i wrote that before i realized its asking for every level it qualifies for,
      // so now im just gonna push to a vec
      
      if (this.score >= 500 && this.age >= 16){
        this.levels.add(Level.ADVANCED);
      } if (this.isPremiumMember && this.score >= 300){
        this.levels.add(Level.PREMIUM);
      }
      if (this.age < 13){
        this.levels.add(Level.JUNIOR);
      }
      if (this.age >= 13) {
        this.levels.add(Level.SENIOR);
      }
      if (this.score < 100){
        this.levels.add(Level.NEEDS_IMPROVEMENT);  
      }
    }
    @Override
    public String toString(){
      return String.format("player age: %d\nplayer score: %d\nis a premium member: %b\neligable tournaments: %s\n",
      this.age,this.score,this.isPremiumMember,this.levels);
    }
  }
    public static void main(String[] args){
       new ArrayList<>(Arrays.asList(
        new Player(8,253,true),
        new Player(16,633,true),
        new Player(13,355,false),
        new Player(19,800,false),
        new Player(10,55,true),
        new Player(15,700,false)
        )
      ).
        forEach(System.out::println);
    }
}
