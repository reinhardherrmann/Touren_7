package de.orome.touren7.view.home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.orome.touren7.R
import de.orome.touren7.databinding.ActivityMainBinding
import de.orome.touren7.view.touren.TourenActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}