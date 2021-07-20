package com.vytautasdev.recipesappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vytautasdev.recipesappmvvm.R
import com.vytautasdev.recipesappmvvm.adapters.IngredientsAdapter
import com.vytautasdev.recipesappmvvm.databinding.FragmentIngredientsBinding
import com.vytautasdev.recipesappmvvm.models.Result
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.RECIPE_RESULT_KEY

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            FragmentIngredientsBinding.inflate(inflater, container, false)

        val args = arguments
        val bundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView()
        bundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}