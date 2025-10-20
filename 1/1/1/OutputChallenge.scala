@main def main = 
    var div = "================================"
    var strings = List(
        div,
        "\tWelcome To \" Scala Basics\"",
        "\tToday's Topic: Output Methods",
        div,
        "all lines use the same function because better",
        div
        )
    strings.foreach(println)
    