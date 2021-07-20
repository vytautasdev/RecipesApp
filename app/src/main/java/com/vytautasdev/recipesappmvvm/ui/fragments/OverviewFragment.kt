package com.vytautasdev.recipesappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.vytautasdev.recipesappmvvm.R
import com.vytautasdev.recipesappmvvm.bindingadapters.RecipesRowBinding
import com.vytautasdev.recipesappmvvm.databinding.FragmentOverviewBinding
import com.vytautasdev.recipesappmvvm.models.Result
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.RECIPE_RESULT_KEY
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val bundle: Result = args!!.getParcelable<Result>(RECIPE_RESULT_KEY) as Result

        binding.mainImageView.load(bundle.image)
        binding.favoriteRowNameTextView.text = bundle.title
        binding.likesTextView.text = bundle.aggregateLikes.toString()
        binding.timeTextView.text = bundle.readyInMinutes.toString()

//        bundle?.summary.let {
//            val summary = Jsoup.parse(it).text()
//            binding.summaryTextView.text = summary
//        }

        RecipesRowBinding.parseHtml(binding.summaryTextView, bundle.summary)

        updateColors(bundle.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView)
        updateColors(bundle.vegan, binding.veganTextView, binding.veganImageView)
        updateColors(bundle.cheap, binding.cheapTextView, binding.cheapImageView)
        updateColors(bundle.dairyFree, binding.dairyTextView, binding.dairyImageView)
        updateColors(bundle.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView)
        updateColors(bundle.veryHealthy, binding.healthyTextView, binding.healthyImageView)

        return binding.root
    }

    private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}