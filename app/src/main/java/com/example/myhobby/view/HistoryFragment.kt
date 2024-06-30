package com.example.myhobby.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhobby.ArticleListener
import com.example.myhobby.R
import com.example.myhobby.databinding.FragmentHistoryBinding
import com.example.myhobby.view.ArticleAdapter
import com.example.myhobby.viewmodel.HomeViewModel
import com.example.myhobby.model.Article

class HistoryFragment : Fragment(), ArticleListener {

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
        homeViewModel.getAllHistoryReadingArticle()
        homeViewModel.historyReadingArticle.observe(viewLifecycleOwner) { historyArticle ->
            binding.tvEmpty.isVisible = historyArticle.isEmpty()
            adapter = ArticleAdapter(historyArticle, this)
            binding.rvNews.adapter = adapter
            binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArticleClicked(article: Article) {
        val bundle = Bundle().apply {
            putParcelable("article", article)
        }
        view?.findNavController()
            ?.navigate(R.id.action_historyFragment_to_detailNewsFragment, bundle)
    }

}