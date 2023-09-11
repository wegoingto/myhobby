package test.wafiahartono.anmp160419098

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerName = view.findViewById<EditText>(R.id.edit_text_player_name)
        val buttonStart = view.findViewById<Button>(R.id.button_start)

        buttonStart.setOnClickListener {
            val action = MainFragmentDirections.actionGameFragment(playerName.text.toString())

            Navigation.findNavController(view).navigate(action)
        }
    }
}
