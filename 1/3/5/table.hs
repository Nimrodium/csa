-- generate a truth table of all variations of x y
import Text.Printf
format :: (Int, Int, Bool) -> String
format (x, y, z) = printf "%d \t|%d \t|%s" x y (show z)
f x y = (x <= 10) || (y < 5)
table :: [(Int,Int,Bool)]
table =
    let
        inputs :: [(Int,Int)]
        inputs = [(x,y) | x <- [5..15], y <- [3..7]]
    in map mapper inputs
        where
            mapper (x,y) = (x, y, f x y)
formatTable :: [(Int,Int,Bool)] -> String
formatTable table = "X\t| Y\t|f!(x > 10) ||  (y< 5)\n" ++ unlines (map format table)
main :: IO()
main = do
    putStrLn (formatTable table)
