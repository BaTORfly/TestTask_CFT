package ru.batorfly.testTask;


import com.beust.jcommander.Parameter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий в себе всю основную логику программы (обработка входящих параметров, фильтрация файлов).
 */
public class DataFilter
{
    @Parameter(description = "Input files", validateWith = PositiveInputFile.class, required = true)
    private List<String> inputFiles = new ArrayList<>();
    @Parameter(names = "-o", description = "Output path", validateWith = PositiveOutputPath.class)
    private String outputPath = ".";
    @Parameter(names = "-p", description = "Output file prefix", required = true)
    private String prefix;
    @Parameter(names = "-a", description = "Append mode")
    private boolean appendMode = false;
    @Parameter(names = "-s", description = "Short statistics")
    private boolean shortStats = false;
    @Parameter(names = "-f", description = "Full statistics")
    private boolean fullStats = false;

    StatisticsCollector statisticsCollector = new StatisticsCollector();
    /**
     * Геттер короткой статистки
     * @return shortStats
     */
    public boolean isShortStats() {
        return shortStats;
    }

    /**
     * Геттер полной статистики
     * @return fullStats
     */
    public boolean isFullStats() {
        return fullStats;
    }

    public void run()
    {
        try {
            process();
            statisticsCollector.printStatistics(shortStats, fullStats);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Основной метод для обработки файлов
     */
    private void process() throws IOException
    {
        FileWriter intWriter = null, floatWriter = null, stringWriter = null;

        for (String inputFile : inputFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while((line = br.readLine()) != null) {
                    // если строка является целым числом
                    if (isInteger(line)) {
                        // если целое число еще не встречалось/поток еще не был открыт
                        if (intWriter == null) {
                            intWriter = getFileWriter(outputPath, "integers.txt");
                        }
                        Long intValue = Long.parseLong(line);
                        intWriter.write(intValue + "\n");
                        statisticsCollector.updateIntStats(intValue);
                    } else if (isFloat(line)) {
                        if (floatWriter == null) {
                            floatWriter = getFileWriter(outputPath, "floats.txt");
                        }
                        double floatValue = Double.parseDouble(line);
                        floatWriter.write(floatValue + "\n");
                        statisticsCollector.updateFloatStats(floatValue);

                    } else {
                        if (stringWriter == null) {
                            stringWriter = getFileWriter(outputPath, "strings.txt");
                        }
                        stringWriter.write(line + "\n");
                        statisticsCollector.updateStringStats(line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to process file: " + inputFile + ". Skipping.");
            }
        }
        // Закрытие поток, если они были открыты
        if (intWriter != null) intWriter.close();
        if (floatWriter != null) floatWriter.close();
        if (stringWriter != null) stringWriter.close();
    }
    /**
     * Получение FileWriter с учетом перезаписи или добавления
     * @param outputPath директория выходных файлов
     * @param filename имя файла
     * @return
     * @throws IOException обработка исключения
     */
    private FileWriter getFileWriter (String outputPath, String filename) throws IOException
    {
        String fullPath = Paths.get(outputPath, prefix + filename).toString();
        return new FileWriter(fullPath, appendMode);
    }
    /**
     * Проверка строки на целое число
     * @param line строка
     * @return true, если line - целое число, false в обратном случае
     */
    private boolean isInteger(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Проверка строки на вещественное число
     * @param line строка
     * @return
     */
    private boolean isFloat(String line) {
        try {
            Double.parseDouble(line);
            return line.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
