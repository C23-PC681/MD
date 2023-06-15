package com.example.myapplication.domain.use_case.validation

class ValidateNameUseCase {
    operator fun invoke(name: String): ValidationResult {
        if (name.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name can not be empty!"
            )
        }

        if (name.length < 3) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name length must be at least 3 characters long!"
            )
        }

        return ValidationResult(successful = true)
    }
}