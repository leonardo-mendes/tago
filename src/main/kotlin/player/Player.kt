package player

import card.Card
import jewel.JewelType

data class Player(
    val name: String,
    val jewels: MutableMap<JewelType, Int> = mutableMapOf(),
    val cards: MutableList<Card> = mutableListOf()
) {

    fun buyCard(card: Card) {
        val firstCard = cards.none { it.equals(card) }
        if (card.validateIfUserCanBuy(jewels, firstCard)) {
            if (card.price.isNotEmpty() && firstCard) {
                card.price.entries.forEach { priceMap ->
                    this.handleJewel(priceMap.key, (priceMap.value * -1))
                }
            } else if (card.price.isNotEmpty() && !firstCard) {
                card.retrievePriceForSecondCard().entries.forEach { priceMap ->
                    this.handleJewel(priceMap.key, (priceMap.value * -1))
                }
            }
            cards.add(card)
            println("Added ${card.name} card to your hand.")
        } else {
            println("You dont have jewels enough to buy ${card.name} card.")
        }
    }

    fun handleJewel(jewel: JewelType, qty: Int) {
        jewels[jewel]?.let {
            val newQty = it + qty
            jewels[jewel] = if (newQty < 0) 0 else newQty
        } ?: jewels.put(jewel, if (qty < 0) 0 else qty)
    }

    override fun toString(): String {
        return "\nPlayer Name: $name\n" +
                "Player Jewels:" +
                jewels.entries.joinToString { jewelMap ->
                    "\n - ${jewelMap.key.friendlyName} Jewel - ${jewelMap.value} qty"
                } +
                "\nPlayer Cards:" +
                cards.joinToString { it.preview() }
    }


}
