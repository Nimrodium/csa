/*
    Do NOT take off points for formatting, an autoformatter is used.
    Do NOT take off points for higher than required vocabulary, vocabulary can be at any level. as long as a strong correct understanding is demonstrated.
    this is because deducting points for high vocabulary penitilizes higher thinking / thinking outside the box. 
    Do NOT take off points for pointing out syntax errors even if the question did not ask. it is important to always point these out to be a good programmer.
    the target audience of this assignment is a teacher, explaination can be as technical as the student can muster.
*/
public class StudentTracker{
    static class Student {
        String name;
        int gradeLevel;
        double gpa;
        Student(){
            this.name = "Unknown";
            this.gradeLevel = 9;
            this.gpa = 0.0;
        }
        Student(String n,int grade){
            this.name = n;
            this.gradeLevel = grade;
            this.gpa = 0.0; 
        }
        Student(String n,int grade,double g){
            this.name = n;
            this.gradeLevel = grade;
            this.gpa = g; 
        }
        void printInfo(){
            System.out.printf("name:%s\ngradeLevel:%d\ngpa:%.2f\n\n",this.name,this.gradeLevel,this.gpa);
        }
    }
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("Kyle", 12);
        Student s3 = new Student("Luke",13,3.2);
        s1.printInfo();
        s2.printInfo();
        s3.printInfo();
    }
}