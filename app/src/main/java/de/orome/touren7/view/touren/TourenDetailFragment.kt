package de.orome.touren7.view.touren

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import de.orome.touren7.R
import de.orome.touren7.databinding.FragmentTourenDetailBinding
import de.orome.touren7.helper.Helper
import de.orome.touren7.viewmodel.TourenViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TourenDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TourenDetailFragment : Fragment() {

    val args: TourenDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentTourenDetailBinding
    private lateinit var viewModel: TourenViewModel


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
        // Inflate the layout for this fragment
        binding = FragmentTourenDetailBinding.inflate(inflater, container, false)
        val tourNummer = args.tourNumber
        val lblFragment = "Übersicht Tour $tourNummer"

        binding.tvLblFragmentTourenDetails.setText(lblFragment)

        return binding.root
    }


    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}