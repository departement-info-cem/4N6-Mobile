package org.deguet.erreurs

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.deguet.erreurs.databinding.ActivityMainBinding

// TODO snackbar with retry button
// TODO is action to retry is obvious > no need
// TODO ajouter un dialog pour
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Messages d'erreur localisés sur un champ
        // validation d'un champ, explication d'un format etc.
        binding!!.envoyer1.setOnClickListener { view: View? -> showMessageSurChamp() }
        binding!!.envoyer2.setOnClickListener { view: View? -> showASnackBar() }

        // Messages d'erreur qui ne correspondent pas à un endroit particulier
        // erreur d'accès réseau ou serveur, pas les autorisations etc.
        binding!!.envoyer3.setOnClickListener { view: View? -> showADialog() }
        binding!!.fab.setOnClickListener { view: View? -> showASnack2() }
    }

    private fun showASnack2() {
        val snacky = Snackbar.make(
            binding!!.coordinator,
            R.string.no_network, Snackbar.LENGTH_LONG
        )
        snacky.setAction(
            R.string.retry
        ) { view1: View? ->
            Toast.makeText(this, "Retrying", Toast.LENGTH_SHORT).show()
        }
        snacky.show()
    }

    private fun showADialog() {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle(R.string.no_network)
        builder.setPositiveButton(
            R.string.ok
        ) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton(R.string.retry) { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, "Retrying", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun showASnackBar() {
        // On peut combiner le fait de marquer un message d'erreur avec le surlignage
        // des erreurs dans le formulaire
        binding!!.textInputLayout.error = getString(R.string.error_example)

        // On peut mettre une action qui nous amène à l'erreur
        val snacky = Snackbar.make(
            binding!!.coordinator,
            R.string.snack_message, Snackbar.LENGTH_LONG
        )
        snacky.setAction(R.string.go_there) { view1: View? ->
            binding!!.textInputLayout.requestFocus()
        }
        snacky.show()
    }

    private fun showMessageSurChamp() {
        // si le message d'erreur sur un champ texte est en dehors de la zone
        // visible, il faut demander le focus pour obliger le scroll à y aller
        binding!!.password.error = getString(R.string.error_password)
        binding!!.password.requestFocus()
    }
}