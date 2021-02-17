package com.bd.sqlitecrud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DBhandler(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Pass_DB"
        private const val TABLE_NAME = "Pass_Info"

        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASS = "pass"
        private const val KEY_ACCOUNT = "account"
        private const val KEY_PIN = "pin"
        private const val KEY_URL = "url"
    }


    fun insertPasswordInfo(info: Model): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, info.name)
        contentValues.put(KEY_EMAIL, info.email)
        contentValues.put(KEY_PASS, info.password)
        contentValues.put(KEY_ACCOUNT, info.account)
        contentValues.put(KEY_PIN, info.pin)
        contentValues.put(KEY_URL, info.url)

        // Inserting row
        db.insert(TABLE_NAME, null, contentValues);
        db.close()

        return true;
    }

    fun fetchAllData(): MutableList<Model> {

        val passInfo = mutableListOf<Model>()
        val selectQuery = "SELECT *FROM $TABLE_NAME"

        val db: SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return mutableListOf()

        }

        cursor.moveToFirst()

        var id: Int
        var name: String
        var email: String
        var pass: String
        var account: String
        var pin: String
        var url: String

        while (cursor.isAfterLast() == false) {

            id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
            email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
            pass = cursor.getString(cursor.getColumnIndex(KEY_PASS))
            account = cursor.getString(cursor.getColumnIndex(KEY_ACCOUNT))
            pin = cursor.getString(cursor.getColumnIndex(KEY_PIN))
            url = cursor.getString(cursor.getColumnIndex(KEY_URL))

            var passValue = Model(id, name, email, pass, account, pin, url)

            passInfo.add(passValue);
            cursor.moveToNext();
        }

        db.close()
        return passInfo
    }

    fun updateData(info: Model): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, info.name)
        contentValues.put(KEY_EMAIL, info.email)
        contentValues.put(KEY_PASS, info.password)
        contentValues.put(KEY_ACCOUNT, info.account)
        contentValues.put(KEY_PIN, info.pin)
        contentValues.put(KEY_URL, info.url)
        db.update(TABLE_NAME, contentValues, "$KEY_ID = ${info.id}", null);
        db.close()
        return true;

    }

    fun deleteData(id:Int?): Int {

        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, id)
        val success = db.delete(TABLE_NAME, "$KEY_ID=${id}", null)
        db.close()

        return success
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Creating table with fields
        val CREATE_SITE_TABLE =
            ("CREATE TABLE IF NOT EXISTS $TABLE_NAME($KEY_ID INTEGER PRIMARY KEY,$KEY_NAME VARCHAR,$KEY_EMAIL VARCHAR, " +
                    "$KEY_PASS VARCHAR, $KEY_ACCOUNT VARCHAR, $KEY_PIN VARCHAR, $KEY_URL VARCHAR)")

        db?.execSQL(CREATE_SITE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("Not yet implemented")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(db);
    }



}