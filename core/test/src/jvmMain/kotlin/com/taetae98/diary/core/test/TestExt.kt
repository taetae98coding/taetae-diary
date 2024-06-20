package com.taetae98.diary.core.test

import java.io.BufferedReader

public fun Any.fileAsText(fileName: String): String {
    return javaClass.classLoader.getResourceAsStream(fileName)!!
        .bufferedReader()
        .use(BufferedReader::readText)
}
