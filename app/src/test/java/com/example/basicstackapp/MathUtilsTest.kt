package com.example.basicstackapp

import com.example.basicstackapp.common.MathUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class MathUtilsTest {

    @Test
    fun testAdd() {
        assertEquals(2, MathUtils.add(1, 1))
        assertEquals(0, MathUtils.add(-1, 1))
        assertEquals(-2, MathUtils.add(-1, -1))
    }

    @Test
    fun testSubtract() {
        assertEquals(0, MathUtils.subtract(1, 1))
        assertEquals(-2, MathUtils.subtract(-1, 1))
        assertEquals(0, MathUtils.subtract(-1, -1))
    }

    @Test
    fun testMultiply() {
        assertEquals(1, MathUtils.multiply(1, 1))
        assertEquals(-1, MathUtils.multiply(-1, 1))
        assertEquals(1, MathUtils.multiply(-1, -1))
    }

    @Test
    fun testDivide() {
        assertEquals(1.0, MathUtils.divide(1, 1), 0.0001)
        assertEquals(-1.0, MathUtils.divide(-1, 1), 0.0001)
        assertEquals(1.0, MathUtils.divide(-1, -1), 0.0001)
        assertEquals(Double.POSITIVE_INFINITY, MathUtils.divide(1, 0), 0.0001)
        assertEquals(Double.NEGATIVE_INFINITY, MathUtils.divide(-1, 0), 0.0001)
        assertEquals(Double.NaN, MathUtils.divide(0, 0), 0.0001)
    }

    @Test
    fun testIsPrime() {
        assertEquals(false, MathUtils.isPrime(1))
        assertEquals(true, MathUtils.isPrime(2))
        assertEquals(true, MathUtils.isPrime(3))
        assertEquals(false, MathUtils.isPrime(4))
        assertEquals(true, MathUtils.isPrime(5))
        assertEquals(false, MathUtils.isPrime(6))
        assertEquals(true, MathUtils.isPrime(7))
        assertEquals(false, MathUtils.isPrime(8))
        assertEquals(false, MathUtils.isPrime(9))
        assertEquals(false, MathUtils.isPrime(10))
        assertEquals(true, MathUtils.isPrime(11))
        assertEquals(false, MathUtils.isPrime(12))
        assertEquals(true, MathUtils.isPrime(13))
        assertEquals(false, MathUtils.isPrime(14))
        assertEquals(false, MathUtils.isPrime(15))
        assertEquals(false, MathUtils.isPrime(16))
        assertEquals(true, MathUtils.isPrime(17))
        assertEquals(false, MathUtils.isPrime(18))
        assertEquals(true, MathUtils.isPrime(19))
        assertEquals(false, MathUtils.isPrime(20))
    }

    @Test
    fun testToBinary() {
        assertEquals("0", MathUtils.toBinary(0))
        assertEquals("10", MathUtils.toBinary(2))
        assertEquals("101", MathUtils.toBinary(5))
        assertEquals("1010", MathUtils.toBinary(10))
        assertEquals("11111111", MathUtils.toBinary(255))
    }

    @Test
    fun testToOctal() {
        assertEquals("0", MathUtils.toOctal(0))
        assertEquals("2", MathUtils.toOctal(2))
        assertEquals("5", MathUtils.toOctal(5))
        assertEquals("12", MathUtils.toOctal(10))
        assertEquals("377", MathUtils.toOctal(255))
    }

    @Test
    fun testToHex() {
        assertEquals("0", MathUtils.toHex(0))
        assertEquals("2", MathUtils.toHex(2))
        assertEquals("5", MathUtils.toHex(5))
        assertEquals("a", MathUtils.toHex(10))
        assertEquals("ff", MathUtils.toHex(255))
    }

    @Test
    fun testFromBinary() {
        assertEquals(0, MathUtils.fromBinary("0"))
        assertEquals(2, MathUtils.fromBinary("10"))
        assertEquals(5, MathUtils.fromBinary("101"))
        assertEquals(10, MathUtils.fromBinary("1010"))
        assertEquals(255, MathUtils.fromBinary("11111111"))
        try {
            MathUtils.fromBinary("1234")
            assert(false)
        } catch (e: IllegalArgumentException) {
            // Expected exception
        }
    }

    @Test
    fun testFromOctal() {
        assertEquals(0, MathUtils.fromOctal("0"))
        assertEquals(2, MathUtils.fromOctal("2"))
        assertEquals(5, MathUtils.fromOctal("5"))
        assertEquals(10, MathUtils.fromOctal("12"))
        assertEquals(255, MathUtils.fromOctal("377"))
        try {
            MathUtils.fromOctal("888")
            assert(false)
        } catch (e: IllegalArgumentException) {
            // Expected exception
        }
    }

    @Test
    fun testFromHex() {
        assertEquals(0, MathUtils.fromHex("0"))
        assertEquals(2, MathUtils.fromHex("2"))
        assertEquals(5, MathUtils.fromHex("5"))
        assertEquals(10, MathUtils.fromHex("a"))
        assertEquals(255, MathUtils.fromHex("ff"))
        try {
            MathUtils.fromHex("gg")
            assert(false)
        } catch (e: IllegalArgumentException) {
            // Expected exception
        }
    }

    @Test
    fun testRoots() {
        assertEquals(Pair(-1.0, -1.0), MathUtils.roots(1.0, 2.0, 1.0))
        assertEquals(Pair(0.0, -1.0), MathUtils.roots(1.0, 1.0, 0.0))
        assertEquals(Pair(Double.NaN, Double.NaN), MathUtils.roots(0.0, 0.0, 1.0))
    }
}
