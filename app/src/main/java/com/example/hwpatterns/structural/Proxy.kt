package com.example.hwpatterns.structural

interface FileStorage {
    fun getFileName(): String
}

class DirectFileStorageAccess() : FileStorage {
    override fun getFileName(): String {
        return "SampleFile.file"
    }
}

class CachedFileStorageAccess(private val storage: FileStorage) : FileStorage {
    private var cachedFileName: String? = null

    override fun getFileName(): String {
        cachedFileName?.let { fileName ->
            return "File name retrieved from cache: $fileName"
        } ?: kotlin.run {
            val fileName = storage.getFileName()
            cachedFileName = fileName
            return fileName
        }
    }
}

fun printFileName(storage: FileStorage) {
    println(storage.getFileName())
}

fun main() {
    val storageProxy = CachedFileStorageAccess(DirectFileStorageAccess())
    val storage = DirectFileStorageAccess()

    printFileName(storage)
    printFileName(storageProxy)
    printFileName(storageProxy)
}