package app;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplorer {
    private final String baseName = "resources.Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;

    public void run() {
        setLocale("en-US");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message("prompt"));
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "locales":
                    displayLocales();
                    break;
                case "set":
                    setLocale(params[1]);
                    break;
                case "info":
                    localeInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println(message("invalid"));
            }
        }
    }

    private String message(String key, String... arguments) {
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);
        return message;
    }

    private void setLocale(String languageTag) {
        this.locale = Locale.forLanguageTag(languageTag);
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        System.out.println(message("locale.set", languageTag));
    }

    private void displayLocales() {
        System.out.println(message("locales"));
        Locale available[] =
                Locale.getAvailableLocales();
        for (Locale locale : available) {
            System.out.println(locale.toLanguageTag() + "\t" + locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        }
    }

    private void localeInfo() {

        String pattern = message("info");
        Object[] arguments = {locale.toLanguageTag()};
        String info = new MessageFormat(pattern).format(arguments);
        System.out.println(info);
        System.out.println(message("country") + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println(message("language") + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");
        Currency currency = Currency.getInstance(locale);
        System.out.println(message("currency") + currency.getCurrencyCode() + " (" + currency.getDisplayName() + ")");
        System.out.println("Symbol: " + currency.getSymbol());

        System.out.println(message("week-days") + DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, locale) +
                ", " + DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, locale));
        System.out.println(message("months") + Month.JANUARY.getDisplayName(TextStyle.FULL, locale) +
                ", " + Month.FEBRUARY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.MARCH.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.APRIL.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.MAY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.JUNE.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.JULY.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.AUGUST.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.SEPTEMBER.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.OCTOBER.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.NOVEMBER.getDisplayName(TextStyle.FULL, locale)
                +
                ", " + Month.DECEMBER.getDisplayName(TextStyle.FULL, locale));

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.getDefault());
        System.out.println(message("today") + today.format(formatter1) + " ("+ today.format(formatter)+")");


    }


    public static void main(String args[]) {
        new LocaleExplorer().run();
    }
}