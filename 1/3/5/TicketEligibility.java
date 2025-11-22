import java.util.Scanner;

public class TicketEligibility {

    static enum Rating {
        G,
        PG,
        PG13,
        R;

        static Result<Rating, String> fromString(String s) {
            return switch (s.toUpperCase()) {
                case "G" -> Result.ok(Rating.G);
                case "PG" -> Result.ok(Rating.PG);
                case "PG13" -> Result.ok(Rating.PG13);
                case "R" -> Result.ok(Rating.R);
                default -> Result.err(
                    String.format("%s is not a valid Rating\n", s)
                );
            };
        }
    }

    record Result<T, E>(T ok, E err) {
        T unwrap() {
            if (this.ok != null) {
                return this.ok;
            } else {
                throw new RuntimeException(
                    String.format("unwrap of Result::Err : %s\n", this.err)
                );
            }
        }

        static <T, E> Result<T, E> ok(T v) {
            return new Result<T, E>(v, null);
        }

        static <T, E> Result<T, E> err(E e) {
            return new Result<T, E>(null, e);
        }

        boolean isOk() {
            return this.ok != null;
        }

        boolean isErr() {
            return this.err != null;
        }

        @Override
        public String toString() {
            if (this.isOk()) {
                return this.ok().toString();
            } else {
                return String.format("Err: %s", this.err());
            }
        }
    }

    static Result<Integer, String> price(int age, Rating r) {
        // i use || here to demonstrate that i can use compound operators, but in this example, its actually better to nest conditionals,
        //  as if (A && B) else if (A && C) is actually worse than if (A) {if (B) else if (C){...}} as A should only be tested once for efficiency, and creating a scope where A is true helps readablity.
        // i opted for nesting the rating conditional but keeping a flat if (r == Rating.G && age < 13) {return Result.ok(8)}... is perfectly valid, but overly verbose, so i opted for the better implementation,
        // and i do not want to rewrite it, i demonstrate compound operators in RangeValidator (and table.hs) more.
        if (r == Rating.G || r == Rating.PG) {
            if (age < 13) {
                return Result.ok(8);
            } else if (age >= 65) {
                return Result.ok(10);
            } else {
                return Result.ok(12);
            }
        } else if (r == Rating.PG13) {
            if (age >= 13) {
                if (age < 17) {
                    return Result.ok(10);
                } else if (age >= 65) {
                    return Result.ok(10);
                } else {
                    return Result.ok(12);
                }
            } else {
                return Result.err(
                    "this age is not allowed in PG-13 rated showings"
                );
            }
        } else {
            if (age >= 17) {
                if (age >= 65) {
                    return Result.ok(10);
                } else {
                    return Result.ok(12);
                }
            } else {
                return Result.err(
                    "this age is not allowed in R rated showings"
                );
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Your code here
        System.out.println("enter age:");
        int age = sc.nextInt();
        Rating rating;
        while (true) {
            System.out.println("enter rating [G,PG,PG13,R]:");
            var result = Rating.fromString(sc.next());
            if (result.isOk()) {
                rating = result.unwrap();
                break;
            } else {
                System.out.println((result.err()));
            }
        }
        var result = price(age, rating);
        // System.out.println(result);
        if (result.isOk()) {
            System.out.printf("ticket price $%d\n", result.unwrap());
        } else {
            System.out.println(result);
        }
        sc.close();
    }
}
