-- until : generate function to apply f[unction] until p[redicate] is true.
-- p : checks for if r[emainder] is greater than or equal to d[enominator]
-- f : subtracts the d[enominator] from the r[emainder] and adds one to the q[otient]
-- div' : returns (q[uotient],r[emainder])
div_unsigned' :: (Show a, Integral a )=> a -> a -> (a,a)
div_unsigned' _ 0 = error "cannot divide by zero"
div_unsigned' n d = until (p) (f) (0,n)
    where
        p (_,r) = r < d
        f (q,r) = (q+1,r-d) 
div' :: (Show a, Integral a )=> a -> a -> (a,a)
div' _ 0 = error "cannot divide by zero"
-- div' n d = if d < 0 then div' n (-d)
div' n d
    | d < 0 = div' n (-d)
    | n < 0 = let (q,r) = div' (-n) d in if r == 0 then (-q,r) else ((-q) -1,d-r)
    | otherwise = div_unsigned' n d