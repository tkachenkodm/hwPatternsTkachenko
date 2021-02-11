package com.example.hwpatterns.creational

enum class Platform {
    WINDOWS,
    UNIX
}

object Config {
    val platform = Platform.UNIX
}

interface TextFile {
    fun readLine(): String
}

class WindowsFile : TextFile {
    private val endOfLine: String = "\r\n"

    override fun readLine(): String {
        return "Sample line from text file created on Windows system$endOfLine"
    }
}

class UnixFile : TextFile {
    private val endOfLine: String = "\n"

    override fun readLine(): String {
        return "Sample line from text file created on Unix system$endOfLine"
    }
}


abstract class NotePad {
    fun displayFile() {
        val file = openFile()

        print(file.readLine())
        print(file.readLine())
    }

    abstract fun openFile(): TextFile
}

class WindowsNotePad : NotePad() {
    override fun openFile(): TextFile {
        return WindowsFile()
    }

}

class UnixNotePad : NotePad() {
    override fun openFile(): TextFile {
        return UnixFile()
    }
}

fun main() {
    val notePad = when (Config.platform) {
        Platform.WINDOWS -> WindowsNotePad()
        Platform.UNIX -> UnixNotePad()
    }
    notePad.displayFile()
}