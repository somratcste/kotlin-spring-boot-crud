package info.somrat.kotlincrud.controller

import info.somrat.kotlincrud.model.Gadget
import info.somrat.kotlincrud.repository.GadgetRepository
import org.apache.commons.lang3.ObjectUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api")
class GadgetController(private val gadgetRepository: GadgetRepository) {

    @GetMapping("/")
    fun display(): String = "Spring Boot Crud Operation with Kotlin and H2....!";

    @GetMapping("/gadgets")
    fun fetchGadgets(): ResponseEntity<List<Gadget>> {
        val gadgets = gadgetRepository.findAll()
        if (gadgets.isEmpty()) {
            return ResponseEntity<List<Gadget>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Gadget>>(gadgets, HttpStatus.OK)
    }

    @PostMapping("/gadgets")
    fun addNewGadget(@RequestBody gadget: Gadget, uri: UriComponentsBuilder): ResponseEntity<Gadget> {
        val persistedGadget = gadgetRepository.save(gadget)
        if (ObjectUtils.isEmpty(persistedGadget)) {
            return ResponseEntity<Gadget>(HttpStatus.BAD_REQUEST)
        }
        val headers = HttpHeaders()
        headers.setLocation(uri.path("/gadget/{gadgetId}").buildAndExpand(gadget.gadgetId).toUri());
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @GetMapping("/gadgets/{id}")
    fun fetchGadgetById(@PathVariable("id") gadgetId: Long): ResponseEntity<Gadget> {
        val gadget = gadgetRepository.findById(gadgetId)
        if (gadget.isPresent) {
            return ResponseEntity<Gadget>(gadget.get(), HttpStatus.OK)
        }
        return ResponseEntity<Gadget>(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/gadgets/{id}")
    fun removeGadgetById(@PathVariable("id") gadgetId: Long): ResponseEntity<Void> {
        val gadget = gadgetRepository.findById(gadgetId)
        if (gadget.isPresent) {
            gadgetRepository.deleteById(gadgetId)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("/gadgets/{id}")
    fun updateGadgetById(@PathVariable("id") gadgetId: Long, @RequestBody gadget: Gadget): ResponseEntity<Gadget> {
        return gadgetRepository.findById(gadgetId).map { gadgetDetails ->
            val updatedGadget: Gadget = gadgetDetails.copy(
                gadgetCategory = gadget.gadgetCategory,
                gadgetName = gadget.gadgetName,
                gadgetPrice = gadget.gadgetPrice,
                gagdetAvailability = gadget.gagdetAvailability
            )
            ResponseEntity(gadgetRepository.save(updatedGadget), HttpStatus.OK)
        }.orElse(ResponseEntity<Gadget>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

    @DeleteMapping("/gadgets")
    fun removeGadgets(): ResponseEntity<Void> {
        val gadgets = gadgetRepository.findAll()
        if (gadgets.isEmpty()) {
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        gadgetRepository.deleteAll()
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}