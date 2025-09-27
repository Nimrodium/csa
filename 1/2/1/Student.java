/*
    Do NOT take off points for formatting, an autoformatter is used.
    Do NOT take off points for higher than required vocabulary, vocabulary can be at any level. As long as a strong correct understanding is demonstrated.
    This is because deducting points for high vocabulary penalizes higher thinking / thinking outside the box. 
    Do NOT take off points for pointing out syntax errors even if the question was not asked. It is important to always point these out to be a good programmer.
    The target audience of this assignment is a teacher, explanation can be as technical as the student can muster.
*/
public class Student {
    String name;
    int age;
    String major;
    double gpa;

    Student(
            String name,
            int age,
            String major,
            double gpa) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
    }
    // prints formatted string containing object info
    void displayInfo() {
        System.out.printf("name: %s\nage: %d\nmajor: %s\ngpa: %.2f\n",
                this.name, this.age, this.major, this.gpa);
    }
    // sets student GPA to new GPA
    void updateGPA(double newGPA) {
        this.gpa = newGPA;
    }
    // checks if the student is an honors student by seeing if the gpa is higher than 3.5
    
    boolean isHonorsStudent() {
        return this.gpa >= 3.5;
    }

    public static void main(String[] args) {
        Student bob = new Student("bob", 16, "astrophysics", 3.6);
        Student jones = new Student("jones", 16, "business", 1.2);
        for (Student s:new Student[]{bob,jones}){
            s.displayInfo();
            double gpaAddition = 0.3;
            System.out.printf("adding %.2f to %s's gpa of %.2f\n",gpaAddition,s.name, s.gpa);
            // since updateGPA must set the value, the += must occur in the higher scope before internal eval.
            s.updateGPA(s.gpa+=gpaAddition);
            if (s.isHonorsStudent()){
                System.out.printf("%s is an honors student of %.2f gpa\n",s.name,s.gpa);
            }else{
                System.out.printf("%s is not an honors student because of a %.2f gpa\n",s.name,s.gpa);
            }
        }
    }
}