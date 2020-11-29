package AcademyPies.com

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(),RegisterFragment.ClickListener,registerNewUser.ClickListenerReg,AddPieFragment.ClickListener,MashaMainFragment.ClickListener {

    private val registerAndSingUser = RegisterFragment().apply { setListener(this@MainActivity) }
    private val registerNewUser= AcademyPies.com.registerNewUser().apply { this@MainActivity }
    private val mashaMainFragment = MashaMainFragment().apply { this@MainActivity }
    private val userMainFragment = UserMainFragment().apply { this@MainActivity }
    private val AddPieFragment = AddPieFragment().apply { this@MainActivity }

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().apply {
                add(R.id.persistent_container,registerAndSingUser)
                commit()
            }
        }
        // Initialize Firebase Auth
        auth = Firebase.auth



    }

    override fun singFirstFragment(login: String, pass: String) {

        auth.signInWithEmailAndPassword(login, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   /* Toast.makeText(baseContext, "Authentication ok.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser*/
                    openNextFragmentAfterSing()
                   // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                   // updateUI(null)
                    // ...
                }

                // ...
            }
    }

    override fun registerFirstFragment(login: String, pass: String) {
        auth.createUserWithEmailAndPassword(login, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication ok.",
                        Toast.LENGTH_SHORT).show()

                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "1Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }
    fun openNextFragmentAfterSing(){

        val account=auth.currentUser
        //проверка тип пользователя и открытие нужного фрагмента
        val database = com.google.firebase.ktx.Firebase.database
        val checkTypeUser = database.getReference("UserType")//.child(account.uid.toString())

        checkTypeUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                for (postSnapshot in dataSnapshot.children) {
                    if(account!=null)
                    {
                    if(postSnapshot.key==account.uid.toString()){
                        var typeUser = postSnapshot.value.toString()

                        if(typeUser=="Маша"){
                            supportFragmentManager.beginTransaction().apply {

                                addToBackStack(null)
                                add(R.id.persistent_container,mashaMainFragment)
                                commit()
                            }
                        }else{
                            supportFragmentManager.beginTransaction().apply {

                                addToBackStack(null)
                                add(R.id.persistent_container, userMainFragment)
                                commit()
                            }
                        }


                    }
                    }
                }

                //  Log.d(TAG, "Value is: $value")
                //  Toast.makeText(this, "1 $value", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        ////////



    }

    override fun openRegisterFragment() {

        supportFragmentManager.beginTransaction().apply {
           // remove(supportFragmentManager.fragments.last())
            addToBackStack(null)
            add(R.id.persistent_container,registerNewUser)
            commit()
        }
    }

    var x = 1



    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
          //  startActivity(Intent(this, MainActivity::class.java))

            // Write a message to the database
            val database = com.google.firebase.ktx.Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue(""+x+"Hello, World!{}"+account.uid.toString())
            val myRef2 = database.getReference("Masha")

            myRef2.setValue("Hello, users!{}")
            x++

            myRef2.child("Hello, users!{}").setValue("2")
           // database.getReference("message").setValue(user)
            val myref3 = database.getReference("Users")
            myref3.child(account.uid.toString()).setValue("0")

            /////
            //чтение этого значения
            val rootRef = database.getReference("Users")//.child(account.uid.toString())

            rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue()
                    for (postSnapshot in dataSnapshot.children) {
                       if(postSnapshot.key==account.uid.toString()){
                           var vwq = postSnapshot.value.toString().toInt()
                           vwq++
                           myref3.child(account.uid.toString()).setValue(vwq.toString())


                       }
                    }

                    //  Log.d(TAG, "Value is: $value")
              //  Toast.makeText(this, "1 $value", Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                   // Log.w(TAG, "Failed to read value.", error.toException())
                }
            })


            ////////////////////
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                  //  Log.d(TAG, "Value is: $value")
              //  Toast.makeText(this, "1 $value", Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                   // Log.w(TAG, "Failed to read value.", error.toException())
                }
            })

            ///////////////////
        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show()
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
      //  val currentUser = auth.currentUser
      //  updateUI(currentUser)
    }

    //регестрацыя пользователей
    override fun registerNewUser(email: String, pass: String, typeUser: String, nameUser: String) {
       var isok = false
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    ///////////
                    val account=auth.currentUser
                    if (account!=null){
                        val database = com.google.firebase.ktx.Firebase.database

                        val addTypeUser = database.getReference("UserType")
                        addTypeUser.child(account.uid.toString()).setValue(typeUser)
                        if(typeUser!="Маша") {
                            val addUser = database.getReference("Users")
                            addUser.child(account.uid.toString()).setValue(nameUser)
                        }
                        if(typeUser!="Маша") {
                            val addPie = database.getReference("CountPie")
                            addPie.child(account.uid.toString()).setValue("0")

                            supportFragmentManager.fragments.clear()
                            supportFragmentManager.beginTransaction().apply {
                                // remove(supportFragmentManager.fragments.last())
                                addToBackStack(null)
                                add(R.id.persistent_container, userMainFragment)
                                commit()
                            }
                        }else{
                            supportFragmentManager.fragments.clear()
                            supportFragmentManager.beginTransaction().apply {
                                // remove(supportFragmentManager.fragments.last())
                                addToBackStack(null)
                                add(R.id.persistent_container,mashaMainFragment)
                                commit()
                            }
                        }
                        Toast.makeText(baseContext, "Данные добавлены!",
                            Toast.LENGTH_SHORT).show()
                    ////////////
                    Toast.makeText(baseContext, "Вы зарегистрированы",
                        Toast.LENGTH_SHORT).show()
                   // updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Провал!",
                        Toast.LENGTH_SHORT).show()
                   // updateUI(null)
                }

                // ...
            }
        ///регистрация закончена


            }

        //////
    }
    var UserList:MutableList<String>?=null
    override fun AllUser(): Array<String> {
        val database = com.google.firebase.ktx.Firebase.database
        val database2 = com.google.firebase.ktx.Firebase.database.reference
        val account=auth.currentUser
        val rootRef = database.getReference("Users")//.child(account.uid.toString())
      //  var x = database2.child("Users").limitToFirst(100)// reference.child("Users") //getReference.child("Users")


       var w = rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            var UserList2:String=""
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                for (postSnapshot in dataSnapshot.children) {
                    var x =postSnapshot.value.toString()
                        UserList?.add(postSnapshot.value.toString())
                   // UserList2=UserList2+","+x


                }

                //  Log.d(TAG, "Value is: $value")
                //  Toast.makeText(this, "1 $value", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
        //var v:ArrayList<String>= ListOf("Пусто")
        var UserAray:Array<String> = UserList?.toTypedArray()?: arrayOf("пустой список")
        //UserList?.clear()
        return UserAray

    }

    fun showToast(stringShow:String){
        Toast.makeText(this, stringShow, Toast.LENGTH_LONG).show()
    }
    override fun addPieUser(NameUser: String) {

        val database = com.google.firebase.ktx.Firebase.database
        val rootRef = database.getReference("Users")

        var w = rootRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (postSnapshot in dataSnapshot.children) {
                    if(NameUser==postSnapshot.value.toString()){
                        val rootRef2 = database.getReference("CountPie")
                        var id = postSnapshot.key.toString()
                        var w2 = rootRef2.addListenerForSingleValueEvent(object : ValueEventListener {

                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                for (postSnapshot in dataSnapshot.children) {
                                    if(id==postSnapshot.key.toString()){

                                        var pieNow = postSnapshot.value.toString().toInt()
                                        pieNow++
                                        val addPie = database.getReference("CountPie")
                                        addPie.child(id).setValue(pieNow.toString())
                                        showToast("Взял пирожок $NameUser")


                                    }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Failed to read value
                                // Log.w(TAG, "Failed to read value.", error.toException())
                            }

                        })
                        /////////////////
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException())
            }

        })


       // Toast.makeText(this, "in $NameUser", Toast.LENGTH_LONG).show()
    }

    override fun openAddPie() {
        val database = com.google.firebase.ktx.Firebase.database
        val database2 = com.google.firebase.ktx.Firebase.database.reference
        val account=auth.currentUser
        val rootRef = database.getReference("Users")//.child(account.uid.toString())
        //  var x = database2.child("Users").limitToFirst(100)// reference.child("Users") //getReference.child("Users")


        var w = rootRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                var stringUser:String?=null

                for (postSnapshot in dataSnapshot.children) {
                   if(stringUser==null)
                   {
                       stringUser=postSnapshot.value.toString()
                   }else{
                       stringUser=stringUser+":"+ postSnapshot.value.toString()
                   }
                   // var x =postSnapshot.value.toString()
                   // UserList?.add(postSnapshot.value.toString())
                    // UserList2=UserList2+","+x


                }
              //  var arayUser= stringUser?.split(":")?.toTypedArray()
                supportFragmentManager.beginTransaction().apply {

                    addToBackStack(null)
                    add(R.id.persistent_container, AddPieFragment.apply { betwinString= stringUser.toString()})
                    commit()
                }
                //  Log.d(TAG, "Value is: $value")
                //  Toast.makeText(this, "1 $value", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
        //var v:ArrayList<String>= ListOf("Пусто")
       // var UserAray:Array<String> = UserList?.toTypedArray()?: arrayOf("пустой список")
    }
}