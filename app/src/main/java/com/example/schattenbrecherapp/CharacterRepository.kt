package com.example.schattenbrecherapp

object CharacterRepository {

    //implement loading of a character from file
    suspend fun loadCharacterFromFile(filePath: String): Character{
        val character = Character(filePath)
        //implement loading of character data from file -> replace prefilled data by actual loaded data
        return character

    }
}