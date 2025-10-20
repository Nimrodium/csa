case class Person(name: String,years: Double) // likely better more idomatic way to do this, tuple? but this is a java translation so idrc.
@main def main = 
    val gradYear = 13;
    val people = List(Person("kyle",12.5),Person("Eric",11.5),Person("Clara",8.5))
    val numPeople = people.length;
    val totalYears = people.map(_.years).sum;
    val avgYears = totalYears / numPeople;
    val totalDays: Int = (totalYears*180).toInt
    people.foreach(x => 
        println(s"${x.name} has ${x.years} of school.\nthey have ${gradYear-x.years} years left of school")
        )
    println(s"number of people: $numPeople")
    println(s"total days in school: $totalDays")
    println(s"The average years of school is $avgYears")