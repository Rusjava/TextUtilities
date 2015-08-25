/*
 * Copyright (C) 2015 Ruslan Feshchenko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package TextUtilities;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JTextField;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Class for some text processing utilities
 *
 * @author Ruslan feshchenko
 * @version 1.0
 */
public class MyTextUtilities {

    /**
     * Checks that the entered value is within bounds provided and that the
     * entered value is actually a double number. In case of error default value
     * is substituted instead.
     *
     * @param min minimal value
     * @param max maximal value
     * @param field text field
     * @param str default value
     * @return either entered value or default value in case of an error
     */
    public static Double TestValue(double min, double max, JTextField field, String str) {
        Double value;
        String newStr = field.getText();
        if (newStr.length() == 0) {
            return 0.0;
        }
        try {
            value = Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(str);
            value = Double.valueOf(str);
            return value;
        }
        if (value < min || value > max) {
            field.setText(str);
            value = Double.valueOf(str);
            return value;
        }
        return value;
    }

    /**
     * Checks that the entered value is within bounds provided and that the
     * entered value is actually a double number. In case of error default value
     * is substituted instead.
     *
     * @param min minimal value
     * @param max maximal value
     * @param field text field
     * @param str default value
     * @return either entered value or default value in case of an error
     */
    public static Double TestValue(double min, double max, TextField field, String str) {
        Double value;
        String newStr = field.getText();
        if (newStr.length() == 0) {
            return 0.0;
        }
        try {
            value = Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(str);
            value = Double.valueOf(str);
            return value;
        }
        if (value < min || value > max) {
            field.setText(str);
            value = Double.valueOf(str);
            return value;
        }
        return value;
    }

    /**
     * Checks that the entered value is within bounds provided and that the
     * entered value is actually a double number. In case of error default value
     * from a HashMap is substituted instead.
     *
     * @param min minimal value
     * @param max maximal value
     * @param field text field
     * @param str default value
     * @param oldStrings
     * @return either entered value or default value in case of an error
     */
    public static Double TestValueWithMemory(double min, double max, JTextField field,
            String str, Map<JTextField, String> oldStrings) {
        Double value;
        String oldStr = oldStrings.get(field);
        String newStr = field.getText();
        if (newStr.length() == 0) {
            return 0.0;
        }
        if (oldStr == null) {
            oldStr = str;
            oldStrings.put(field, str);
        }
        try {
            value = Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(oldStr);
            value = Double.valueOf(oldStr);
            return value;
        }
        if (value < min || value > max) {
            field.setText(oldStr);
            value = Double.valueOf(oldStr);
            return value;
        }
        oldStrings.replace(field, newStr);
        return value;
    }

    /**
     * Checks that the entered value is within bounds provided and that the
     * entered value is actually a double number. In case of error default value
     * from a HashMap is substituted instead.
     *
     * @param min minimal value
     * @param max maximal value
     * @param field text field
     * @param str default value
     * @param oldStrings
     * @return either entered value or default value in case of an error
     */
    public static Double TestValueWithMemory(double min, double max, TextField field,
            String str, Map<TextField, String> oldStrings) {
        Double value;
        String oldStr = oldStrings.get(field);
        String newStr = field.getText();
        if (newStr.length() == 0) {
            return 0.0;
        }
        if (oldStr == null) {
            oldStr = str;
            oldStrings.put(field, str);
        }
        try {
            value = Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(oldStr);
            value = Double.valueOf(oldStr);
            return value;
        }
        if (value < min || value > max) {
            field.setText(oldStr);
            value = Double.valueOf(oldStr);
            return value;
        }

        oldStrings.replace(field, newStr);
        return value;
    }

    /**
     * Returning a JFormattedTextField with an Integer formatter installed with
     * min and max values
     *
     * @param initValue
     * @param minValue
     * @param maxValue
     * @return
     */
    public static JFormattedTextField getIntegerFormattedTextField(Integer initValue,
            Integer minValue, Integer maxValue) {
        JFormattedTextField box = new JFormattedTextField(initValue);
        //Formatter getter names
        String[] methods = {"getEditFormatter", "getDisplayFormatter", "getDefaultFormatter"};
        //Default formatter factory
        DefaultFormatterFactory factory = (DefaultFormatterFactory) box.getFormatterFactory();
        InternationalFormatter formatter;
        //Setting up all four formatters
        try {
            for (int i = 0; i < 3; i++) {
                formatter = (InternationalFormatter) factory.getClass().getMethod(methods[i], null).invoke(factory);
                formatter.setMinimum(minValue);
                formatter.setMaximum(maxValue);
                formatter.setAllowsInvalid(true);
                formatter.setCommitsOnValidEdit(false);
            }
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MyTextUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return box;
    }
    
    /**
     * Returning a JFormattedTextField with a Double formatter installed with
     * min and max values
     *
     * @param initValue
     * @param minValue
     * @param maxValue
     * @return
     */
    public static JFormattedTextField getDoubleFormattedTextField(Double initValue,
            Double minValue, Double maxValue) {
        //Default formatter factory
        DefaultFormatterFactory factory = new DefaultFormatterFactory (
                new NumberFormatter (),
                new NumberFormatter (),
                new NumberFormatter ()
        );
        JFormattedTextField box = new JFormattedTextField(factory, initValue);
        
        //Formatter getter names
        String[] methods = {"getEditFormatter", "getDisplayFormatter", "getDefaultFormatter"};
        
        //Setting up all four formatters
        InternationalFormatter formatter; 
        try {
            for (int i = 0; i < 3; i++) {
                formatter = (InternationalFormatter) factory.getClass().getMethod(methods[i], null).invoke(factory);
                formatter.setMinimum(minValue);
                formatter.setMaximum(maxValue);
                formatter.setAllowsInvalid(true);
                formatter.setCommitsOnValidEdit(false);
            }
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MyTextUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return box;
    }
}
