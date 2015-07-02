/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextUtilities;

import javax.swing.JTextField;
import java.util.Map;
import javafx.scene.control.TextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.DefaultFormatterFactory;

/**
 * Class for some text processing utilities
 * @author Ruslan feshchenko
 * @version 0.1
 */
public class MyTextUtilities {

    /**
     * Checks that the entered value is within bounds provided and that the entered value is
     * actually a double number. In case of error default value is substituted instead.
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
            value=Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(str);
            value=Double.valueOf(str);
            return value;
        }
        if (value < min || value > max ) {
            field.setText(str);
            value=Double.valueOf(str);
            return value;
        }
        return value;
    }
    
    /**
     * Checks that the entered value is within bounds provided and that the entered value is
     * actually a double number. In case of error default value is substituted instead.
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
            value=Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(str);
            value=Double.valueOf(str);
            return value;
        }
        if (value < min || value > max ) {
            field.setText(str);
            value=Double.valueOf(str);
            return value;
        }
        return value;
    }
    
    /**
     * Checks that the entered value is within bounds provided and that the entered value is
     * actually a double number. In case of error default value from a HashMap is substituted instead.
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
        String oldStr=oldStrings.get(field);
        String newStr=field.getText();
        if (newStr.length() == 0) {
            return 0.0;
        }
        if (oldStr==null) {
            oldStr=str;
            oldStrings.put(field, str);
        }
        try {
            value=Double.valueOf(newStr);
        } catch (NumberFormatException e) {
            field.setText(oldStr);
            value=Double.valueOf(oldStr);
            return value;
        }
        if (value < min || value > max ) {
            field.setText(oldStr);
            value=Double.valueOf(oldStr);
            return value;
        }
        oldStrings.replace(field, newStr);
        return value;
    }
    
     /**
     * Checks that the entered value is within bounds provided and that the entered value is
     * actually a double number. In case of error default value from a HashMap is substituted instead.
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
     * Returning a JFormattedTextField with an Integer formatter installed 
     * with min and max values
     * @param initValue
     * @param minValue
     * @param maxValue
     * @return
     */
    public static JFormattedTextField getIntegerFormattedTextField(Integer initValue, 
            Integer minValue, Integer maxValue) {
        JFormattedTextField box = new JFormattedTextField(initValue);
        InternationalFormatter formatter
                = (InternationalFormatter) (((DefaultFormatterFactory) box.getFormatterFactory()).getEditFormatter());
        formatter.setMinimum(minValue);
        formatter.setMaximum(maxValue);
        formatter.setCommitsOnValidEdit(false);
        return box;
    }
}
