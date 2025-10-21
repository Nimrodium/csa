class ParsedName(first:String,middle:Option[String],last:String);


def parse(name: String): ParsedName =
    val recurse = (name: String, split: List[String]) => 
        val nextSpace = name.find(c => c==' ')-1 // maybe -1 ? 
        // val taken = name.take(nextSpace)
        // val movedName = name.drop(nextSpace)
        recurse(name.take(nextSpace),name.drop(nextSpace))
    recurse("",List())

@main def main = 
    ()    