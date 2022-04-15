package com.serhohuk.passbank

import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun isCyrilic(){
        val text = "привіт"
        val exp = Pattern.matches(".*\\p{InCyrillic}.*", text)
        assertEquals(exp,true)
    }
}