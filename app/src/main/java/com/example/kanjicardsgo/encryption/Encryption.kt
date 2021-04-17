package com.example.kanjicardsgo.encryption

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class Encryption {

    fun encrypt(pass: CharArray){
        // Create salt
        val random = SecureRandom()
        val salt = ByteArray(256)
        random.nextBytes(salt)

        // Salt the password, create encryption object
        val pbKeySpec = PBEKeySpec(pass, salt, 1324, 256)
        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

        // Generate the key as a byte array
        val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded
        // Wrap byte array into secret key spec object
        val keySpec = SecretKeySpec(keyBytes, "AES")

        // Create init vector
        // Created 16 bytes of random data.
        val ivRandom = SecureRandom()
        val iv = ByteArray(16)
        ivRandom.nextBytes(iv)
        // Packaged it into an IvParameterSpec object.
        val ivSpec = IvParameterSpec(iv) // 2

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding") // 1
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encrypted = cipher.doFinal(keyBytes) // 2
    }

}