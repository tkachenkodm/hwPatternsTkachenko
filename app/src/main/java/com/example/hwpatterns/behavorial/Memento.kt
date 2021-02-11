package com.example.hwpatterns.behavorial


interface SnapShot

class Database() {
    private var version: Int = 0

    fun touch() {
        version++
    }

    fun save(): SnapShot {
        return DatabaseSnapShot()
    }

    fun restore(snapShot: SnapShot) {
        (snapShot as DatabaseSnapShot).restoreState()
    }

    fun printVersion() {
        println("Current version: $version")
    }

    inner class DatabaseSnapShot : SnapShot {
        private val state = this@Database.version
        fun restoreState() {
            this@Database.version = state
        }

    }
}

class VersionControl(private var database: Database) {
    private var lastVersion: SnapShot? = null

    fun commit() {
        lastVersion = database.save()
    }

    fun revert() {
        lastVersion?.let {
            database.restore(it)
        } ?: kotlin.run {
            println("No saved version")
        }
    }

}

fun main() {
    val database = Database()
    database.printVersion()

    val versionControl = VersionControl(database)
    versionControl.commit()

    database.touch()
    database.printVersion()

    versionControl.revert()
    database.printVersion()
}