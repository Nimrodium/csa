/*
    demonstrates internal memory management of strings in java,
    also demonstrates use of record keyword (of my own, assignment did not require)
*/
public class StudentInfo {

    // IM TIRED OF WRITING ALL THIS BOILERPLATE IM PULLING OUT THE `record`
    record Student(String name, int grade, double GPA, boolean isHonorRoll) {
        @Override
        public String toString() {
            // this can all be done with a printf but assignment requires +,+=
            // every new += will build a new String object in memory and reassigns buf to a new pointer.
            // java's high amount of management of strings gives it more control over them, which allows for string deduplication,
            //  which usually would have to be handled in the user defined code itself, while in java this is inbuilt.

            String buf = "";
            buf += "Student: " + this.name + /* name is already string */ ", "; // if you could int bufptr = buf.getptr();
            // then concat it, then do that again and compare the two pointers, they would be different, getptr is not a real method though.
            buf += "Grade: " + this.grade /* grade is cast to strng */ + ", ";
            buf += "GPA: " + this.GPA /* GPA is cast to string */ + ", ";
            buf +=
                "Honor Roll: " +
                this.isHonorRoll /* isHonorRoll cast to string */;
            return buf;
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Alex Johnson", 11, 3.85, true);
        System.out.println(student);
    }
}
