package com.example.restauranteapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.restauranteapp.databinding.ActivityMainBinding
import com.example.restauranteapp.modelo.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    private val pastel = ItemMenu("Pastel de Choclo", 12000)
    private val cazuela = ItemMenu("Cazuela", 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventos()
    }

    private fun eventos() {

        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calcular()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.edtPastel.addTextChangedListener(watcher)
        binding.edtCazuela.addTextChangedListener(watcher)

        binding.switchPropina.setOnCheckedChangeListener { _, _ ->
            calcular()
        }
    }

    private fun calcular() {

        val cantPastel = binding.edtPastel.text.toString().toIntOrNull() ?: 0
        val cantCazuela = binding.edtCazuela.text.toString().toIntOrNull() ?: 0

        val subtotalPastel = cantPastel * pastel.precio
        val subtotalCazuela = cantCazuela * cazuela.precio

        binding.txtSubtotalPastel.text = formato.format(subtotalPastel)
        binding.txtSubtotalCazuela.text = formato.format(subtotalCazuela)

        val total = subtotalPastel + subtotalCazuela
        val propina = if (binding.switchPropina.isChecked) (total * 0.1).toInt() else 0
        val totalFinal = total + propina

        binding.txtTotal.text = "Comida: ${formato.format(total)}"
        binding.txtPropina.text = "Propina: ${formato.format(propina)}"
        binding.txtFinal.text = "TOTAL: ${formato.format(totalFinal)}"
    }
}