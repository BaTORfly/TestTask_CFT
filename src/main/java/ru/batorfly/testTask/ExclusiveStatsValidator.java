package ru.batorfly.testTask;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Класс-валидатор, проверяющий отсутствие взаимоисключающих параметров -s и -f
 */
public class ExclusiveStatsValidator implements IParameterValidator {
    @Override
    public void validate(String s, String s1) throws ParameterException {

    }
    public static void validateExclusiveStats(boolean shortStats, boolean fullStats) throws ParameterException
    {
        if (shortStats && fullStats) {
            throw new ParameterException("The -s and -f parameters cannot be specified at the same time.");
        }
    }
}
