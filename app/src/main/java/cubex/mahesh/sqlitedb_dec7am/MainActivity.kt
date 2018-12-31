package cubex.mahesh.sqlitedb_dec7am

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dBase : SQLiteDatabase = openOrCreateDatabase(
            "and7amdec", Context.MODE_PRIVATE,
            null)
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement,eid integer,ename varchar(50),edesig varchar(50),edept varchar(50))")

        insert.setOnClickListener {

            // long insert(String table, String nullColumnHack, ContentValues values)

            var cv:ContentValues = ContentValues()
            cv.put("eid",et1.text.toString().toInt())
            cv.put("ename",et2.text.toString())
            cv.put("edesig",et3.text.toString())
            cv.put("edept",et4.text.toString())

          var status:Long =   dBase.insert("employee",
                null,cv)

            if(status!=-1L){
                Toast.makeText(this@MainActivity,
                    "Data is Inserted..",
                    Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("")
                et3.setText(""); et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,
                    "Data is Insertion Failed..",
                    Toast.LENGTH_LONG).show()
            }

        //    dBase.execSQL("insert into employee values(${et1.text.toString().toInt()},'${et2.text.toString()}','${et3.text.toString()}','${et4.text.toString()}')")

        }  // Insert


        read.setOnClickListener {

            /* Cursor query(String table, String[] columns,
            String selection, String[] selectionArgs,
            String groupBy, String having, String orderBy)*/
    /*     var c:Cursor =    dBase.query("employee",
             arrayOf("eid","ename","edesig","edept"),
             null, null,null,
             null,null) */
    /*        var c:Cursor =    dBase.query("employee",
                null,
                "eid=? and edept=?",
                arrayOf(et1.text.toString(),et4.text.toString()),
                null,
                null,null) */

       /*     var c:Cursor =    dBase.query("employee",
                null,
                null,
                null,
                "ename",
                null,null) */

        /*    var c:Cursor =    dBase.query("employee",
                null,
                null,
                null,
                "eid",
                "eid>125",null) */

          var c:Cursor =    dBase.query("employee",
                null,
                null,
                null,
                null,
                null,"eid desc")
// Context context, int layout, Cursor c, String[] from, int[] to, int flags
 var from:Array<String> = arrayOf("eid","ename","edesig","edept")
 var to:IntArray = intArrayOf(R.id.eid,R.id.ename,R.id.edesig,R.id.edept)
          var myadapter = SimpleCursorAdapter(
              this@MainActivity,
              R.layout.indiview,c,from,to,0)
  lview.adapter  = myadapter

        } // Read


        update.setOnClickListener {
 /* int update(String table, ContentValues values,
      String whereClause, String[] whereArgs) */
            var cv = ContentValues( )
            cv.put("ename",et2.text.toString())
            cv.put("edesig",et3.text.toString())
      var status =       dBase.update("employee",cv,
                "eid=?",
                arrayOf(et1.text.toString()))

            if(status!=0){
                Toast.makeText(this@MainActivity,
                    "Data is Updated..",
                    Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("")
                et3.setText(""); et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,
                    "Data Updation  Failed..",
                    Toast.LENGTH_LONG).show()
            }
        } // Update


        delete.setOnClickListener {
 // int delete(String table, String whereClause, String[] whereArgs)
  var status =   dBase.delete("employee",
                            "eid=?",
                        arrayOf(et1.text.toString()))
            if(status!=0){
                Toast.makeText(this@MainActivity,
                    "Data is Deleted..",
                    Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("")
                et3.setText(""); et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,
                    "Data Deletion  Failed..",
                    Toast.LENGTH_LONG).show()
            }
        } //delete


    } // onCreate( )
}
