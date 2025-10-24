class ParsedName(first:String,middle:Option[String],last:String):
    override def toString(): String = {
        this.middle match {
            case Some(middle) => {
                s"first: ${this.first}\nmiddle: ${middle}\nlast: ${this.last}"
            }
            case None => s"first: ${this.first}\nlast: ${this.last}"
        }
    }
    def totalChars: Int = {
        val middle = this.middle match {case Some(m) => m; case None => ""}
        List(this.first,middle,this.last).map(_.length).sum
    }
def parse(name: String): ParsedName =
    def recurse(name: String, split: List[String]): ParsedName =
        val nextSpace = name.indexOf(' ')
        nextSpace match {
            case -1 => {
                split.length match {
                    case 1 => {
                        ParsedName(split.head,None,name)
                    }
                    case _ => ParsedName(split.head,Some(split(1)),name)
                }
            }
            case _ => {
                recurse(name.drop(nextSpace+1),split:+name.take(nextSpace))
                }
            }
    recurse(name,List())

@main def main =
    List(
        parse("David Daniel Debunki"),
        parse("Emmanuel dubois")).
            foreach(
            x => println(s"${x},\ntotal chars: ${x.totalChars}\n")
        )
