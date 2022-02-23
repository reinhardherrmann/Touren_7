package de.orome.touren7.view.touren

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import de.orome.touren7.R
import de.orome.touren7.databinding.FragmentTourenDetailBinding
import de.orome.touren7.helper.Helper
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.database.entity.Tour
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [TourenDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TourenDetailFragment : Fragment() {

    private val args: TourenDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentTourenDetailBinding
    private lateinit var viewModel: TourenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val selTour = args.selTour
        val helper = Helper()
        var dat: String = " "
        var std: String = ""
        var minute: String = ""
        var tag: String = ""
        var monat: String = ""
        // Inflate the layout for this fragment
        binding = FragmentTourenDetailBinding.inflate(inflater, container, false)
        val dao = TourenDb.getInstance(requireActivity().application).tourDao
        val repository = TourenRepository(dao)

        val factory = TourenViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(TourenViewModel::class.java)
        binding.tViewModel = viewModel
        //binding.lifecycleOwner = this
        //var tour = viewModel.get

        val lblFragment = "Übersicht Tour ${selTour.tourNummer}"

        binding.apply {
            tvLblFragmentTourenDetails.setText(lblFragment)
            tvTourenDetailCosZeit.setText(selTour.tourDauer)
            tvTourenDetailDepotzeitVt.setText(selTour.DepotzeitVt)
            tvTourenDetailDepotzeitNt.setText(selTour.DepotzeitNt)
            tvTourenDetailTourStatus.setText(selTour.tourStatus)
            tvTourenDetailStartKilometer.setText(selTour.tourAnfangsKm.toString())
            tvTourenDetailEndeKilometer.setText(selTour.tourEndeKm.toString())
            tvTourenDetailGesamtKilometer.setText(selTour.tourGesamtKm.toString())
        }


        return binding.root
    }


    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}