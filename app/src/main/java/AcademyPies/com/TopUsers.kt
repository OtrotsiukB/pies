package AcademyPies.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Map

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopUsers.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopUsers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var mapCountPieOnTopUsers:String?=null
    var mapUserOnTopUsers:String?= null
    var tv_top:TextView?=null
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
        return inflater.inflate(R.layout.fragment_top_users, container, false)
    }

    data class Top (var Id:String,var Count:Int)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_top = view.findViewById<TextView>(R.id.tv_onTopUser)
        //mapCountPieOnTopUsers?.toSortedMap()
        var pieCount = mapCountPieOnTopUsers?.let { fromStringToMap(it)?.toList() }
        var UserNamed= mapUserOnTopUsers?.let { fromStringToMapString(it) }
        var Count =pieCount?.toList()

        var top = Count?.sortedBy { it.second  }

       //var stroka:String=top.toString()
        var topSize: Int? = top?.count()// ?.size?.toInt() ?: -1
        var topString:String=""

        var x:Boolean=true
        var nomerTop:Int=1
        while(x){
            if (topSize == 0) {
                x=false

            }else{
                var minusOneSize = topSize?.minus(1)
                var nameFromtop: String? = minusOneSize?.let { top?.get(index = it)?.first }
                    var name = UserNamed?.get(nameFromtop).toString()
                topString=topString+"№$nomerTop "+name+" Пирожков: "+ topSize?.let { top?.get(it-1)?.second.toString()+"\n" }
            }
            nomerTop++
            if (topSize != null) {
                topSize = topSize - 1
            }
        }
        ///////////
        tv_top?.text=topString
        //////////




    }

    fun fromStringToMap(stroka:String):MutableMap<String,Int>?{
        var needMutableMap:MutableMap<String,Int>?=mutableMapOf()
        var x = stroka.split(",")
        //var ddd:MutableList<Int,String>
        for (element in x){
            var subX = element.split(":")

             needMutableMap?.put(subX[0].toString(),subX[1].toString().toInt())
        }
        return needMutableMap
    }
    fun fromStringToMapString(stroka:String):MutableMap<String,String>?{
        var needMutableMap:MutableMap<String,String>?=mutableMapOf()
        var x = stroka.split(",")
        for (element in x){
            var subX = element.split(":")

            needMutableMap?.put(subX[0].toString(),subX[1].toString())
        }
        return needMutableMap
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TopUsers.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopUsers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}