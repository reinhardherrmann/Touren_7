package de.orome.touren7.view.home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.orome.touren7.R
import de.orome.touren7.databinding.ActivityMainBinding
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.view.touren.TourenActivity
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TourenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


    }
}