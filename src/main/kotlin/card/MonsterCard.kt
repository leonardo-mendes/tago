package card

import jewel.JewelType

data class MonsterCard(
    override val name: String,
    override val attack: Int,
    override val defense: Int,
    override val description: String? = null,
    override val price: Map<JewelType, Int>
): Card
