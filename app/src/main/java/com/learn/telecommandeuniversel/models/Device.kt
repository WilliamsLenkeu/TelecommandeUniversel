package com.learn.telecommandeuniversel.models

data class Device(
    val id: Int,
    val type: String,
    val brand: String,
    val function: String,
    val pattern: IntArray,
    val frequency: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Device

        if (id != other.id) return false
        if (type != other.type) return false
        if (brand != other.brand) return false
        if (function != other.function) return false
        if (!pattern.contentEquals(other.pattern)) return false
        if (frequency != other.frequency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + type.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + function.hashCode()
        result = 31 * result + pattern.contentHashCode()
        result = 31 * result + frequency
        return result
    }
}
