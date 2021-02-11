package com.example.hwpatterns.creational

class Singleton private constructor(
    private var intProperty: Int,
    private val stringProperty: String
) {
    companion object {
        private var instance: Singleton? = null

        fun getInstance(): Singleton {
            if (instance == null) {
                instance = Singleton(12, "string")
            }
            return instance!!
        }
    }

    fun changeIntProperty() {
        intProperty++
    }

    fun printIntProperty(){
        println(intProperty)
    }
}

fun main() {
    val firstInstance = Singleton.getInstance()
    val secondInstance = Singleton.getInstance()

    firstInstance.changeIntProperty()
    println("Property value of two object should be the same:")
    firstInstance.printIntProperty()
    secondInstance.printIntProperty()
}