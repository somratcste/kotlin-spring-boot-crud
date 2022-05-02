package info.somrat.kotlincrud.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloController {

    @GetMapping("/")
    fun helloMessage(): String {
        return "Hello Kotlin World!";
    }
}