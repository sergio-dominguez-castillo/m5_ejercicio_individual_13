package cl.bootcamp.ejercicioindividual13.model

data class PatientsListState (
    var id: Int = 0,
    val name: String = "",
    var namePatient: String = "",
    val flagModal: Boolean = false,
    val age: String = "",
    val weight: String = "",
    val height: String = "",
    val bmi: String = "0.0",
    val flagAlert: Boolean = false,
    val flagAge: Boolean = false,
    val flagWeight: Boolean = false,
    var flagHeight: Boolean = false,
    val flagGender: Boolean = false


)