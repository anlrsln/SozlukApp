package com.example.sozluk.Classes

import android.annotation.SuppressLint

class KelimelerDao {


    @SuppressLint("Range")
    fun tumKelimeler(db:VeritabaniYardimcisi):ArrayList<Kelimeler>{
        val kelimeListe = ArrayList<Kelimeler>()

        val cursor = db.writableDatabase.rawQuery("SELECT * FROM kelimeler ORDER BY ingilizce",null)

        while(cursor.moveToNext()){

            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndex("kelime_id")),
                cursor.getString(cursor.getColumnIndex("ingilizce")),
                cursor.getString(cursor.getColumnIndex("turkce"))
            )

            kelimeListe.add(kelime)
        }

        return kelimeListe

    }


    @SuppressLint("Range")
    fun aramaYap(db:VeritabaniYardimcisi, arananKelime:String):ArrayList<Kelimeler>{
        val kelimeListe = ArrayList<Kelimeler>()
        val cursor = db.writableDatabase.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%$arananKelime%'",null)
        while(cursor.moveToNext()){
            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndex("kelime_id")),
                cursor.getString(cursor.getColumnIndex("ingilizce")),
                cursor.getString(cursor.getColumnIndex("turkce"))
            )
            kelimeListe.add(kelime)
        }
        return kelimeListe
    }
}




