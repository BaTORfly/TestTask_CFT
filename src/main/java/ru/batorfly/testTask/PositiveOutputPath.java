package ru.batorfly.testTask;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Класс-валидатор, проверяющий корректный ввод параметра -o
 */
public class PositiveOutputPath implements IParameterValidator{
    @Override
    public void validate(String name, String value) throws ParameterException {
        File file = new File(value);
        if (!file.exists() || !file.isDirectory()) {
            throw new ParameterException("Parameter " + name + " should be a valid directory path. '"
                    + value + "' is not valid.");
        }
    }
}
