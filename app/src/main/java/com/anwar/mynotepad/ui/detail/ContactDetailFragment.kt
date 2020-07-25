package com.anwar.mynotepad.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.databinding.FragmentItemDetailBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ContactDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentItemDetailBinding.inflate(layoutInflater)

        val dataSource = NoteContactDatabase.getInstance(requireContext()).noteContactDao

        val noteDetailFragmentArgs by navArgs<ContactDetailFragmentArgs>()

        val viewModel by viewModels<ContactDetailFragmentViewModel> {
            ContactDetailFragmentViewModelFactory(
                dataSource,
                noteDetailFragmentArgs.itemId
            )

        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.item.observe(viewLifecycleOwner, Observer {
            binding.item = it
        })
        Timber.i( "My Message Item Detail fragment")
        return binding.root
    }


}
