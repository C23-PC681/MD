package com.example.myapplication.utils

object BMICalculator {
    fun calc(height: Int, weight: Int): String {
        val heightDouble = height.toDouble() / 100
        val weightDouble = weight.toDouble()
        return (weightDouble / (heightDouble * heightDouble)).toString()
    }
}