package AcademyPies.com

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserMainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var countPies:Int=0
    var nameUserOnfragmenUser:String=""
    var tv_countPies:TextView?=null
    var tv_nameUser:TextView?=null

    private var listener: ClickListener?=null
    private var topButton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setRetainInstance(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_countPies=view.findViewById(R.id.tv_countPie)
        tv_countPies?.text=countPies.toString()
        tv_nameUser=view.findViewById(R.id.v_nameUserOnUserFragment)
        tv_nameUser?.text=nameUserOnfragmenUser
        topButton = view.findViewById<Button>(R.id.b_topUserFragment).apply {
            setOnClickListener {
                listener?.openTop()
            }
        }

    }

    fun setListener(l: ClickListener) {
        listener = l
    }

    //TODO(WS2:4) Create interface ClickListener
    interface ClickListener {
        fun AllUser():Array<String>
        fun openTop()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ClickListener){
            listener=context
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        listener=null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserMainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserMainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}