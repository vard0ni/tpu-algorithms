import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InvertedIndexBuilder {
    public static void main(String[] args) {
        List<String> documents = new ArrayList<>();
        // список токенизированных документов
        List<List<String>> tokenizedDocuments = new ArrayList<>();
        List<List<String>> normalizedDocuments = new ArrayList<>();

        // содержит информацию о лексемах в качестве ключей и соответствующие списки документов в качестве значений
        Map<String, List<Integer>> invertedIndex;

        // Собираем документы, подлежащие индексации
        for (int i = 0; i < 10; i++) {
            String fileName = "document" + i + ".txt";
            // считываем данные из файла используя функцию readTextFile
            String content = readTextFile(fileName);
            documents.add(content);
        }
    
        for (String document : documents) {
            System.out.println(document);
        }
        System.out.println("---------------------------");

        // Разметка текста, превращая каждый документ в список токенов
        for (String document : documents) {
            List<String> tokens = new ArrayList<>();
            // Класс токенизатора строк позволяет приложению разбивать строку на токены
            StringTokenizer tokenizer = new StringTokenizer(document);

            while (tokenizer.hasMoreTokens()) { // hasMoreTokens() - Проверяет наличие дополнительных токенов из строки этого токенизатора.
                String token = tokenizer.nextToken();       // nextToken() - Возвращает следующий токен из этого токенизатора строки.
                tokens.add(token);
            }

            tokenizedDocuments.add(tokens);
        }

        for (List<String> document : tokenizedDocuments) {
            System.out.println(document);
        }
        System.out.println("---------------------------");

        // Применяем лингвистическую обработку к каждой лексеме, добавляем их в список нормализованных лексем (индексируемых терминов)
        for (List<String> tokens : tokenizedDocuments) {
            List<String> normalizedTokens = new ArrayList<>();

            for (String token : tokens) {
                String normalizedToken = preprocessToken(token);
                normalizedTokens.add(normalizedToken);
            }

            normalizedDocuments.add(normalizedTokens);
        }

        for (List<String> document : normalizedDocuments) {
            System.out.println(document);
        }
        System.out.println("---------------------------");

        invertedIndex = new HashMap<>();

        // Шаг 4: Индексируем документы, в которых встречается токен, создавая инвертированный индекс
        for (int i = 0; i < normalizedDocuments.size(); i++) {
            List<String> normalizedTokens = normalizedDocuments.get(i);
            // проходим по каждому документу
            for (String token : normalizedTokens) {
                if (invertedIndex.containsKey(token)) {
                    // если термин уже присутсвует в индексе, добавить идентификатор документа в список документов
                    invertedIndex.get(token).add(i);
                } else {
                    // если термин встречается впервые, создать новую запись с ключом в виде термина и значением в виде списка документов
                    List<Integer> documentIds = new ArrayList<>();
                    documentIds.add(i);
                    invertedIndex.put(token, documentIds);
                }
            }
        }

        // Сортировка индекса по терминам (лексемам)
        // entrySet() возвращает набор элементов инвертированного индекса в виде коллекции пар ключ-значение, где ключом является термин, а значением является список идентификаторов документов.
        List<Map.Entry<String, List<Integer>>> sortedIndex = new ArrayList<>(invertedIndex.entrySet());
        // указывает, что нужно сравнивать элементы по ключу, который является термином. Это позволяет отсортировать элементы по алфавитному порядку терминов.
        sortedIndex.sort(Comparator.comparing(Map.Entry::getKey));

        // Вывод инвертированный индекс
        printInvertedIndex(sortedIndex);

        // Запросы на поиск
        String query1 = "example";
        String query2 = "java";

        // Поиск по алгоритму "brute force"
        System.out.println("Результаты поиска с использованием алгоритма 'brute force':");
        searchBruteForce(query1, documents, tokenizedDocuments);
        searchBruteForce(query2, documents, tokenizedDocuments);

        // Поиск с использованием инвертированных индексов
        System.out.println("Результаты поиска с использованием инвертированных индексов:");
        searchWithInvertedIndex(query1, invertedIndex);
        searchWithInvertedIndex(query2, invertedIndex);
    }

    // Вспомогательная функция для лингвистической обработки лексемы
    private static String preprocessToken(String token) {
        // Применяем лингвистические правила, например, удаление пунктуации и приведение к нижнему регистру
        // Для удаления пунктуации и пробелов, использую символьный класс [\pP\s]
        return token.toLowerCase().replaceAll("(?U)[\\pP\\s]", "");
    }

    private static void printInvertedIndex(List<Map.Entry<String, List<Integer>>> sortedIndex) {
        // Вывод отсортированного инвертированного индекса
        for (Map.Entry<String, List<Integer>> entry : sortedIndex) {
            String termin = entry.getKey();
            List<Integer> documentIds = entry.getValue();
            System.out.println("Термин: " + termin);
            System.out.println("Документы: " + documentIds);
            System.out.println("---------------------------");
        }
    }

    private static void searchBruteForce(String query, List<String> documents, List<List<String>> tokenizedDocuments) {
        long startTime = System.nanoTime();

        List<Integer> docs = new ArrayList<>();

        for (int i = 0; i < documents.size(); i++) {
            List<String> tokens = tokenizedDocuments.get(i);

            if (tokens.contains(query)) {
                docs.add(i);
            }
        }

        long endTime = System.nanoTime();
        long time = (endTime - startTime);

        System.out.println("Запрос: " + query);
        System.out.println("Совпадающие документы: " + docs);
        System.out.println("Время выполнения (brute force): " + time + " наносекунд");
        System.out.println("---------------------------");
    }

    // invertedIndex для получения соответствующего списка документов для данного запроса
    private static void searchWithInvertedIndex(String query, Map<String, List<Integer>> invertedIndex) {
        long startTime = System.nanoTime();

        // getOrDefault - возвращает значение, которому сопоставлен указанный ключ, или defaultValue, если эта карта не содержит сопоставления для ключа.
        // Если запрос найден в инвертированном индексе извлекаем список документов связанный с этим запросом.
        // Если запрос отсутствует в инвертированном индексеcвозвращаем пустой список.
        List<Integer> docs = invertedIndex.getOrDefault(query, new ArrayList<>());

        long endTime = System.nanoTime();
        long time = (endTime - startTime);

        System.out.println("Запрос: " + query);
        System.out.println("Совпадающие документы: " + docs);
        System.out.println("Время выполнения (инвертированный индекс): " + time + " наносекунд");
        System.out.println("---------------------------");
    }

    // Вспомогательная функция для чтения содержимого текстового файла
    private static String readTextFile(String fileName) {
        // StringBuilder - класс, представляющий последовательность символов
        StringBuilder content = new StringBuilder();
        
        // BufferedReader читает текст из потока ввода символов, буферизуя прочитанные символы, чтобы обеспечить эффективное считывание символов
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}