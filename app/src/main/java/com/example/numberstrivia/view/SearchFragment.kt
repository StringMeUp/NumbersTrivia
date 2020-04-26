package com.example.numberstrivia.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.numberstrivia.R
import com.example.numberstrivia.databinding.FragmentSearchBinding
import com.example.numberstrivia.viewmodel.SearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    //vars
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        //call viewModel
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDescription.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.greatadventure)
        binding.textDisplay.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.automobile)

        //search trivia on click event
        binding.buttonSearchTrivia.setOnClickListener {
            if (binding.editSearch.text.toString() == "") {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid search item",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchViewModel.searchTriviaFacts(binding.editSearch.text.toString())
            }
        }

        //search year on click event
        binding.buttonSearchYear.setOnClickListener {
            if (binding.editSearch.text.toString() == "") {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid search item",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchViewModel.searchYearFacts(binding.editSearch.text.toString())
            }
        }

        //search math on click event
        binding.buttonSearchMath.setOnClickListener {
            if (binding.editSearch.text.toString() == "") {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid search item",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchViewModel.searchMathFacts(binding.editSearch.text.toString())
            }
        }

        //search date on click event
        binding.buttonSearchDate.setOnClickListener {
            if (binding.editSearch.text.toString() == "") {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid search item",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchViewModel.searchDateFacts(binding.editSearch.text.toString())
            }
        }

        binding.viewModel?.isError?.observe(viewLifecycleOwner, Observer { anError ->
            anError?.let {
                if (it) Toast.makeText(
                    requireContext(),
                    "Please try again!",
                    Toast.LENGTH_SHORT
                ).show() else println("Success")
            }
        })
    }
}