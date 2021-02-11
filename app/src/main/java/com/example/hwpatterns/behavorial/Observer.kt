package com.example.hwpatterns.behavorial

interface DishObserver {
    fun update(dish: Dish)
}

enum class FoodState {
    READY,
    COOKING
}

class Dish(val name: String) {
    private var state = FoodState.COOKING
    private val observers = arrayListOf<DishObserver>()

    fun subscribe(observer: DishObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: DishObserver) {
        observers.remove(observer)
    }

    private fun notifySubscribers() {
        observers.forEach {
            it.update(this)
        }
    }

    fun cook() {
        if (state == FoodState.COOKING) {
            state = FoodState.READY
            notifySubscribers()
        }
    }

    fun printState() {
        println(state)
    }
}

class DishChangeStateObserver(var block: (Dish) -> Unit) : DishObserver {

    override fun update(dish: Dish) {
        block.invoke(dish)
    }
}

fun main() {
    val soup = Dish("Soup")

    soup.subscribe(DishChangeStateObserver { dish ->
        println("Dish: ${dish.name}, is now ready")
    })

    soup.cook()
}
