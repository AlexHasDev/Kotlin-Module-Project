object MenuOperator {
    var exitFlag: Int = 1 // переменная меню, в котором пользователь в данный момент находится
    var userPoint: Int = 0 // переменная пользовательского ввода


    fun input(menu: MutableList<String?>) { // эта функция позволяет обработать ввод, проверить его на тип данных. Так же эта функция определяет значение exitFlag
        var answer = 8
        var input: Any?
        while (answer == 8 && exitFlag != 0) {

            input = readLine().toString().trim().replace("\\s".toRegex(), "")  // ввод чистится от пробелов
            input = if (input.toString().length > 5) input.toString().substring(0, 4) else input // ввод обрезается до 4х символов

            if (input.toString().isEmpty() || !input.toString().toCharArray()[0].isDigit()) { // проверка на пустое значение и на цифру
                println("Введите цифру: ")
                answer = 8
                continue
            }
            input.toString().toCharArray().toMutableList().forEach{ el -> if( el.isLetter() ){
                println("Введите цифру: "); return}} // проверка на наличие букв среди цифр

            if (input.toString().toInt() > menu.size - 1) { // проверка на наличие такого пункта

                println("Такого пункта нет.\nВыберите действие: ")
                answer = 8
            } else {
                answer = input.toString().toInt()
                if (answer > 0 && exitFlag < 3 && answer != 1) { // проверка на актуальное меню
                    exitFlag++
                } else if (answer == 0) {
                    exitFlag--

                } else if (exitFlag == 3 || exitFlag > 3) {
                    exitFlag = 3
                }
                userPoint = answer
            }
        }
    }
}

fun uniqName(menu: List<String?>, name: String): String{ // функция проверяет значение на уникальность
   menu.forEach{el -> if(el == name ){ println("Такой элемен уже присутстствует.\nПовторите ввод:"); return uniqName(menu,readLine().toString()) }
   }
    return name
}

fun start() { // функция старта программы
    var archiveName: String? // переменная для нового архива
    println("Добро пожаловать в заметки!")
    while (MenuOperator.exitFlag > 0) {
        Menu.mainMenuInfo()
        MenuOperator.input(Menu.mainMenu)
        while (MenuOperator.userPoint != 0) {
            if (MenuOperator.userPoint == 1) {
                println("Введите название архива: ")
                val nameArchive: String = uniqName(Menu.mainMenu,readLine().toString())

                DataStorage.dataStorage.add(createNewArchive(nameArchive))
                println("Архив заметок \"$nameArchive\" создан")
                Menu.mainMenuInfo()
                MenuOperator.input(Menu.mainMenu)
            }

            if (MenuOperator.userPoint > 1) {

                println("Архив \"${Menu.mainMenu[MenuOperator.userPoint]}\"\nВыберите действие: ")
                archiveName = Menu.mainMenu[MenuOperator.userPoint]
                Menu.archiveMenu.clear()
                Menu.archiveMenu.add("К списку архивов")
                Menu.archiveMenu.add("Создать заметку")
                putDataNote(archiveName)

                while (MenuOperator.userPoint != 0 || MenuOperator.exitFlag == 2) {

                    println(Menu.showMenu(Menu.archiveMenu))
                    MenuOperator.input(Menu.archiveMenu)

                    if (MenuOperator.userPoint == 1) {
                        println("Введите текст заметки: ")
                        Menu.archiveMenu.add(createNewNote(archiveName, uniqName(Menu.archiveMenu, readLine()!!)))
                    }
                    if (MenuOperator.userPoint > 1) {
                        var actualNote = putDataText(Menu.archiveMenu[MenuOperator.userPoint])
                        while (MenuOperator.userPoint != 0) {

                            Menu.noteMenu.clear()
                            Menu.noteMenu.add("К заметкам")
                            Menu.noteMenu.add("Добавить текст")

                            println("\n \"${actualNote}\" ")
                            println(Menu.showMenu(Menu.noteMenu))
                            MenuOperator.input(Menu.archiveMenu)
                            if (MenuOperator.userPoint == 1) {
                                println("Введите новый текст: ")
                                actualNote = addNoteText(actualNote, readLine())
                                println("\n \"${putDataText(Menu.archiveMenu[MenuOperator.userPoint])}\" ")
                            }
                            println(Menu.showMenu(Menu.noteMenu))
                        }
                    }
                }
            }
        }
    }
}
