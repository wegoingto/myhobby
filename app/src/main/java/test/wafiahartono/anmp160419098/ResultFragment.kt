package test.wafiahartono.anmp160419098

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val score = ResultFragmentArgs.fromBundle(it).score

            view.findViewById<TextView>(R.id.text_score).text = "Your score is $score"
        }

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            Navigation.findNavController(view).navigate(
                ResultFragmentDirections.actionMainFragment()
            )
        }
    }
}
