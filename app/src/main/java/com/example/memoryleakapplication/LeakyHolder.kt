package com.example.memoryleakapplication

object LeakyHolder {
    var leakedObjects = mutableListOf<Any>()
}