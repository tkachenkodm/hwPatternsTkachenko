package com.example.hwpatterns.structural

interface LightSource {
    fun shineLight(): String
}

class MonochromeLightSource(private val baseColor: String): LightSource {
    override fun shineLight(): String {
        return baseColor
    }
}

//Base Decorator
abstract class LightFilter(private val baseLightSource: LightSource): LightSource {
    override fun shineLight(): String {
        return baseLightSource.shineLight()
    }

    abstract fun applyFilter(color: String): String
}

//Concrete Decorators
class GreenFilter(lightSource: LightSource) : LightFilter(lightSource) {
    override fun shineLight(): String {
        return applyFilter(super.shineLight())
    }

    override fun applyFilter(color: String): String {
        return "$color + Green Filter"
    }
}

class RedFilter(lightSource: LightSource) : LightFilter(lightSource) {
    override fun shineLight(): String {
        return applyFilter(super.shineLight())
    }

    override fun applyFilter(color: String): String {
        return "$color + Red Filter"
    }
}

fun main() {
    val filteredLightSource = RedFilter(GreenFilter(MonochromeLightSource("Blue Color")))

    println(filteredLightSource.shineLight())
}