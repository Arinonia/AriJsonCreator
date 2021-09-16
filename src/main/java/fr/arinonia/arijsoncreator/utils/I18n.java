package fr.arinonia.arijsoncreator.utils;


import fr.arinonia.arijsoncreator.Main;
import utybo.minkj.locale.MinkJ;

import java.util.Locale;

public class I18n {

    public static final MinkJ LANG = new MinkJ();

    public static void init() {
        try {
            LANG.loadTranslationsFromFile(Locale.FRENCH, Main.class.getResourceAsStream("/langs/French.lang"));
            LANG.loadTranslationsFromFile(Locale.ENGLISH, Main.class.getResourceAsStream("/langs/English.lang"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
