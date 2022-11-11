package com.trainee.appinventiv.eccoscreens.validator

import android.util.Patterns
import java.util.regex.Pattern

object PasswordValidator : Validator {

    val Password_Regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{12,}\$"

    /**
     * Password should contains
     * atleast a number,lowercase, uppercase.
     * no spaces
     * minimum 12 character
     */
    override fun valid(password: String): Boolean {
        return Pattern.compile(Password_Regex).matcher(password).matches()
    }
}