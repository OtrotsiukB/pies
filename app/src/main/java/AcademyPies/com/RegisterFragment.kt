package AcademyPies.com

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listener:ClickListener? = null
    private var SingOnFirstForm:Button?=null
    private var RegisterOnFirstForm:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SingOnFirstForm = view.findViewById<Button>(R.id.b_Sing_in).apply {

            setOnClickListener {
                val v_login:EditText = view.findViewById<EditText>(R.id.Login)
                val v_pass:EditText = view.findViewById<EditText>(R.id.Password)
                listener?.singFirstFragment(v_login.text.toString(),v_pass.text.toString()) }
        }
        RegisterOnFirstForm= view.findViewById<Button>(R.id.b_register).apply {
            setOnClickListener {
                val v_login:EditText = view.findViewById<EditText>(R.id.Login)
                val v_pass:EditText = view.findViewById<EditText>(R.id.Password)
              //  listener?.registerFirstFragment(v_login.text.toString(),v_pass.text.toString())
            listener?.openRegisterFragment()
            }
        }

    }
    fun setListener(l: ClickListener) {
        listener = l
    }

    //TODO(WS2:4) Create interface ClickListener
    interface ClickListener {
        fun singFirstFragment(login:String,pass:String)
        fun registerFirstFragment(login:String, pass:String)
        fun openRegisterFragment()

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
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}