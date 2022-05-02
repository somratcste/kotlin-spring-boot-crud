package info.somrat.kotlincrud.repository

import info.somrat.kotlincrud.model.Gadget
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GadgetRepository : JpaRepository<Gadget, Long>