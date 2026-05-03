package ai.ai

class ResourceNotFoundException(message: String) : RuntimeException(message)

class InsufficientCreditsException(message: String) : RuntimeException(message)