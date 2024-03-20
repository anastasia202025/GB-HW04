/**
— Добавьте новую команду export, которая экспортирует добавленные значения в текстовый файл в формате JSON.
Команда принимает путь к новому файлу. Например
export /Users/user/myfile.json

— Реализуйте DSL на Kotlin, который позволит конструировать JSON и преобразовывать его в строку.

— Используйте этот DSL для экспорта данных в файл.

— Выходной JSON не обязательно должен быть отформатирован, поля объектов могут идти в любом порядке. Главное, чтобы он имел корректный синтаксис. Такой вывод тоже принимается:
[{""emails"": [""ew@huh.ru""],""name"": ""Alex"",""phones"": [""34355"",""847564""]},{""emails"": [],""name"": ""Tom"",""phones"": [""84755""]}]

Записать текст в файл можно при помощи удобной функции-расширения writeText:
File(""/Users/user/file.txt"").writeText(""Text to write"")

Под капотом она использует такую конструкцию


FileOutputStream(file).use {
it.write(text.toByteArray(Charsets.UTF_8))
}
 */

fun main() {
    var userInput: String = ""

    println("-- help - помощь по программе.")
    println("-- show - показывает все телефоны и email введённого userName.")
    println("-- find - показывает всех людей, у которых записан введённый телефон или email.")
    println("-- add userName phone numberPhone - добавить пользователю номер телефона.")
    println("-- add userName email emailAddress - добавить пользователю адрес электронной почты.")
    println("-- exit - выход из программы.")
    while (userInput != "exit") {
        println("Введите команду:")
        print(">>: ")

        userInput = readlnOrNull().toString();

        try {
            when (val command = readCommand(userInput)) {
                is Command.Exit -> println("Выход из программы")
                is Command.Help -> println(command.message)
                is Command.Show -> command.execute(phoneBook)
                is Command.Find -> command.execute(phoneBook)
                is Command.Export -> command.execute(phoneBook)
                is Command.AddPhoneNumber -> command.execute(phoneBook)
                is Command.AddEmailAddress -> command.execute(phoneBook)
                else -> continue
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}