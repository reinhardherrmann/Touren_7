package de.orome.touren7.view.touren

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import de.orome.touren7.R
import de.orome.touren7.databinding.NewTourFragmentBinding
import de.orome.touren7.helper.Helper
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.database.entity.Tour
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory
import java.util.*

class NewTourFragment : Fragment() {

    private lateinit var binding: NewTourFragmentBinding
    private lateinit var viewModel: TourenViewModel
    private lateinit var tour: MutableLiveData<Tour>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val helper = Helper()
        var dat: String = " "
        var std: String = ""
        var minute: String = ""
        var tag: String = ""
        var monat: String = ""

        binding = NewTourFragmentBinding.inflate(inflater,container,false)
        val dao = TourenDb.getInstance(requireActivity().application).tourDao
        val repository = TourenRepository(dao)
        val factory = TourenViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(TourenViewModel::class.java)
        binding.tViewModel = viewModel
        binding.lifecycleOwner = this.requireActivity()

        // Observer für Statusmeldungen
        viewModel._statusMessage.observe(requireActivity(), Observer {
            it.getContentIfNotHandled().let {
                when(it){
                    "Bitte geben Sie eine gültige TourNummer ein" -> binding.etTourdatumTourdatenErfassen.requestFocus()
                    "Bitte geben Sie das TourDatum ein"  -> binding.etTourdatumTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Cos-Zeit an" -> binding.etTourdauerTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Fahrernummer an" -> binding.etFahrerNummerTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Fahrzeugnummer an" -> binding.etFahrzeugNummerTourdatenErfassen.requestFocus()
                    "Eingabe beendet." -> returnToTourenList()
                }
            }
        })

        // Dialoge zur Eingabe von Datum und Zeiten realisieren
        binding.etTourdatumTourdatenErfassen.setOnLongClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                requireContext(), DatePickerDialog.OnDateSetListener { v, year, month, day ->

                    //val helper
                    tag = helper.modifyMinuteHourDayMonth(day)
                    monat = helper.modifyMinuteHourDayMonth(month + 1)

                    dat = tag + "." + monat + "." + year.toString()
                    binding.etTourdatumTourdatenErfassen.setText(dat)
                }, year, month, day
            )
            dpd.show()
            return@setOnLongClickListener(true)
        }

        binding.etTourdauerTourdatenErfassen.setOnLongClickListener {
            // Standardzeit für Tourdauer auf 03:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 3 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                requireContext(), TimePickerDialog.OnTimeSetListener { v, hh, mm ->
                    if (hh < 10) {
                    }

                    // Zeitwerte auf 2-Stellige Anzeige umschalten
                    std = helper.modifyMinuteHourDayMonth(hh)
                    minute = helper.modifyMinuteHourDayMonth(mm)
                    dat = "$std:$minute"

                    binding.etTourdauerTourdatenErfassen
                        .setText(dat)
                }, hh, mm, true
            )
            dpd.show()
            return@setOnLongClickListener(true)
        }

        binding.etDepotzeitVtTourdatenErfassen.setOnLongClickListener {
            // Standardzeit für Tourdauer auf 01:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 1 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                requireContext(), TimePickerDialog.OnTimeSetListener { v, hh, mm ->
                    if (hh < 10) {
                    }

                    // Zeitwerte auf 2-Stellige Anzeige umschalten
                    std = helper.modifyMinuteHourDayMonth(hh)
                    minute = helper.modifyMinuteHourDayMonth(mm)
                    dat = "$std:$minute"

                    binding.etDepotzeitVtTourdatenErfassen
                        .setText(dat)
                }, hh, mm, true
            )
            dpd.show()
            return@setOnLongClickListener(true)
        }

        binding.etDepotzeitNtTourdatenErfassen.setOnLongClickListener {
            // Standardzeit für Tourdauer auf 00:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 0 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                requireContext(), TimePickerDialog.OnTimeSetListener { v, hh, mm ->
                    if (hh < 10) {
                    }

                    // Zeitwerte auf 2-Stellige Anzeige umschalten
                    std = helper.modifyMinuteHourDayMonth(hh)
                    minute = helper.modifyMinuteHourDayMonth(mm)
                    dat = "$std:$minute"

                    binding.etDepotzeitNtTourdatenErfassen
                        .setText(dat)
                }, hh, mm, true
            )
            dpd.show()
            return@setOnLongClickListener(true)
        }

        return binding.root

    }

    fun returnToTourenList(){
        findNavController().navigate(R.id.action_newTourFragment_to_tourenListFragment)
    }

}