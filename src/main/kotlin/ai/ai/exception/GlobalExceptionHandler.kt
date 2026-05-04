package ai.ai.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

class InsufficientCreditsException(message: String) : RuntimeException(message)

class InvalidRequestException(message: String) : RuntimeException(message)

class InternalServerErrorException(message: String) : RuntimeException(message)

class ServiceUnavailableException(message: String) : RuntimeException(message)