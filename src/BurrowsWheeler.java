import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BurrowsWheeler {
    protected int originalIndex;
    protected String text, lastColumn = "";

    private String getFirstColumn() {
        char[] characters = lastColumn.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    private int getOccurrenceIndex(String string, char character, int occurrence) {
        int index = -1;
        for (int i = 0; i <= occurrence; i++) index = string.indexOf(character, index + 1);
        return index;
    }

    private String initialiseText() {
        String text = "";
        String firstColumn = getFirstColumn();
        int previous = originalIndex;

        for (int i = 0; i < lastColumn.length(); i++) {
            char character = firstColumn.charAt(previous);
            int position = previous - firstColumn.indexOf(character);
            previous = getOccurrenceIndex(lastColumn, character, position);
            text += character;
        }

        return text;
    }

    private String initialiseText(String filename) {
        Scanner in = Helper.initialiseScanner(filename);
        String text = "";
        while (in.hasNext()) text += in.next().charAt(0);
        return text;
    }

    private String getCircularSuffix(int i) {
        return text.substring(i) + text.substring(0, i);
    }

    private int[] getIndex() {
        // I can't explain it. Sorry, mate
        int[] index = new int[text.length()];
        int[] inverse = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            String thisString = getCircularSuffix(i);

            for (int j = 0; j < text.length(); j++) if (thisString.compareTo(getCircularSuffix(j)) > 0) inverse[i]++;

            index[inverse[i]] = i;
        }
        originalIndex = inverse[0];

        return index;
    }

    private String initialiseLastColumn(int[] index) {
        String lastColumn = "";
        for (int i : index) lastColumn += text.charAt((text.length() - 1 + i) % text.length());
        return lastColumn;
    }

    private void save(String filename) {
        PrintWriter out = Helper.initialiseWriter(filename);
        out.println(originalIndex);
        out.print(lastColumn);
        out.close();
    }

    public static void encode(String filename) {
        BurrowsWheeler burrows = new BurrowsWheeler();

        burrows.text = burrows.initialiseText(filename);
        burrows.lastColumn = burrows.initialiseLastColumn(burrows.getIndex());

        burrows.save(Helper.getFilenamePrefix(filename) + ".burrows");

    }

    public static void decode(String filename) {
        BurrowsWheeler wheeler = new BurrowsWheeler();

        Scanner in = Helper.initialiseScanner(filename);
        wheeler.originalIndex = Integer.parseInt(in.nextLine());
        while (in.hasNext()) wheeler.lastColumn += in.next().charAt(0); // rest of the file
        in.close();

        wheeler.text = wheeler.initialiseText();
        PrintWriter out = Helper.initialiseWriter(Helper.getFilenamePrefix(filename) + ".wheeler");
        out.print(wheeler.text);
        out.close();
    }
}
