package de.orome.touren7.view.touren

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import de.orome.touren7.R
import de.orome.touren7.databinding.ActivityMainBinding
import de.orome.touren7.databinding.ActivityTourenMainBinding
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory

class TourenActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityTourenMainBinding
    private lateinit var tViewModel: TourenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_touren_main)
        val tDao = TourenDb.getInstance(application).tourDao
        val repository = TourenRepository(tDao)
        val factory = TourenViewModelFactory(repository)
        tViewModel = ViewModelProvider(this,factory).get(TourenViewModel::class.java)
        binding.tViewModel = tViewModel
        binding.lifecycleOwner = this
    }
}