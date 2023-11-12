package com.example.w6_kotlin_listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ListView
import android.widget.PopupMenu

import android.widget.TextView
import android.widget.Toast
//import androidx.appcompat.widget.PopupMenu

class MainActivity : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Khai bao danh sach item list
        var list = mutableListOf<ItemData>()
        list.add(ItemData(1,"Josefina Lehner","0123456789","Lehner@outlook.com"))
        list.add(ItemData(2,"Stuart Vandervort","0123456789","Vandervort@gmail.com"))
        list.add(ItemData(3,"Maddision Russel","0123456789","Russel@gmail.com"))
        list.add(ItemData(4,"Halie Morar","0123456789","Morar@hi.jp"))
        list.add(ItemData(5,"Fanny Frami","0123456789","Frami@gmail.com"))
        list.add(ItemData(6,"Wisozk","0123456789","Wisozk@gmail.com"))

        customAdapter = CustomAdapter(this,list)

        //khai bao bien den listEmail
        val listContact = findViewById<ListView>(R.id.listEmail)
        listContact.isClickable = true
        listContact.adapter = customAdapter
        listContact.setOnItemClickListener { parent, view, position, id ->
            val item = list[position]
            if (list.isNotEmpty() && position < list.size) {
                val intent = Intent(this, DetailActivity::class.java)
                val id = item?.id
                val name = item?.name
                val phone = item?.phoneNumber
                val email = item?.email
                val bundle = Bundle()
                bundle.putSerializable("id", id)
                bundle.putSerializable("name", name)
                bundle.putSerializable("phone", phone)
                bundle.putSerializable("email", email)
                intent.putExtras(bundle)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Invalid item position", Toast.LENGTH_SHORT).show()
            }
        }

        listContact.setOnItemLongClickListener { parent, view, position, id ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_call -> {
                        return@setOnMenuItemClickListener true
                    }
                    R.id.action_message -> {
                        return@setOnMenuItemClickListener true
                    }
                    R.id.action_email -> {
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }
            popupMenu.show()
            true
        }
    }


}