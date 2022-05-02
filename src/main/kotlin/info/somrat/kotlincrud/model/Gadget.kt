package info.somrat.kotlincrud.model

import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@NoArgsConstructor
data class Gadget (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val gadgetId: Long,
    val gadgetName: String,
    val gadgetCategory: String?,
    val gagdetAvailability: Boolean = true,
    val gadgetPrice: Double
)