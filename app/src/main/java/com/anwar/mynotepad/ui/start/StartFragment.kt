package com.anwar.mynotepad.ui.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.ui.home.HomeFragmentViewModel
import com.anwar.mynotepad.ui.home.HomeFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataSource = NoteContactDatabase.getInstance(requireContext()).noteContactDao

        val homeFragmentViewModel by viewModels<HomeFragmentViewModel> {
            HomeFragmentViewModelFactory(
                dataSource
            )
        }

        homeFragmentViewModel.hasContent.observe(this, Observer {
            val navController = this.findNavController()
            if (it == true){
                //Start the ContactNoteList Fragment if the database has any content
              val action =
                  StartFragmentDirections.actionStartFragmentToItemListFragment()
                navController.navigate(action)
            }else{
                //Start the Home Fragment if the database has no content
                val action =
                    StartFragmentDirections.actionStartFragmentToHomeFragment()
                navController.navigate(action)
            }
        })
        return null
    }

}
