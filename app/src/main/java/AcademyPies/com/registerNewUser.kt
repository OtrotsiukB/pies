package AcademyPies.com

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registerNewUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class registerNewUser : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listener:ClickListenerReg? = null
    private var registeBut: Button?=null

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
        return inflater.inflate(R.layout.fragment_register_new_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registeBut=view.findViewById<Button>(R.id.b_reg).apply {


            setOnClickListener{
                val edEmail = view.findViewById<EditText>(R.id.edt_email).text.toString()
                if (edEmail!=""){
                    val btPass1 = view.findViewById<EditText>(R.id.edt_password).text.toString()
                    val btPass2 = view.findViewById<EditText>(R.id.edt_password1).text.toString()
                    if(btPass1==btPass2){
                        val nameOrKey =view.findViewById<EditText>(R.id.edt_name_user).text.toString()
                        if(nameOrKey!=""){
                            val rbmasha = view.findViewById<RadioButton>(R.id.rb_masha).isChecked
                            if(rbmasha==true){
                                if(nameOrKey=="pies"){
                                    listener?.registerNewUser(edEmail,btPass1,"Маша","Маша")

                                }else{
                                    Toast.makeText( activity, "Неверный ключ, ты не Маша!", Toast.LENGTH_LONG).show()
                                }


                            }else{
                                listener?.registerNewUser(edEmail,btPass1,"Пользователь",nameOrKey)

                            }


                        }else{
                            Toast.makeText( activity, "Укажите пользователя или ключ!", Toast.LENGTH_LONG).show()
                        }

                    }else{
                        Toast.makeText( activity, "Разные пароли!", Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(activity, "Пустое поле електронной почты!", Toast.LENGTH_LONG).show()
                }



            }
        }

    }
    fun setListener(l: ClickListenerReg) {
        listener = l
    }

    //TODO(WS2:4) Create interface ClickListener
    interface ClickListenerReg {
        fun registerNewUser(email:String,pass:String,typeUser:String,nameUser:String)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ClickListenerReg){
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
         * @return A new instance of fragment registerNewUser.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registerNewUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}