package com.trainee.appinventiv.eccoscreens.validator

import android.util.Patterns

object EmailValidator : Validator {
    override fun valid( email : String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}