package test.wafiahartono.anmp160419098

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class GameFragment : Fragment() {
    private var view: View? = null

    private var score = 0
    private var numbers = generateNumbers()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.view = view

        val textPlayersTurn = view.findViewById<TextView>(R.id.text_players_turn)
        val textInputAnswer = view.findViewById<TextInputLayout>(R.id.text_input_layout_answer)
        val editTextAnswer = view.findViewById<TextInputEditText>(R.id.edit_text_answer)
        val buttonSubmit = view.findViewById<Button>(R.id.button_submit)

        nextRound()

        arguments?.let {
            val playerName = GameFragmentArgs.fromBundle(it).playerName

            textPlayersTurn.text = "$playerName's turn"
        }

        buttonSubmit.setOnClickListener {
            if (editTextAnswer.text.isNullOrEmpty()) {
                textInputAnswer.error = "Please answer the question"
                return@setOnClickListener
            } else {
                textInputAnswer.error = null
            }

            val result = numbers.first + numbers.second
            val answer = editTextAnswer.text.toString().toInt()

            if (result == answer) {
                score++
                nextRound()
            } else {
                Navigation.findNavController(view).navigate(
                    GameFragmentDirections.actionResultFragment(score)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view = null
    }

    private fun nextRound() {
        numbers = generateNumbers()

        view?.apply {
            findViewById<TextView>(R.id.text_quiz).text = "${numbers.first} + ${numbers.second}"
            findViewById<TextInputEditText>(R.id.edit_text_answer).text = null
        }
    }

    private fun generateNumbers(): Pair<Int, Int> {
        return Pair(Random.nextInt(100), Random.nextInt(100))
    }
}
