package info.somrat.kotlincrud.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GadgetController {

    @GetMapping("/")
    fun display(): String = "Spring Boot Crud Operation with Kotlin and H2....!";
}