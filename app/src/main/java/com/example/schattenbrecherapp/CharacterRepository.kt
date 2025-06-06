package com.example.schattenbrecherapp

//import com.google.gson.Gson // Example, replace with your choice
//import com.google.gson.reflect.TypeToken

object CharacterRepository {

    //private val gson = Gson() // Example for JSON parsing

    //implement loading of a character from file
    suspend fun loadCharacterFromFile(filePath: String): Character{
        val character = Character(filePath)
        //implement loading of character data from file -> replace prefilled data by actual loaded data
        return character

        /*
        // This is where the actual file I/O happens
        try {
            val file = File(filePath)
            if (!file.exists()) {
                throw FileNotFoundException("Character file not found at: $filePath")
            }
            val jsonString = file.readText()

            // Parse the raw string (e.g., JSON into a Map or a temporary data structure)
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val parsedData: Map<String, Any> = gson.fromJson(jsonString, type)

            // Now use the Character's companion object factory method (or similar logic)
            // to construct the actual Character object from the parsed data.
            return Character.fromParsedData(parsedData, filePath)

        } catch (e: FileNotFoundException) {
            // Log or handle specific file not found
            throw e // Re-throw or handle appropriately
        } catch (e: Exception) {
            // Handle other I/O or parsing errors
            throw IllegalStateException("Failed to load or parse character from: $filePath", e)
        }
         */
    }

    //implement saving of a character to file
    suspend fun saveCharacterToFile(character: Character){
        //implement saving to file
        val filePath = character.filePath

        /*
        try {
            val jsonString = gson.toJson(character) // Convert Character object to JSON
            File(character.filePath).writeText(jsonString)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to save character to: ${character.filePath}", e)
        }
         */
    }

}