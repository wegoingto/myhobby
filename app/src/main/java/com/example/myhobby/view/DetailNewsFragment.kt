package com.example.myhobby.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myhobby.model.Article
import com.example.myhobby.databinding.FragmentDetailNewsBinding

class DetailNewsFragment : Fragment() {

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = arguments?.getParcelable<Article>("article")
        val content = splitContent(article?.content.orEmpty())

        binding.tvTitle.text = article?.title
        binding.tvWriter.text = article?.writer
        Glide.with(binding.root.context)
            .load(article?.imageUrl)
            .into(binding.ivNews)

        binding.tvContent.text = content[currentPage]

        binding.btnNext.setOnClickListener {
            if (currentPage < content.size - 1) {
                currentPage++
                binding.tvContent.text = content[currentPage]
            }
            if (currentPage != 0) {
                binding.btnPrev.apply {
                    val colorStateList = ColorStateList.valueOf(Color.parseColor("#800080"))
                    this.backgroundTintList = colorStateList
                    (this as? Button)?.setTextColor(Color.WHITE)
                }
            }
            val colorStateList = ColorStateList.valueOf(if (currentPage == content.size - 1) Color.WHITE else Color.parseColor("#800080"))
            it.backgroundTintList = colorStateList
            (it as? Button)?.setTextColor(if (currentPage == content.size - 1) Color.BLACK else Color.WHITE)
        }
        binding.btnPrev.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                binding.tvContent.text = content[currentPage]
            }

            if (currentPage != content.size - 1) {
                binding.btnNext.apply {
                    val colorStateList = ColorStateList.valueOf(Color.parseColor("#800080"))
                    this.backgroundTintList = colorStateList
                    (this as? Button)?.setTextColor(Color.WHITE)
                }
            }

            val colorStateList = ColorStateList.valueOf(if (currentPage == 0) Color.WHITE else Color.parseColor("#800080"))
            it.backgroundTintList = colorStateList
            (it as? Button)?.setTextColor(if (currentPage == 0) Color.BLACK else Color.WHITE)
        }
    }

    private fun splitContent(content: String): List<String> {
        val chunkSize = 600
        val chunks = mutableListOf<String>()

        var index = 0
        while (index < content.length) {
            chunks.add(content.substring(index, (index + chunkSize).coerceAtMost(content.length)))
            index += chunkSize
        }

        return chunks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}