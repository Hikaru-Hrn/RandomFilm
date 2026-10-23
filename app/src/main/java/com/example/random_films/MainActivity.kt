package com.example.random_films

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvMovieTitle: TextView
    private lateinit var btnNextMovie: Button
    private lateinit var btnReset: Button

    // Список доступных (ещё не показанных) фильмов
    private var remainingMovies = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMovieTitle = findViewById(R.id.tvMovieTitle)
        btnNextMovie = findViewById(R.id.btnNextMovie)
        btnReset = findViewById(R.id.btnReset)

        // Инициализируем и перемешиваем список при запуске
        resetMoviesList()

        btnNextMovie.setOnClickListener {
            showRandomMovie()
        }

        btnReset.setOnClickListener {
            resetMoviesList()
            tvMovieTitle.text = getString(R.string.press_button_hint)
            btnNextMovie.isEnabled = true
        }
    }

    private fun resetMoviesList() {
        // Загружаем массив из strings.xml (автоматически берется нужная локализация)
        val moviesArray = resources.getStringArray(R.array.movies_array)
        remainingMovies = moviesArray.toMutableList()
        remainingMovies.shuffle()
    }

    private fun showRandomMovie() {
        if (remainingMovies.isNotEmpty()) {
            // Берем и удаляем последний элемент из перемешанного списка
            val nextMovie = remainingMovies.removeAt(remainingMovies.lastIndex)
            tvMovieTitle.text = nextMovie
        } else {
            // Если список пуст — выводим сообщение об окончании фильмов
            tvMovieTitle.text = getString(R.string.no_more_movies)
            btnNextMovie.isEnabled = false
        }
    }
}