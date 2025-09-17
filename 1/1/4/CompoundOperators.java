/*
 * Activity 1.1.4
 */
// CODE TO ADD
/*
* TODO 1) Add at least three other people to your program,
* such as siblings, friends, or neighbors.
*
* TODO 2) Use people that are in different grades to validate
* your program can work for different school grades.
*
* TODO 3) For their years in school, use values that indicate
* they will soon be halfway through their current
* school year, as shown in the provided code.
*/
/*
* TODO 4)The average years you and your friends, siblings,
* and neighbors have been in school.
*
* TODO 5) The total days you have all been in school,
* assuming you spend 180 days per year in school.
*
* TODO 6) The average days you have all spent in school.
 */
public class CompoundOperators
{
  
  static class Person{
    String name;
    double years;

    Person(String name,double years){
        this.name = name;
        this.years = years;
    }
  }
  
  public static void main(String[] args)
  {
    int numPeople = 0;  
    double totalYears = 0;
    double avgYears = 0;
    int gradYear = 13;
    for (Person p: new Person[]{
        new Person("Kyle",12.5),
        new Person("Eric",11.5),
        new Person("Clara",8.5),
    }){

        numPeople++;
        totalYears +=p.years;
        avgYears = totalYears/numPeople;
        System.out.println(p.name + " has " + p.years + " years of school.\nthey have " +(gradYear-p.years)+ " years left of school");
    }
    System.out.println("number of people: " + numPeople);
    int totalDays = (int)(totalYears * 180);
    System.out.println("total days in school: " + totalDays);
    System.out.println("The average years of school is " + avgYears);
  }
}