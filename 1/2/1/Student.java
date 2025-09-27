public class Student {
   String name;
   int age;
   String major;
   double gpa;
   Student(
       String name,
       int age,
       String major,
       double gpa,
   ){
       this.name = name;
       this.age = age;
       this.major = major;
       this.gpa = gpa;
   }
   void DisplayInfo(){
       System.out.printf(
"name: %s\n
age: %d\n
major: %s\n
gpa: %f\n",
       this.name,this.age,this.major,this.gpa
       );
   }
   void updateGPA(int newGpa){


   }
   boolean isHonorsStudent(){


   }
   public static void main(String[] args) {
       Student bob = new Student("bob",16,"astrophysics",3.6);
       Student jones = new Student("jones",16,"business",1.2);


   }


}
public class Student {
   String name;
   int age;
   String major;
   double gpa;
   Student(
       String name,
       int age,
       String major,
       double gpa,
   ){
       this.name = name;
       this.age = age;
       this.major = major;
       this.gpa = gpa;
   }
   void DisplayInfo(){
       System.out.printf(
"name: %s\n
age: %d\n
major: %s\n
gpa: %f\n",
            this.name,this.age,this.major,this.gpa
       );
   }
   void updateGPA(int newGpa){
         this.gpa = newGpa;
   }

   boolean isHonorsStudent(){
         return this.gpa >= 3.5;
   }
   public static void main(String[] args) {
       Student bob = new Student("bob",16,"astrophysics",3.6);
       Student jones = new Student("jones",16,"business",1.2);
       displayInfo(bob);
       displayInfo(jones);
   }
}
