package com.example.papb_team5.data

import com.example.papb_team5.R
import com.example.papb_team5.model.Affirmation

class datasource {
    fun loadAffirmations(): List<Affirmation>{
        return listOf<Affirmation>(
            Affirmation(R.string.todoList1),
            Affirmation(R.string.todoList2),
            Affirmation(R.string.todoList3),
        )
    }
}