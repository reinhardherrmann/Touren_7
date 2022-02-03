package de.orome.touren7.view.home


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.orome.touren7.R
import de.orome.touren7.databinding.ActivityMainBinding
import de.orome.touren7.helper.Helper
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.view.touren.TourenActivity
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TourenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val helper = Helper()
        var dat: String = " "
        var std: String = ""
        var minute: String = ""
        var tag: String = ""
        var monat: String = ""

        super.onCreate(savedInstanceState)
        //helper.setUpDirectory()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(binding.root)
        val tDao = TourenDb.getInstance(application).tourDao
        val repository = TourenRepository(tDao)
        val factory = TourenViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(TourenViewModel::class.java)
        binding.tViewModel = viewModel
        binding.lifecycleOwner = this

        // Observer für Statusmeldungen
        viewModel._statusMessage.observe(this, Observer {
            it.getContentIfNotHandled().let {
                when(it)  {
                    "Bitte geben Sie eine gültige TourNummer ein" -> binding.etTournummerTourdatenErfassen.requestFocus()
                    "Bitte geben Sie das TourDatum ein"  -> binding.etTourdatumTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Cos-Zeit an" -> binding.etTourdauerTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Fahrernummer an" -> binding.etFahrerNummerTourdatenErfassen.requestFocus()
                    "Bitte geben Sie die Fahrzeugnummer an" -> binding.etFahrzeugNummerTourdatenErfassen.requestFocus()
                }
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })

        // Dialoge zur Eingabe von Datum und Zeiten realisieren
        binding.etTourdatumTourdatenErfassen.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { v, year, month, day ->

                    tag = helper.modifyMinuteHourDayMonth(day)
                    monat = helper.modifyMinuteHourDayMonth(month + 1)

                    dat = tag + "." + monat + "." + year.toString()
                    binding.etTourdatumTourdatenErfassen.setText(dat)
                }, year, month, day
            )
            dpd.show()
            }

        binding.etTourdauerTourdatenErfassen.setOnClickListener {
            // TODO Standardzeit für Tourdauer auf 03:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 3 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { v, hh, mm ->
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
        }

        binding.etDepotzeitVtTourdatenErfassen.setOnClickListener {
            // TODO Standardzeit für Tourdauer auf 01:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 1 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { v, hh, mm ->
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
        }

        binding.etDepotzeitNtTourdatenErfassen.setOnClickListener {
            // TODO Standardzeit für Tourdauer auf 00:25 in Shared Preferences setzen
            val c = Calendar.getInstance()
            val hh = 0 //c.get(Calendar.HOUR_OF_DAY)
            val mm = 25 //c.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { v, hh, mm ->
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
        }


    }
}