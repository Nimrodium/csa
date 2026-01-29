package reviewer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import reviewer.SentimentMapper;
class ReviewRunner {
  static class Review{
    List<Double> inner;
    List<String> original;
    Double net;

    private double totalSentiment(){
      return this.net;
    }
    private int adjectiveCount(){
      return (int) this.inner.stream().filter(d -> d != 0).count();
    }
    private Review(List<Double> inner, List<String> original){
      this.inner = inner;
      this.original = original;
      this.net = this.inner.stream().mapToDouble(Double::valueOf).sum();
    }
    static Review fromString(String s){
      if (s==null){
        return null;
      }
      var original = List.of(s.trim().split(" "));
      var inner = original.stream().map(SentimentMapper::sentimentVal).toList();
      return new Review(inner,original);
    }
    boolean isNegative(){
      return this.totalSentiment()<0;
    }
    boolean isPositive(){
      return 0<this.totalSentiment();
    }
    int starRating(){
      int maxStars = 5;
      var net = this.totalSentiment();
      var max = this.adjectiveCount();
      if (adjectiveCount()==0){
        return maxStars/2;
      }
      // Haskell Spec:
      /*
        -- shift (+max) -> normalize (/max*2) -> scale (*maxStars)
        starRating :: (Fractional a) => a -> a -> a -> a
        starRating maxStars max net = normalize*maxStars
            where shift = net+max
                  normalize = shift/(max*2)
      */
      var shift = net+max;
      var normalize = shift/(max*2);
      return (int) (normalize*maxStars);
    }
    private String substitute(Predicate<Double> predicate,Supplier<String> substitutes){
      // Haskell Spec:
      // foldr (++) "" $ map (\x -> (snd x) ++ " ") $ map (\(v,str) if predicate v then (v,nextSub) else (v,str)) $ zip this.inner this.original
      var buf = new StringBuilder();
      for (int i=0;i<this.inner.size();i++){
        var str = this.original.get(i);
        var value = this.inner.get(i);
        if (predicate.test(value)){
          buf.append(substitutes.get());
        }else buf.append(str);
        buf.append(" ");
      }
      return buf.toString().trim();
    }

    String mkPositive(){
      return this.substitute(v -> v<0, () -> SentimentMapper.randomPositiveAdj());
    }
    String mkNegative(){
      return this.substitute(v -> 0<v, () -> SentimentMapper.randomNegativeAdj());
    }
    String fakeReview(){
      return this.mkPositive();
    }
  }
  static String stdInReview(){
    var sc = new Scanner(System.in);
    System.out.println("enter a review:");
    var input = sc.nextLine();
    sc.close();
    return input;
  }
  static String getReview(String[] args){
    if (args.length<1){
      return stdInReview();
    }else{
      var path = args[0];
      try{
        return Files.readString(Path.of(args[0]));
      } catch (IOException e){
        System.err.printf("could not read %s: `%s`",path,e);
        return stdInReview();
      }
    }
  }
  public static void main(String[] args) 
  {
    /* your code here, for example: */
    // - write a program which uses the functions of Review to determine if a review is negative or positive
    // - swap negative adjectives with positive adjectives
    // - convert the raw net sentiment into a 5 star rating.
    // plan:
    //  i am going to take a review string and break it up into words, map each word into its sentiment value 
    //  (0 for not in), then i can sum up that list into the net value of the review.
    
    //  for star rating, i need to determine what a 100% review would look like, 
    //  and then figure out how to contrast with the current review
    //  im thinking maybe that i can filter out 0's for only weighted values, a 100% positive review is all 1's,
    //  a 100% negative review is all -1's (-10). if say there are 10 adjectives in the 100% review, itll be 10.0. so, 
    // if all are 1.0, then thats max, anything less is a lower star. maybe, 10/5 for the star diff, 
    // if its 8 then its 4 star, 6 3 star, 4 2 star, 2 1 star, 0 is also 1 star, so actually i need 
    // to distribute that last 2, so maybe, (10/5)/5
    // so probably, shift all values by max so that they are all positive, then starDiff = (total*2)/maxStars, 
    // then if net <= starDiff then 1 else if net <= starDiff*2 then 2 ... 
    // but i want to do it without if else or match case, 
    // so i need to normalize [0..max] -> [0..1], then
    
    // my problem is essentially data-binning a single value, so the starDiff is actually binWidth = (max-min)/n
    // and bins are 0..binwidth, binwidth..binwidth*2,...; 
    // i need a transformation which will turn the value into its binN.
    // so far i have [-max..max] (+max) -> [0..max*2] (a?) -> [0..1] (b?) -> [0..maxStars]
    //   where the function a? normalizes the positive range, which seems like its needed
    // , and b? maps the normalized range to the star rating. the relationship of star rating with normalized is
    // 0->0,0.5->maxStars/2,1->maxStars; thus it will look like an inverse of the logic of the normalization function,
    // so now i must understand the normalization function, its relationship is 0->0,total/2->0.5,total->1;
    //  i decided to look up "how to normalize a set to [0..1]" and it produced `a min max x = (x - min*x)/(max*x - min*x)`
    //  i know how to map a range of [0..1] -> [0..max] actually, its very simple you just (*max), thus `b = (*maxStars)`
    //  so now i know the whole pipeline, 
        //  -- aka [-max..max] (+max) -> [0..max*2] (a) -> [0..1] (b) -> [0..maxStars]
    // Haskell Spec:
    //  map (b) $ map (a) $ map (+max) [-max..max]
    //    where
    //      a min max x = (x - min*x)/(max*x - min*x)     
    //      b = (*maxStars)
    // in this program it only has to implement a single indices computation.   

    // changes from original structure: renamed Review to SentimentMapper, and my class to Review, 
    // since previously Review just handled individual adjective sentiments, not the whole review string.
    // my Review actually handles the whole review string.
    var review = Review.fromString(getReview(args));
    System.out.printf("total sentiment: %.2f\n",review.totalSentiment());
    System.out.printf("review is: %s\n",review.isPositive() ? "positive" : "negative");
    System.out.printf("review rating: %d stars\n",review.starRating());
    // String swapped;
    if (review.isPositive()){
      // var swapped = review.mkNegative();
      System.out.printf("review made negative: %s\n",review.mkNegative());
    }else{
      System.out.printf("review made positive: %s\n",review.mkPositive());
    }
  }
}
