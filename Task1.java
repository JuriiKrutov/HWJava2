/*Дана json строка [{ "фамилия":"Иванов","оценка":"5","предмет":"Математика"},
{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Задача написать метод(ы), который распарсит строку и выдаст ответ вида:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.

Используйте StringBuilder для подготовки ответа

Исходная json строка это просто String !!! Для работы используйте методы String, такие как replace, split,
 substring и т.д. по необходимости

Создать метод, который запишет результат работы в файл. Обработайте исключения и запишите ошибки в лог файл.
2. *Получить исходную json строку из файла, используя FileReader или Scanner
3. *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task1 {

    public static void main(String[] args) {
        String text = "[{ \"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},{\"фамилия\":\"Петрова\"," +
                "\"оценка\":\"4\",\"предмет\":\"Информатика\"},{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

        String newText = text.replaceAll("\\p{Punct}", " ");
        String[] words = newText.split("\\s*(\\s)\\s*");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("фамилия")){
                sb.append("\nСтудент " + words[i + 1] + " ");
            } else if (words[i].equals("оценка")) {
                sb.append("получил " + words[i + 1] + " ");
            } else if (words[i].equals("предмет")) {
                sb.append("по предмету " + words[i + 1]);
            }
        }
        saveToFile(sb);

    }

    static void saveToFile(StringBuilder s) {
        Logger logger = Logger.getLogger(Task1.class.getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        String path = "task.txt";

        try (FileWriter fileWriter = new FileWriter(path, false)) {
            fileWriter.write(s.toString());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Тестовое логирование");
        }
        fileHandler.close();
    }
}