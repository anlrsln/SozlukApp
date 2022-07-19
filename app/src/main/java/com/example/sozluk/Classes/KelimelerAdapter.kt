package com.example.sozluk.Classes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sozluk.Activities.DetailActivity
import com.example.sozluk.Activities.MainActivity
import com.example.sozluk.R
import com.example.sozluk.databinding.CardTasarimBinding

class KelimelerAdapter(private val context: Context, private val kelimelerListe:List<Kelimeler>)
    : RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>(){


    // RecyclerView'da göstereceğimiz card yapısının elemanlarına erişim için bir alt sınıf oluşturduk
    inner class CardTasarimTutucu(val binding: CardTasarimBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            val kelimeCard = binding.cardView
            val viewIngilizce = binding.textViewIngilizce
            val viewTurkce = binding.textViewTurkce
        }
    }

    // Yukarıda tanımladığımız CardTasarımTutucu class'ında card yapısının elemanlarına erişim için card_tasarim.xml dosyasına erişmemiz gerekiyor
    // Bu işlemi burada gerçekleştiririz
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardTasarimTutucu(binding)
    }


    // Elimizdeki verilere erişip, viewlara bağlarız
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kelime = kelimelerListe.get(position)
        holder.binding.textViewIngilizce.text = kelime.ingilizce
        holder.binding.textViewTurkce.text = kelime.turkce

        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("kelime",kelime)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return kelimelerListe.size
    }

}