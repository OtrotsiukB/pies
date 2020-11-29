package AcademyPies.com

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/*
private const val PARAM_STR = "param1"
private const val ARG_PARAM2 = "param2"*/

/**
 * A simple [Fragment] subclass.
 * Use the [AddPieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPieFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var listener:ClickListener?=null
    private var listViewUsers:RecyclerView?=null
    var wTry:Array<String> = arrayOf(
        "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
        "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
        "Китти", "Масяня", "Симба"
    )
    var lv_forAdd:ListView?=null

    var betwinString:String=""
    var arrayUsers:Array<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
          //  betwinString = it.getString(PARAM_STR).toString()
          //  param2 = it.getString(ARG_PARAM2)
        }
       // arrayUsers=betwinString?.split(":")?.toTypedArray()
        setRetainInstance(true);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pie, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listUser = betwinString.split(":")
        listViewUsers=view.findViewById(R.id.rv_users)

        lv_forAdd=view.findViewById(R.id.lt_UsersFragment)
        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listUser
        )
        if(lv_forAdd!=null)
        {
            adapter2.also { lv_forAdd!!.adapter = it }
        }

        lv_forAdd!!.setOnItemClickListener(OnItemClickListener { parent, v, position, id -> // по позиции получаем выбранный элемент
           // val selectedItem: String = countries.get(position)
            // установка текста элемента TextView
            //selection.setText(selectedItem)
            var checkUser = listUser[position].toString()
            AlertDialog.Builder(context)
                .setTitle("Угостить $checkUser пирожком?")
                .setPositiveButton("Агась!") { _, _ ->

                    listener?.addPieUser(checkUser)
                }
                .setNegativeButton("Обойдется!") { _, _ ->
                    //Toast.makeText(context, "you called cancel", Toast.LENGTH_SHORT).show()
                }
                .show()

           // Toast.makeText( activity, "ok!!!!!!!!!!!!$position", Toast.LENGTH_LONG).show()
        })

    }
    fun setListener(l: ClickListener) {
        listener = l
    }

    //TODO(WS2:4) Create interface ClickListener
    interface ClickListener {
        fun AllUser():Array<String>
        fun addPieUser(NameUser:String)

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
        private const val PARAM_ID = "fragment_id"
        private const val PARAM_COLOR = "fragment_color"

        fun newInstance(
            id: Int,
            color: Int
        ): AddPieFragment {
            val fragment = AddPieFragment()
            val args = Bundle()
            args.putInt(PARAM_ID, id)
            args.putInt(PARAM_COLOR, color)
            fragment.arguments = args
            return fragment
        }
    }
   /* companion object {

        private const val PARAM_STR = "fragment_id"
        fun newInstance(param1: String):AddPieFragment{
            val fragment = AddPieFragment()
            val args = Bundle()
            args.putString(PARAM_STR, param1)
            fragment.arguments = args
            return fragment
            /*
            AddPieFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_STR, param1)
                    // putString(ARG_PARAM2, param2)
                }
            }*/
            }

    }*/
}