package com.zanella.blocodenotas

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar

import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.OutputStreamWriter

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        loadFile()

    }

    override fun onStart() {
        super.onStart()
        saveFile()
    }

    override fun onPause() {
        super.onPause()
        saveFile()
    }

    override fun onStop() {
        super.onStop()
        saveFile()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveFile()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveFile ( ) {
        val FILENAME = "hello_file"
        val string = editTextMainNote.text.toString()

        val fos = openFileOutput(FILENAME, Context.MODE_PRIVATE)
        fos.write(string.toByteArray())
        fos.close()
    }

    fun loadFile () {

        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput("hello_file")
        val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.appendln(text)
        }
        //Displaying data on EditText
        editTextMainNote.setText(stringBuilder.toString()).toString()
        editTextMainNote.setSelection(editTextMainNote.text.length)

    }

}
