package ru.malevichrp.studentsOnLectures

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.malevichrp.studentsOnLectures.core.presentation.Navigate
import ru.malevichrp.studentsOnLectures.core.presentation.Screen

class MainActivity : AppCompatActivity(), Navigate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            navigateToChooseProfile()
        }
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }
}