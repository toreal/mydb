package com.example.mydb
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class UserModel( userid:String, name: String , age: String )

class MyDBHelper(ctx: Context
) : SQLiteOpenHelper(ctx, "mydb", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        p0?.execSQL("create table if not exists usermodel(id INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT, name TEXT, age TEXT )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        p0?.execSQL("drop table usermodel")
        onCreate(p0)
    }


    fun readAllUsers(): ArrayList<UserModel> {

        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from usermodel" , null)
        } catch (e: SQLiteException) {
            db.execSQL("create table if not exists usermodel(id INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT, name TEXT, age TEXT )")
            return ArrayList()
        }
        var userid: String
        var name: String
        var age: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex("userid"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                age = cursor.getString(cursor.getColumnIndex("age"))
                users.add(UserModel(userid, name, age))
                cursor.moveToNext()
            }
        }




        return users;

    }

    fun deleteUser( userid: String ) {

        val db = writableDatabase
// Define 'where' part of query.
        val selection = " userid LIKE ?"
// Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
// Issue SQL statement.
        db.delete("usermodel" , selection, selectionArgs)



    }


    fun insertUser( user: UserModel ){

        var db=getWritableDatabase()



        var sqlstr :String ="insert into usermodel(userid, name, age ) values( '123' ,'james', '20' ) "
        db.execSQL(sqlstr)

    }







}