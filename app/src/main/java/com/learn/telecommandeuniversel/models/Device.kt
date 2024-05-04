package com.learn.telecommandeuniversel.models

data class Remote(
    val id: Int,
    val marque: String,
    val type: String,
    val models: List<Model>
)

data class Model(
    val name: String,
    val functions: List<Function>
)

data class Function(
    val name: String,
    val pattern: IntArray,
    val frequency: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Function

        if (name != other.name) return false
        if (!pattern.contentEquals(other.pattern)) return false
        if (frequency != other.frequency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + pattern.contentHashCode()
        result = 31 * result + frequency
        return result
    }
}
