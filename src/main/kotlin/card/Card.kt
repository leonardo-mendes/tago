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

    fun validateIfUserCanBuy(hand: Map<JewelType, Int>): Boolean {
        if (price.isEmpty()) return true
        if (price.isNotEmpty()) {
            price.entries.forEach { priceMap ->
                hand[priceMap.key]?.let { jewelQty ->
                    if (jewelQty < priceMap.value) return false
                } ?: return false
            }
        }
        return true
    }

}