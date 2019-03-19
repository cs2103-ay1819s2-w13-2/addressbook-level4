package seedu.address.logic.exportUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import seedu.address.model.person.Person;

/**
 * File handling utility functions for exporting data
 */
public class ExportUtil {

    /**
     * Exports the person details into a text file
     * @throws IOException if file cannot be created successfully
     */
    public static void exportPersonToFile (Person person) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        String now = dateFormat.format(date);

        String filename = now + "_" + generateRandom() + "_" + person.getName();
        filename = filename.replaceAll("\\W", "");

        File file = new File(filename + ".txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            String formatExport = "Name : " + person.getName().toString() + System.lineSeparator();
            formatExport += "Email : " + person.getEmail().toString() + System.lineSeparator();
            formatExport += "Phone : " + person.getPhone().toString() + System.lineSeparator();

            writer.write(formatExport);
            writer.close();
        } catch (IOException e) {
            System.out.println("File already Exists");
            exportPersonToFile(person);
        }
    }

    /**
     * Creates a random string for a part of the filename
     */
    private static String generateRandom() {
        String allowedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder strBuilt = new StringBuilder();
        Random rnd = new Random();
        while (strBuilt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * allowedChar.length());
            strBuilt.append(allowedChar.charAt(index));
        }
        String randStr = strBuilt.toString();
        return randStr;
    }
}
