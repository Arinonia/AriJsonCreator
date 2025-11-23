package fr.arinonia.arijsoncreator.service;

/**
 * Service responsible for formatting JSON strings with proper indentation.
 */
public final class JsonFormatter {

    private JsonFormatter() {
        // Utility class
    }

    /**
     * Formats a JSON string with proper indentation and line breaks.
     *
     * @param json The raw JSON string to format
     * @return The formatted JSON string
     */
    public static String format(String json) {
        char[] chars = json.toCharArray();
        String newLine = System.lineSeparator();
        boolean insideQuotes = false;

        StringBuilder result = new StringBuilder();
        int indent = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '\"') {
                result.append(c);
                insideQuotes = !insideQuotes;
                continue;
            }

            if (!insideQuotes) {
                switch (c) {
                    case '{':
                    case '[':
                        indent += 2;
                        result.append(c).append(newLine).append(getIndent(indent));
                        continue;
                    case '}':
                    case ']':
                        indent -= 2;
                        result.append(newLine).append(getIndent(indent)).append(c);
                        continue;
                    case ':':
                        result.append(c).append(" ");
                        continue;
                    case ',':
                        result.append(c).append(newLine).append(getIndent(indent));
                        continue;
                    default:
                        if (Character.isWhitespace(c)) {
                            continue;
                        }
                }
            }

            result.append(c);
            if (c == '\\' && i + 1 < chars.length) {
                result.append(chars[++i]);
            }
        }

        return result.toString();
    }

    private static String getIndent(int indent) {
        if (indent <= 0) {
            return "";
        }
        return String.format("%" + indent + "s", "");
    }
}
