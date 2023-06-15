package com.example.myapplication.domain.use_case.validation

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password can not be empty!"
            )
        }

        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password length must be at least 6 characters long!"
            )
        }

        return ValidationResult(successful = true)
    }
}