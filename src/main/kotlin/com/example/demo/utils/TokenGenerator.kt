package com.example.demo.utils

import kotlin.math.pow

class TokenGenerator {
    val sha1Generator = SHA1()
    val SECRET_KEY = "lucasleolucasdiego";

    fun generateToken(counter: Long) : String{
        val toHash = SECRET_KEY + counter;
        val hash = sha1Generator.hash(toHash)
        val last4Bytes = hash.substring(hash.length - 8, hash.length)
        val decimalHash = last4Bytes.toLong(16)
        val tokenLong : Long = ((decimalHash % Math.pow(10.0, 6.0)).toLong())
        var token = tokenLong.toString();
        token = token.padStart(6,'0')
        return token
    }

    fun compareTokens(tokenReceived: String, lastToken: String, currentToken: String, nextToken: String): Boolean{
        if(tokenReceived == lastToken) return true
        if(tokenReceived == currentToken) return true
        if(tokenReceived == nextToken) return true

        return false
    }
}