package com.trainee.appinventiv.eccoscreens.validator

import android.util.Patterns

object PhoneNumberValidator : Validator {
    override fun valid(phoneNumber : String): Boolean {
        return Patterns.PHONE.matcher(phoneNumber).matches()
    }
}