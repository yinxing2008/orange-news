package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {
    private var index = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_blank, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "this Tab $index"
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            val fragment = BlankFragment()
            fragment.index = position
            return fragment
        }
    }
}