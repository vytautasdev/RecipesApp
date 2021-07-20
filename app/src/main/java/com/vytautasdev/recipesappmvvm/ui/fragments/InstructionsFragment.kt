package com.vytautasdev.recipesappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.vytautasdev.recipesappmvvm.R
import com.vytautasdev.recipesappmvvm.databinding.FragmentInstructionsBinding
import com.vytautasdev.recipesappmvvm.models.Result
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.RECIPE_RESULT_KEY

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)

        _binding =
            FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val bundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        binding.instructionsWebView.webViewClient = object : WebViewClient() {}

        val websiteUrl: String = bundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}