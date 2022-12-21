class Data(var archiveName: String?, // класс всех заметок
           val noteName: String?,
           var text: String?)

object DataStorage{
    val dataStorage: MutableList<Data> = mutableListOf()
}

fun createNewArchive(name: String): Data{   // создать новый архив
    Menu.mainMenu.add(name)
    return Data(name, null, null)
}
fun createNewNote(name: String?, text: String?): String?{ // создает новую заметку (с имеем из первых 10 символов
    val newName = Data(name, text?.substringBefore(' '), text)
    DataStorage.dataStorage.add(newName)
    return newName.noteName
}

fun putDataNote(archName: String?){ // показывает список названий заметок из архива

    for (el in DataStorage.dataStorage) {
        if (el.archiveName == archName && el.noteName != null )
            Menu.archiveMenu.add(el.noteName)
    }

}
fun putDataText(name: String?): String?{ // открыть заметку
    var newName: String? = ""
    for (el in DataStorage.dataStorage) {
        if (el.noteName == name)
        newName = el.text
    }
     return newName
}

fun addNoteText(text: String?,newText: String?): String?{ // добавилть новый текст заметки

    var finalText: String?= ""
    for (el in DataStorage.dataStorage) {
        if (el.text == text)
            el.text = "$text $newText"
        finalText = el.text
    }
    return finalText
}
