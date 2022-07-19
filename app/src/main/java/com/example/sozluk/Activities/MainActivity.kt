package com.example.sozluk.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sozluk.Classes.Kelimeler
import com.example.sozluk.Classes.KelimelerAdapter
import com.example.sozluk.Classes.KelimelerDao
import com.example.sozluk.Classes.VeritabaniYardimcisi
import com.example.sozluk.R
import com.example.sozluk.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import java.lang.Exception

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener{
    private lateinit var binding:ActivityMainBinding
    private lateinit var vt : VeritabaniYardimcisi
    private lateinit var kelimeListe : ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Veritabanına erişim için nesne oluşturduk
        vt = VeritabaniYardimcisi(applicationContext)
        // Veritabanı telefona kopyalandı
        veritabaniKopyala()

        // Veritabanındaki verilere erişim için KelimelerDao sınıfından tumkelimeler() metodu çağırıldı ve liste içerisine aktarıldı
        kelimeListe = KelimelerDao().tumKelimeler(vt)

        // toolbar title yazıldı
        binding.toolbar.title = "Sözlük Uygulaması"
        setSupportActionBar(binding.toolbar)


        // Cardlar Listede alt alta görünmesi için LinearLayoutManager kullanıldı
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        // adapter objesi oluşturuldu ve recyclerview'a bağlandı
        adapter = KelimelerAdapter(this,kelimeListe)
        binding.recyclerView.adapter = adapter

        println(kelimeListe.size.toString())

    }


    fun veritabaniKopyala(){
        val dbx = DatabaseCopyHelper(applicationContext)
        try {
            dbx.createDataBase()
            dbx.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu?.findItem(R.id.action_ara)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        arama(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        arama(newText!!)
        return true
    }


    fun arama(arananKelime:String){
        kelimeListe = KelimelerDao().aramaYap(vt,arananKelime)
        adapter = KelimelerAdapter(this,kelimeListe)
        binding.recyclerView.adapter = adapter
    }


}