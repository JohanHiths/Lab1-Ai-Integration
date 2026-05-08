package ai.ai



import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.resilience.annotation.EnableResilientMethods


@SpringBootApplication
@EnableResilientMethods
class AiApplication

fun main(args: Array<String>) {

    val client = 
	runApplication<AiApplication>(*args)

}
