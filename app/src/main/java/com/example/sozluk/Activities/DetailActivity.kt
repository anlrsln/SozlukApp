package com.example.sozluk.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozluk.Classes.Kelimeler
import com.example.sozluk.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kelime = intent.getSerializableExtra("kelime") as Kelimeler

        binding.detayIngilizce.text = kelime.ingilizce
        binding.detayTurkce.text = kelime.turkce

    }
}