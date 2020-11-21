package com.example.demo.token

import com.example.demo.utils.TokenGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController {
    val tokenGenerator = TokenGenerator()

    @PostMapping("/token")
    fun validateToken(@RequestBody tokenToValidate: Token2FA): ResponseEntity<Token2FA>{
        val currentTime = System.currentTimeMillis() / 30000

        val lastToken = tokenGenerator.generateToken(currentTime - 1)
        val currentToken = tokenGenerator.generateToken(currentTime)
        val nextToken = tokenGenerator.generateToken(currentTime + 1)

        val isTokenValid = tokenGenerator.compareTokens(tokenToValidate.token,lastToken,currentToken, nextToken)
        println(isTokenValid)
        return if(isTokenValid){
            ResponseEntity.status(HttpStatus.ACCEPTED).body(tokenToValidate)
        }else{
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

}