package com.example.basicstackapp.common


object Keys  {

    init {
        System.loadLibrary("native-lib")
    }

    external  fun cipherKey(): String

    external fun vectorSecretKey(): String
}
