package com.example.schattenbrecherapp

import android.view.WindowInsets.Side

public enum class MainAttribute {
    STÄRKE,
    AUSDAUER,
    CHARME,
    INTELLIGENZ,
    BEWEGLICHKEIT,
    WAHRNEHMUNG,
    GLÜCK,
    MENTALE_BELASTBARKEIT
}

public enum class SideAttribute {
    NAHKAMPF,
    FERNKAMPF,
    RÜSTUNGSWERT,
    PARADE,
    AUSWEICHEN,
    INITIATIVE,
    EHREM,
    GEISTIGE_GESUNDHEIT,
    LEBENSENERGIE
}

public data class Character(

    val filePath: String = "character.json",
    val profileImagePath: String = "image_quentin",
    val characterName: String = "Quentin Solus von Farenth",
    val currentHealthPoints: Int = 80,
    val currentEhremPoints: Int = 7,
    val currentMentalHealthPoints: Int = 13,

    // this should come from a character file in the real app
    val mainAttributesList: Map<MainAttribute, Int> = mutableMapOf(
        MainAttribute.STÄRKE to 9,
        MainAttribute.AUSDAUER to 10,
        MainAttribute.CHARME to 8,
        MainAttribute.INTELLIGENZ to 14,
        MainAttribute.BEWEGLICHKEIT to 10,
        MainAttribute.WAHRNEHMUNG to 12,
        MainAttribute.GLÜCK to 7,
        MainAttribute.MENTALE_BELASTBARKEIT to 13
    ),

    // this should come from a character file in the real app
    val positiveSpecifikaList: Map<String, String> = mutableMapOf<String, String>(
        "Fotografisches Gedächtnis" to "Der Charakter kann sich an alles bildlich erinnern, was er je gesehen hat."
    ),

    // this should come from a character file in the real app
    val neutralSpecifikaList: Map<String, String> = mutableMapOf<String, String>(
        "Nachteule" to "Der Charakter bleibt gern lang auf."
    ),

    // this should come from a character file in the real app
    val negativeSpecifikaList: Map<String, String> = mutableMapOf<String, String>(
        "Nicht-Trinker" to "Der Charakter trinkt keinen Alkohol."
    ),



    val talentList: Map<String, MutableList<Talent>> = mutableMapOf<String, MutableList<Talent>>(
        "Körperliches" to mutableListOf<Talent>(
            Talent("Tragen/Ziehen", arrayListOf(MainAttribute.STÄRKE, MainAttribute.STÄRKE, MainAttribute.AUSDAUER), 0),
            Talent("Schmerzen ertragen", arrayListOf(MainAttribute.STÄRKE, MainAttribute.STÄRKE, MainAttribute.GLÜCK), 0)
            // Todo fill the list
        ),

        "Handwerkliches" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Soziales" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Wissen" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Talente" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Gaunerei" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Überleben" to mutableListOf<Talent>(
            // Todo fill the list
        ),

        "Magisches" to mutableListOf<Talent>(
            // Todo fill the list
        )
    )
    ){
    // END OF CONSTRUCTOR
    //START OF ACTUAL CLASS

    // side attributes get calculated automatically based on the attributes
    fun getSideAttributesList(): Map<SideAttribute, Int> {
        return mapOf(
        SideAttribute.NAHKAMPF to ((7 * mainAttributesList[MainAttribute.STÄRKE]!! + 3 * mainAttributesList[MainAttribute.AUSDAUER]!!) / 10),
        SideAttribute.FERNKAMPF to ((7 * mainAttributesList[MainAttribute.WAHRNEHMUNG]!! + 3 * mainAttributesList[MainAttribute.GLÜCK]!!) / 10),
        SideAttribute.RÜSTUNGSWERT to ((4 * mainAttributesList[MainAttribute.BEWEGLICHKEIT]!! + 4 * mainAttributesList[MainAttribute.WAHRNEHMUNG]!! + 2 * mainAttributesList[MainAttribute.GLÜCK]!!) / 10),
        SideAttribute.PARADE to ((5 * mainAttributesList[MainAttribute.STÄRKE]!! + 3 * mainAttributesList[MainAttribute.AUSDAUER]!! + 2 * mainAttributesList[MainAttribute.MENTALE_BELASTBARKEIT]!!) / 2),
        SideAttribute.AUSWEICHEN to ((6 * mainAttributesList[MainAttribute.BEWEGLICHKEIT]!! + 4 * mainAttributesList[MainAttribute.WAHRNEHMUNG]!!) / 10),
        SideAttribute.INITIATIVE to ((10 * mainAttributesList[MainAttribute.AUSDAUER]!! + 10 * mainAttributesList[MainAttribute.BEWEGLICHKEIT]!! - 5 * mainAttributesList[MainAttribute.STÄRKE]!!) / 10),
        SideAttribute.EHREM to (6 * mainAttributesList[MainAttribute.GLÜCK]!! + 2 * mainAttributesList[MainAttribute.MENTALE_BELASTBARKEIT]!! + 2 * mainAttributesList[MainAttribute.WAHRNEHMUNG]!!),
        SideAttribute.GEISTIGE_GESUNDHEIT to (10 * mainAttributesList[MainAttribute.MENTALE_BELASTBARKEIT]!! + 5 * mainAttributesList[MainAttribute.INTELLIGENZ]!!),
        SideAttribute.LEBENSENERGIE to (10 * mainAttributesList[MainAttribute.STÄRKE]!! + 5 * mainAttributesList[MainAttribute.AUSDAUER]!!)
        )
    }


}
