-- this is a rewrite of NameParser.java in Haskell
  import Data.Maybe
  import Data.List
  import Text.Printf
  parse :: String -> [String] -> [String]
  parse name builder = -- name context is consumed as it is moved into builder. ("first last",[]) -> ("last",["first"]) -> ("",["first","last"])
    let
      enumerate :: [a] -> [(a,Int)]
      enumerate x = x `zip` [0..]
      next :: Maybe Int
      -- next = ' ' `elemIndex` (i `drop` name)
      next = elemIndex ' ' name -- gets next occurance of ' ' 
    in
      case next of
        Just nx -> let
              -- nx = nx
              substring = [x | (x,n) <- enumerate name,n<=nx]
            in
              parse (drop (nx+1) name) (builder ++ [substring])
        Nothing -> let
            substring = [x | (x,n) <- enumerate name]
          in
            builder ++ [substring]

  parseName :: String -> [String]
  parseName name = parse name []
  charCount :: [String] -> Int
  charCount listS = sum [length s | s <- listS ]
  main :: IO()
  main =
    let
      withMiddle =  "David Daniel Debunki"
      withoutMiddle = "Emmanuel dubois"
      process :: String -> String
      process name = let
        parsed = parseName name in
           if length parsed == 2 then
              printf  "first: %s\nlast: %s\ntotal chars: %d\n" (head parsed) (parsed !! 1) (charCount parsed)
            else
               printf "first: %s\nmiddle: %s\nlast: %s\ntotal chars: %d\n" (head parsed) (parsed !! 1) (parsed !! 2) (charCount parsed)

    in do
      putStrLn (process withMiddle)
      putStrLn (process withoutMiddle)
