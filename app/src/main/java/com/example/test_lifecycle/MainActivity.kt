package com.example.test_lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity(var count: Int = 0) : AppCompatActivity() {

    lateinit var txt : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        txt = findViewById<TextView>(R.id.textView)

        findViewById<Button>(R.id.button).setOnClickListener {
            count++;
            txt.text = "Counter: $count"
            Log.i("TAG-D","onClick event triggered")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("TAG-C","onCreate event triggered")
    }


    override fun onRestoreInstanceState(state: Bundle) {

        // This callback is called only when there is a saved instance previously saved using
        // onSaveInstanceState(). Some state is restored in onCreate(). Other state can optionally
        // be restored here, possibly usable after onStart() has completed.
        // The savedInstanceState Bundle is same as the one used in onCreate().

        count = state.getInt("My Counter")
        txt.text = "Counter: $count"
        Log.d("TAG-E","onRestoreInstanceState event triggered")
    }

    // Invoked when the activity might be temporarily destroyed; save the instance state here.
    override fun onSaveInstanceState(state: Bundle) {
        state.run {
            putInt("My Counter",count)  // try to increment the counter and then rotate screen when this line is commented
        }
        Log.d("TAG-E","onSaveInstanceState event triggered")
        // Call superclass to save any view hierarchy.
        super.onSaveInstanceState(state)
    }

    override fun onStart() {
        super.onStart()
        Log.i("TAG-A","onStart event triggered")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAG-A","onStop event triggered")
    }

    override fun onPause() {
        super.onPause()
        Log.v("TAG-B","onPause event triggered")
    }

    override fun onResume() {
        super.onResume()
        Log.v("TAG-C","onResume event triggered")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG-C","onDestroy event triggered")
    }
}