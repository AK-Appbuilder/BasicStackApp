package com.example.basicstackapp.common;

import kotlin.math.*

object MathUtils {

    fun add(a:Int, b:Int) =a +b

    fun subtract(a:Int, b:Int) =a -b

    fun multiply(a:Int, b:Int) =a *b

    fun divide(a:Int, b:Int):Double =a.toDouble()/b.toDouble()

    fun pow(base:Double, exponent:Double):Double =base.pow(exponent)

    fun sqrt(a:Double):Double = a*a

    fun cbrt(a:Double):Double =

        cbrt(a)

    fun round(a:Double):Long =

        round(a)

    fun ceil(a:Double):Double =

        ceil(a)

    fun floor(a:Double):Double =

        floor(a)

    fun abs(a:Int):Int =

        abs(a)

    fun max(a:Int, b:Int):Int =

        max(a, b)

    fun min(a:Int, b:Int):Int =

        min(a, b)

    fun signum(a:Int):Int =a.sign

    fun gcd(a:Int, b:Int):

            Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun lcm(a:Int, b:Int):Int =

        abs(a *b) /

                gcd(a, b)

    fun isPrime(a:Int):

            Boolean {
        if (a <= 1) return false
        if (a <= 3) return true
        if (a % 2 == 0 || a % 3 == 0) return false
        var i = 5
        while (i * i <= a) {
            if (a % i == 0 || a % (i + 2) == 0) return false
            i += 6
        }
        return true
    }

    fun factorial(a:Int):

            Long {
        var result:Long = 1
        for (i in 2..a) {
            result *= i.toLong()
        }
        return result
    }

    fun fibonacci(a:Int):

            Long {
        var a1:Long = 0
        var a2:Long = 1
        var i = 0
        while (i < a) {
            val temp = a1
            a1 = a2
            a2 += temp
            i++
        }
        return a1
    }

    fun isPalindrome(a:Int):Boolean =a.toString()==a.toString().

    reversed()

    fun toBinary(a:Int):String =Integer.toBinaryString(a)

    fun toOctal(a:Int):String =Integer.toOctalString(a)

    fun toHex(a:Int):String =Integer.toHexString(a)

    fun fromBinary(a:String):Int =Integer.parseInt(a,2)

    fun fromOctal(a:String):Int =Integer.parseInt(a,8)

    fun fromHex(a:String):Int =Integer.parseInt(a,16)

    fun roots(a:Double, b:Double, c:Double):Pair<Double, Double>

    {
        val disc = b * b - 4 * a * c
        val x1 = (-b + sqrt(disc)) / (2 * a)
        val x2 = (-b - sqrt(disc)) / (2 * a)
        return Pair(x1, x2)
    }


}
