package ru.batorfly.testTask;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * Точка запуска программы
 */
public class Main {
    public static void main(String[] args) {
        DataFilter dataFilter = new DataFilter();
        JCommander jc = JCommander.newBuilder()
                .addObject(dataFilter)
                .build();
        try
        {
            jc.parse(args);
            ExclusiveStatsValidator.validateExclusiveStats(dataFilter.isShortStats(), dataFilter.isFullStats());
            dataFilter.run();
        } catch (ParameterException e)
        {
            System.err.println(e.getMessage());
            jc.usage();
        }
    }
}
