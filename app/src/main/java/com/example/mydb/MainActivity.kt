
package com.example.mydb
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var dbhelper : MyDBHelper =MyDBHelper(this);

    var userid: EditText? =null
    var name : EditText? =null
    var age  :EditText? =null
    var resultv : TextView?= null
    var listv: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            SQLiteDatabase.openDatabase(
                    "mydb",
                    null,
                    SQLiteDatabase.CREATE_IF_NECESSARY
            )
            var user = UserModel("123", "James", "20")
            dbhelper.insertUser(user)
        }catch( err:Exception)
        {
            println(err)
        }

        userid= findViewById(R.id.userid)
        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        resultv = findViewById(R.id.result)
        listv =findViewById(R.id.listview)


        var b1 = findViewById<Button>(R.id.button1)
        var b2 = findViewById<Button>(R.id.button2)
        var b3 = findViewById<Button>(R.id.button3)


        b1.setOnClickListener { this.addUser() }
        b2.setOnClickListener { this.deleteUser() }
        b3.setOnClickListener { this.showAllUsers() }



    }
    fun addUser(){
        var userid = this.userid?.text.toString()
        var name = this.name?.text.toString()
        var age = this.age?.text.toString()
        var result = dbhelper.insertUser(UserModel(userid = userid,name = name,age = age))
//clear all edittext s
        this.age?.setText("")
        this.name?.setText("")
        this.userid?.setText("")
        this.resultv?.text = "Added user : "+result
        this.listv?.text=""

    }
    fun deleteUser(){
        var userid = this.userid?.text.toString()
        val result = dbhelper.deleteUser(userid)
        this.resultv?.text = "Deleted user : "+result
        this.listv?.text=""
    }
    fun showAllUsers(){
        var users = dbhelper.readAllUsers()
// this.ll_entries.removeAllViews()
        var tv_user=""
        users.forEach {


            tv_user+="james"
 //tv_user = it.name.toString() + " - " + it.age.toString()

        }
        this.listv?.text=tv_user
        this.resultv?.text = "Fetched " + users.size + " users"
    }
}