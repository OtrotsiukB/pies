package AcademyPies.com

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


var listViewUsers:RecyclerView?=null
var lv_forAdd:ListView?=null
class AddPiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pies)

        listViewUsers=findViewById(R.id.rv_addPieActivity)
        var wTry:Array<String> = arrayOf(
            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
            "Китти", "Масяня", "Симба"
        )

        val adapter = RcForSimpleList(wTry)
       // listViewUsers.adapter=adapter
        /*var adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, wTry
        )*/
        //listViewUsers.adapter
        lv_forAdd=findViewById(R.id.lv_addPieForUser)
        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, wTry
        )
        if(lv_forAdd!=null)
        {
            adapter2.also { lv_forAdd!!.adapter = it }
        }
///////////////recicle
      /*  listViewUsers=findViewById(R.id.rv_addPieActivity)
        var adapter2= RcForSimpleList(wTry)
        listViewUsers!!.adapter=adapter2*/
        /*if(listViewUsers!=null) {

            adapter2.also { listViewUsers!!.adapter = it }
        }*/
        ////////////
    }
}