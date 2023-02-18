object Menu {
    var mainMenu: MutableList<String?> = mutableListOf("Выход", "Создать архив")
    var archiveMenu: MutableList<String?> = mutableListOf()
    var noteMenu: MutableList<String?> = mutableListOf()

    fun showMenu(menu: MutableList<String?>): String { // функция показывает меню ввиде индекса и значения напротив
        var pointToString = ""
        for ((number, el) in menu.withIndex()) {
            pointToString = "$pointToString\n $el - $number"
        }
        return pointToString
    }
fun mainMenuInfo(){
    println("Введите номер пункта меню: ")
    println(showMenu(mainMenu))
}
}




