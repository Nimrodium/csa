import scala.util.Random


@main def main = {

    val generateRandomList = (s:Int,e:Int,r:Random,length:Int) => 
        def sampleRandom(s:Int, e:Int): Int = 
            val sample = (r.nextDouble*e).toInt
            if s <= sample && sample < e then
                sample
            else
                println(s"sample = $sample")
                sampleRandom(s,e)

        Seq.tabulate(length)(_ => sampleRandom(s,e))
    val r  = Random()
    val range = 30
    val master = generateRandomList(0,range,r,range)
    val weight = generateRandomList(0,40,r,range)
    println(s"master = $master")
    println(s"weight = $weight")
    val weighted = master.zipWithIndex.filter((x,i) => x < weight(i)).map((x,_) => x)
    println(s"weighted = $weighted")
    println("---")
    
    val toStr = (v:String,a:Seq[Any]) => 
        val toList = (a:Seq[Any]) => a.map(x => s"$x, ").foldLeft("")((x,y) => x + y)
        s"$v = [${toList(a)}]"
    
    println(toStr("a",master))
    println(toStr("b",weight))
    println(toStr("c",weighted))
}