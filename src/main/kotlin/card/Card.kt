package card

import jewel.JewelType

interface Card {

    val name: String
    val attack: Int
    val defense: Int
    val description: String?
    val price: Map<JewelType, Int>

    fun preview(): String {
        return "\nCard: $name\n" +
                "Description ${if (!description.isNullOrBlank()) description else '-'}\n" +
                "Attack Points: $attack\n" +
                "Defense Points: $defense\n" +
                "Defense Points: $defense\n" +
                "Price:" +
                price.entries.joinToString { priceMap ->
                    "\n - ${priceMap.key.friendlyName} Jewel - ${priceMap.value} qty"
                }
    }

    fun validateIfUserCanBuy(hand: Map<JewelType, Int>, firstCard: Boolean): Boolean {
        if (price.isEmpty()) return true
        if (price.isNotEmpty()) {
            price.entries.forEach { priceMap ->
                hand[priceMap.key]?.let { jewelQty ->
                    if (firstCard) {
                        return if (jewelQty < priceMap.value) return false else true
                    } else {
                        val newValue = priceMap.value.div(2)
                        return if (jewelQty <= newValue) return true else false
                    }
                } ?: return false
            }
        }
        return true
    }

    fun retrievePriceForSecondCard(): Map<JewelType, Int> {
        val newPriceMap = mutableMapOf<JewelType, Int>()

        this.price.entries.forEach {
            newPriceMap.putIfAbsent(it.key, it.value.div(2))
        }
        return newPriceMap
    }

    fun equals(card: Card): Boolean {
        return card.name == this.name && card.price.entries == this.price.entries
    }

}