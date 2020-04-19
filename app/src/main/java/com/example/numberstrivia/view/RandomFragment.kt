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
import com.example.numberstrivia.databinding.FragmentRandomBinding
import com.example.numberstrivia.viewmodel.RandomViewModel

/**
 * A simple [Fragment] subclass.
 */
class RandomFragment : Fragment() {

    //variables
    private lateinit var binding: FragmentRandomBinding
    private lateinit var randomViewModel: RandomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_random, container, false)
        //call viewModel
        randomViewModel = ViewModelProvider(this).get(RandomViewModel::class.java)
        binding.viewModel = RandomViewModel()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDisplay.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.automobile)
        binding.randomTextView.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.greatadventure)

        //trivia button click event
        binding.buttonRandomTrivia.setOnClickListener {
            binding.viewModel?.loadRemoteTrivia()
        }

        //year button click event
        binding.buttonRandomYear.setOnClickListener {
            binding.viewModel?.loadRemoteYear()
        }

        //date button click event
        binding.buttonRandomDate.setOnClickListener {
            binding.viewModel?.loadRemoteDate()
        }

        //math button click event
        binding.buttonRandomMath.setOnClickListener {
            binding.viewModel?.loadRemoteMath()
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