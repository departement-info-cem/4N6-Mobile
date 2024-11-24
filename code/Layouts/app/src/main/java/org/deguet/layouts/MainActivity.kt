package org.deguet.layouts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.deguet.layouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // utilisation du binding pour l'affichage
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.root)

        // Toolbar initialization
        setSupportActionBar(binding.toolbar)

        // le bouton flottant ba afficher un SnackBar
        binding.fab.setOnClickListener(View.OnClickListener { view: View ->
            Snackbar.make(view, "yo", Snackbar.LENGTH_LONG).show()
        })
    }


    //////////   partie menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = getMenuInflater()
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_settings -> {
                Toast.makeText(this, "yo", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_add_fav -> {
                Toast.makeText(this, "mama", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}