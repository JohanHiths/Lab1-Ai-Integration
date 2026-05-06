package ai.ai



import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry


@SpringBootApplication
@EnableRetry
class AiApplication

fun main(args: Array<String>) {

    val client = 
	runApplication<AiApplication>(*args)

}
