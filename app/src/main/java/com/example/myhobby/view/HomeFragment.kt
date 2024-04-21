package com.example.myhobby.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhobby.R
import com.example.myhobby.databinding.FragmentHomeBinding
import com.example.myhobby.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        adapter = ArticleAdapter(homeViewModel.getAllNewsArticle()) {
            homeViewModel.saveArticle(requireContext(), it)
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_detailNewsFragment, bundle)
        }
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}