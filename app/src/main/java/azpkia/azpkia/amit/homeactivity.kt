package azpkia.azpkia.amit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_homeactivity.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Document

class homeactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeactivity)
        var auth = FirebaseAuth.getInstance()

        home_signout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this,"sign out successfully",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))
        }

        email_tx.text = "User:     " + auth.currentUser?.email

        var db = FirebaseFirestore.getInstance()
        add_bt.setOnClickListener {
            var data = HashMap<String, Any>()
            data.put("id", id_tx.text.toString().toInt())
            data.put("name", name_tx.text.toString())
            data.put("body", body_tx.text.toString())

            db.collection("data" + auth.currentUser?.email).document(id_tx.text.toString())
                .set(data).addOnCompleteListener { task ->
                if (task.isSuccessful)
                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()

            }

        }
        update_bt.setOnClickListener {
            var doc =
                db.collection("data" + auth.currentUser?.email).document(id_tx.text.toString())
            doc.update("name", name_tx.text.toString(), "body", body_tx.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                        Toast.makeText(this, "updated", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()

                }
        }

        delete_bt.setOnClickListener {
            var doc =
                db.collection("data" + auth.currentUser?.email).document(id_tx.text.toString())
            doc.delete().addOnCompleteListener { task ->
                if (task.isSuccessful)
                    Toast.makeText(this, "deleted", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
        find_bt.setOnClickListener {
            db.collection("data" + auth.currentUser?.email).document(id_tx.text.toString()).get().addOnCompleteListener {
                task ->
                if (task.result!!.exists())
                {
                    Toast.makeText(this, "title and body has been recovered",Toast.LENGTH_LONG).show()
                    name_tx.setText(task.result?.getString("name"))
                    body_tx.setText(task.result?.getString("body"))
                }
             
                else {
                    Toast.makeText(this, "this id doesn't have any data",Toast.LENGTH_LONG).show()
                }
            }

        }

list_bt.setOnClickListener{
    startActivity(Intent(this, findActivity::class.java))
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
            Toast.makeText(this,"sign out successfully",Toast.LENGTH_SHORT).show()
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