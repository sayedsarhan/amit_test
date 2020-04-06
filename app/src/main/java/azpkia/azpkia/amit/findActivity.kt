package azpkia.azpkia.amit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_find.*
import kotlinx.android.synthetic.main.activity_find.view.*
import kotlinx.android.synthetic.main.activity_homeactivity.*

class findActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        bc_bt.setOnClickListener{
            startActivity(Intent(this,homeactivity::class.java))        }
        var db = FirebaseFirestore.getInstance()
        var auth = FirebaseAuth.getInstance()
      db.collection("data"+auth.currentUser?.email).get().addOnCompleteListener { task ->
          var list=ArrayList<String>()
              for (doc in task.result!!) {
                  list.add( "  id:  "+doc.getLong("id")+"\n  name:  "+doc.getString("name")+"\n  body:  "+doc.getString("body"))


              var adb=ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,list)
                  listview.adapter=adb

          }
      }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.list_menu)
        {
            startActivity(Intent(this,findActivity::class.java))

        }
        if (item.itemId==R.id.signout_menu){
            var auth = FirebaseAuth.getInstance()

            auth.signOut()
            Toast.makeText(this,"You have been signed out successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))

        }
        if (item.itemId==R.id.me_menu)
        {
            startActivity(Intent(this,sayedActivity::class.java))
        }
        if (item.itemId==R.id.add_menu){
            startActivity(Intent(this,homeactivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }
}


