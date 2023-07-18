import card.Card
import card.MonsterCard
import jewel.JewelType
import player.Player

fun main(args: Array<String>) {

    val chicagoSeagull: Card = MonsterCard(
        name = "Chicago Seagull",
        attack = 10,
        defense = 5,
        price = mapOf(
            JewelType.BLUE to 2,
            JewelType.YELLOW to 1,
        )
    )

    val chicagoSquirrel: Card = MonsterCard(
        name = "Chicago Squirrel",
        attack = 3,
        defense = 1,
        price = mapOf(
            JewelType.BLUE to 2,
            JewelType.YELLOW to 1,
        )
    )

    val leo = Player(
        name = "Leonardo",
        jewels = mutableMapOf(
            JewelType.BLUE to 1,
            JewelType.YELLOW to 1,
        )
    )

    leo.buyCard(chicagoSeagull)
    leo.handleJewel(JewelType.BLUE, 1)
    leo.handleJewel(JewelType.BLUE, 2)
    leo.handleJewel(JewelType.YELLOW, 2)
    leo.buyCard(chicagoSeagull)
    leo.buyCard(chicagoSquirrel)

    leo.handleJewel(JewelType.BLUE, 1)
    leo.buyCard(chicagoSeagull)

    println(leo.toString())
}