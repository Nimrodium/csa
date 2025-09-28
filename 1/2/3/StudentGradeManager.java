public class StudentGradeManager {
    static class Student{
        String name;
        int grade;
        String subject;
        Student(){
            // initializes object with default values
            this.name = "Unknown";
            this.grade = 0;
            this.subject = "None";
        }
        // sets values of object
        void setStudentInfo(){
            this.name = "Alex Johnson";
            this.grade = 85;
            this.subject = "Computer Science";
        }
        // increments object grade by 10
        void updateGrade(){
            this.grade+=10;
        }
        // prints student info
        void displayStudent(){
            System.out.printf("name:%s\ngrade:%d\nsubject:%s\n\n",this.name,this.grade,this.subject);
        }
    }
    public static void main(String[] args) {
        // we will modify this one through its methods
        Student s1 = new Student();
        // this one will be unchanged
        Student s2 = new Student();
        // show both students
        s1.displayStudent();
        s2.displayStudent();
        // call s1's set student info method
        s1.setStudentInfo();
        // call s1's update grade method
        s1.updateGrade();
        // show both students again. now s1 is different.
        s1.displayStudent();
        s2.displayStudent(); // this demonstrates that objects are isolated
    }
}