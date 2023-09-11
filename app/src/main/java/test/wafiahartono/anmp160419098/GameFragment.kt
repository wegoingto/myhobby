package test.wafiahartono.anmp160419098

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textPlayersTurn = view.findViewById<TextView>(R.id.text_players_turn)
        val buttonBack = view.findViewById<Button>(R.id.button_back)

        arguments?.let {
            val playerName = GameFragmentArgs.fromBundle(it).playerName

            textPlayersTurn.text = "$playerName's turn"
        }

        buttonBack.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment()

            Navigation.findNavController(view).navigate(action)
        }
    }
}
