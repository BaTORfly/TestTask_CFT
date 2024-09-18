package ru.batorfly.testTask;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Класс-валидатор, проверяющий корректный ввод файлов
 */
public class PositiveInputFile implements IParameterValidator{
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.toLowerCase().endsWith(".txt")) {
            throw new ParameterException("Parameter " + name + " " +
                    "should contain files with '.txt' extension. '" + value + "' is not valid.");
        }
    }
}
