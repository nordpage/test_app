package xyz.mobcoder.testapp.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import xyz.mobcoder.testapp.sqlite.models.ListItem

class DBOpenHelper(context: Context,
                   factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

        companion object {
            private val DATABASE_VERSION = 1
            private val DATABASE_NAME = "planner.db"
            val TABLE_NAME = "plans"
            val COLUMN_ID = "_id"
            val COLUMN_TITLE = "title"
        }

    init {
        addItem(ListItem("be357acd257797c9da6c91b1b67db028"))
        addItem(ListItem("dcb2140952685837c31f6d84b7b4caa4"))
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE
                + " TEXT UNIQUE" + ")")
        db?.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addItem(listItem: ListItem) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, listItem.title)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllItems(): MutableList<ListItem> {
        val items = mutableListOf<ListItem>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                val listItem = ListItem(title)
                items.add(listItem)
            }while (cursor.moveToNext())
        }
        return items
    }

}