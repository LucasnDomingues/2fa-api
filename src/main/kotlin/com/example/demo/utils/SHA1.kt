package com.example.demo.utils

import java.math.BigInteger
import java.security.MessageDigest


class SHA1 {
    fun hash(value: String) : String {
        var sha1 = ""
        // With the java libraries
        try {
            val digest: MessageDigest = MessageDigest.getInstance("SHA-1")
            digest.reset()
            digest.update(value.toByteArray(charset("utf8")))
            sha1 = String.format("%040x", BigInteger(1, digest.digest()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sha1;
    }
}