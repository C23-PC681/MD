package com.example.myapplication.domain.use_case.validation

import android.util.Patterns
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
) {
    operator fun invoke(email: String): ValidationResult {
        if (email.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email can not be empty!"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email should be formatted correctly!"
            )
        }

        return ValidationResult(successful = true)
    }
}