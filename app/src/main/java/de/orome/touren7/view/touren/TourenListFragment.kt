package de.orome.touren7.view.touren

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.orome.touren7.R
import de.orome.touren7.databinding.FragmentTourenListBinding
import de.orome.touren7.model.TourenListAdapter
import de.orome.touren7.model.database.TourenDb
import de.orome.touren7.model.database.entity.Tour
import de.orome.touren7.model.repositories.TourenRepository
import de.orome.touren7.viewmodel.TourenViewModel
import de.orome.touren7.viewmodel.TourenViewModelFactory


class TourenListFragment : Fragment() {

    private lateinit var binding: FragmentTourenListBinding
    private lateinit var viewModel: TourenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_touren_list, container, false)
        binding = FragmentTourenListBinding.inflate(inflater,container,false)
        val dao = TourenDb.getInstance(requireActivity().application).tourDao
        val repository = TourenRepository(dao)
        val factory = TourenViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(TourenViewModel::class.java)
        binding.tViewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvFragmentTourenList.adapter
        initRecyclerView()
        binding.fabAddNewTour.setOnClickListener {
            it.findNavController().navigate(R.id.action_tourenListFragment_to_newTourFragment)
        }
        return binding.root
    }

    private fun initRecyclerView(){
        binding.rvFragmentTourenList.layoutManager = LinearLayoutManager(requireActivity())
        displayTourenList()
    }

    private fun displayTourenList(){
        viewModel.liveTourenList.observe(viewLifecycleOwner, Observer {
            binding.rvFragmentTourenList.adapter = TourenListAdapter(it,{selectedTour: Tour ->listItemClicked(selectedTour)})
        })
    }

    private fun listItemClicked(selectedTour: Tour) {
        val tourNummer = selectedTour.tourNummer
        val action = TourenListFragmentDirections.actionTourenListFragmentToTourenDetailFragment(tourNummer)
        findNavController().navigate(action)
    }
}