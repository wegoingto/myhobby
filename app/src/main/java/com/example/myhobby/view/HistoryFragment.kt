package com.example.myhobby.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhobby.R
import com.example.myhobby.databinding.FragmentHistoryBinding
import com.example.myhobby.viewmodel.HomeViewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ArticleAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val historyArticle = homeViewModel.getAllHistoryReadingArticle(requireContext())
        binding.tvEmpty.isVisible = historyArticle.isEmpty()
        adapter = ArticleAdapter(historyArticle) {
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            view.findNavController()
                .navigate(R.id.action_historyFragment_to_detailNewsFragment, bundle)
        }
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}