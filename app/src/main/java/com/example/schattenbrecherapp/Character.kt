package com.example.schattenbrecherapp

public data class Character(val filePath: String) {
    val characterName: String = "Quentin Solus von Farenth"
    val currentHealthPoints: Int = 80
    val currentEhremPoints: Int = 7
    val currentMentalHealthPoints: Int = 13


    // this should come from a character file in the real app
    val attributesList = mutableMapOf<String, Int>(
        "Stärke" to 9,
        "Ausdauer" to 10,
        "Charme" to 8,
        "Intelligenz" to 14,
        "Beweglichkeit" to 10,
        "Wahrnehmung" to 12,
        "Glück" to 7,
        "Mentale Belastbarkeit" to 13
    )

    // side attributes get calculated automatically based on the attributes
    val sideAttributesList = mutableMapOf<String, Int>(
        "Nahkampf" to ((7 * attributesList["Stärke"]!! + 3 * attributesList["Ausdauer"]!!) / 10),
        "Fernkampf" to ((7 * attributesList["Wahrnehmung"]!! + 3 * attributesList["Glück"]!!) / 10),
        "Rüstungswert" to ((4 * attributesList["Beweglichkeit"]!! + 4 * attributesList["Wahrnehmung"]!! + 2 * attributesList["Glück"]!!) / 10),
        "Parade" to ((5 * attributesList["Stärke"]!! + 3 * attributesList["Ausdauer"]!! + 2 * attributesList["Mentale Belastbarkeit"]!!) / 2),
        "Ausweichen" to ((6 * attributesList["Beweglichkeit"]!! + 4 * attributesList["Wahrnehmung"]!!) / 10),
        "Initiative" to ((10 * attributesList["Ausdauer"]!! + 10 * attributesList["Beweglichkeit"]!! - 5 * attributesList["Stärke"]!!) / 10),
        "Ehrem" to (6 * attributesList["Glück"]!! + 2 * attributesList["Mentale Belastbarkeit"]!! + 2 * attributesList["Wahrnehmung"]!!),
        "Geistige Gesundheit" to (10 * attributesList["Mentale Belastbarkeit"]!! + 5 * attributesList["Intelligenz"]!!),
        "Lebensenergie" to (10 * attributesList["Stärke"]!! + 5 * attributesList["Ausdauer"]!!)
    )

    // this should come from a character file in the real app
    val positiveSpecifikaList = mutableMapOf<String, String>(
        "Fotografisches Gedächtnis" to "Der Charakter kann sich an alles bildlich erinnern, was er je gesehen hat."
    )

    // this should come from a character file in the real app
    val neutralSpecifikaList = mutableMapOf<String, String>(
        "Nachteule" to "Der Charakter bleibt gern lang auf."
    )

    // this should come from a character file in the real app
    val negativeSpecifikaList = mutableMapOf<String, String>(
        "Nicht-Trinker" to "Der Charakter trinkt keinen Alkohol."
    )



    val talentList = mutableMapOf<String, MutableList<Talent>>(
        "Körperliches" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Handwerkliches" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Soziales" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Wissen" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Talente" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Gaunerei" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Überleben" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        ),

        "Magisches" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf("Stärke", "Stärke", "Ausdauer"), 0),
            Talent("Schmerzen ertragen", arrayListOf("Stärke", "Stärke", "Glück"), 0)
            // Todo fill the list
        )
    )
}
