package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    private String text = "";                                                      ///it's result text

    public Profile getDataFromFile(File file) {

        Profile pf = new Profile();
        try (FileInputStream reader = new FileInputStream(file)) {

            int c;
            while ((c = reader.read()) != -1) {                                     ///Write the line with changes
                if (c == 13 || c == 10) {
                    text += ": ";
                    continue;
                }
                text += (char) c;
            }
        } catch (IOException ex) {

            System.out.println(ex);
        }

        String[] words = text.split(": ");

        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].equals("Name")) pf.setName(words[i]);
            if (words[i - 1].equals("Age")) pf.setAge(Integer.parseInt(words[i]));
            if (words[i - 1].equals("Email")) pf.setEmail(words[i]);
            if (words[i - 1].equals("Phone")) pf.setPhone((long) Integer.parseInt(words[i]));
        }

        return pf;
    }
}
