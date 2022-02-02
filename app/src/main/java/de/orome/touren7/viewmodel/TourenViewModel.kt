package de.orome.touren7.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.orome.touren7.model.database.dao.TourDao
import de.orome.touren7.model.database.entity.Tour
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.helper.Event
import kotlinx.coroutines.launch

class TourenViewModel(private val tourenRepository: TourenRepository): ViewModel(), Observable {

    val touren = tourenRepository.allLiveTouren
    private val isUpdateOrDelete = false
    private lateinit var tourToUpdateOrDelete: Tour

    @Bindable
    val inputTourNummer = MutableLiveData<String?>()
    @Bindable
    val inputTourDatum = MutableLiveData<String?>()
    @Bindable
    val inputTourDauer = MutableLiveData<String?>()
    @Bindable
    val inputTourDepotzeitVt = MutableLiveData<String?>()
    @Bindable
    val inputTourDepotzeitNt = MutableLiveData<String?>()
    @Bindable
    val inputTourFahrerNummer = MutableLiveData<String?>()
    @Bindable
    val inputTourFahrzeugNummer = MutableLiveData<String?>()

    // Meldungen um mit der View zu kommunizieren
    private val statusMessage = MutableLiveData<Event<String>>()
    val _statusMessage : LiveData<Event<String>>
    get() = statusMessage


    fun saveOrUpdateTour(){
        // TODO Funktion mit Leben füllen
        if (inputTourNummer.value == null){
            statusMessage.value = Event("Bitte geben Sie eine gültige TourNummer ein")
        } else if (inputTourDatum.value == null){
            statusMessage.value = Event("Bitte geben Sie das TourDatum ein")
        } else if (inputTourDauer.value == null){
            statusMessage.value = Event("Bitte geben Sie die Cos-Zeit an")
        } else if (inputTourFahrerNummer.value == null){
            statusMessage.value = Event("Bitte geben Sie die Fahrernummer an")
        }else if (inputTourFahrzeugNummer.value == null){
            statusMessage.value = Event("Bitte geben Sie die Fahrzeugnummer an")
        } else {
            // alle erfoderlichen daten wurden eingegeben
            val tourNummer = inputTourNummer.value!!
            val tourDatum = inputTourDatum.value!!
            val tourDauer = inputTourDauer.value!!
            var tourDepotzeitVt = ""
            var tourDepotzeitNt = ""
            var tourFahrerNummer = inputTourFahrerNummer.value!!
            if (inputTourFahrerNummer.value == null){
                tourFahrerNummer = "0056"
            }
            var tourFahrzeugNummer = inputTourFahrzeugNummer.value!!
            if (inputTourFahrzeugNummer.value == null){
                tourFahrzeugNummer = "0098"
            }
            if (inputTourDepotzeitVt.value == null){
                tourDepotzeitVt = ""
            } else {
                tourDepotzeitVt = inputTourDepotzeitVt.value!!
            }
            if (inputTourDepotzeitNt.value == null){
                tourDepotzeitNt = ""
            } else {
                tourDepotzeitNt = inputTourDepotzeitNt.value!!

            }
            insertTour(Tour(0,tourNummer.toInt(),tourDatum,tourDauer,tourDepotzeitVt,
                tourDepotzeitNt,tourFahrerNummer,tourFahrzeugNummer)
            )
            clearFormValues()
        }
    }


    private fun clearFormValues(){
        inputTourNummer.value = null
        inputTourDatum.value = null
        inputTourDauer.value = null
        inputTourDepotzeitVt.value = null
        inputTourDepotzeitNt.value = null
        inputTourFahrerNummer.value = null
        inputTourFahrzeugNummer.value = null
    }



    fun cancelInsertTour(){
        clearFormValues()
    }


    fun insertTour(tour: Tour) = viewModelScope.launch {
        val newRowId = tourenRepository.insertTour(tour)
        if (newRowId > -1){
            statusMessage.value = Event("Tour ${tour.tourNummer} mit ID $newRowId gespeichert.")
        } else{
            statusMessage.value = Event("Fehler beim Speichern der Tour !")}
    }





































    // ----------------------- Element werden z.Zt. nicht benötigt
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}