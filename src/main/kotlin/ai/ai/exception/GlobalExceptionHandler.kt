package ai.ai.exception

import org.springframework.web.client.HttpStatusCodeException

class ResourceNotFoundException(message: String) : RuntimeException(message)

class InsufficientCreditsException(message: String) : RuntimeException(message)

class InvalidRequestException(message: String) : RuntimeException(message)

class InternalServerErrorException(message: String) : RuntimeException(message)

class ServiceUnavailableException(message: String, ex: HttpStatusCodeException) : RuntimeException(message)