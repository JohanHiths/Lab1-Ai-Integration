package ai.ai.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

class InsufficientCreditsException(message: String) : RuntimeException(message)