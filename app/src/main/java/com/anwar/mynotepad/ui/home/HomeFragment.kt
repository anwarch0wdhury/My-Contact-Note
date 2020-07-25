package com.anwar.mynotepad.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.anwar.mynotepad.R
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.databinding.FragmentHomeBinding
import com.anwar.mynotepad.utils.Popup

import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        //Layout binding
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        //Contact Item DAO
        val dataSource = NoteContactDatabase.getInstance(this.requireContext()).noteContactDao

        //ViewModel
        val homeFragmentViewModel by viewModels<HomeFragmentViewModel> {
            HomeFragmentViewModelFactory(
                dataSource
            )
        }

        /**
         * This observer checks if the database has any content.
         * If there is content in the database, it triggers a navigation
         * from the HomeFragment to the ContactListFragment
         */
        homeFragmentViewModel.hasContent.observe(this, Observer { state ->
            if (state){
                this.findNavController().navigate(R.id.action_homeFragment_to_itemListFragment)
                homeFragmentViewModel.doneNavigating()
            }
            Timber.i( "my Message Home Fragment")
        })

        Timber.i("Third Question Observers? ${homeFragmentViewModel.hasContent.hasActiveObservers()} ")

        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this

        /**
         *  Onclick listener for the FAB to add a new item.
         *  This creates a pop dialog
         *  @see [Popup]
         */
        binding.btnNoteAdd.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }
        return binding.root
    }

}
