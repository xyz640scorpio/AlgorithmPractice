import java.util.*;

public class reverseWords2 {
    public String reverseWords(String input) {
        // corner case
        if (input == null || input.length() == 0) {
            return input;
        }
    // step 1
        char[] array = input.toCharArray();
        int write = 0;
        int start = 0;
        int count = 1;
        for (int read = 0; read < array.length; read++) {
            if (array[read] == ' ') { // read a space character
                // first space after a word
                if (count == 0) {
                    reverse(array, start, write - 1);
                    array[write++] = array[read];
                    start = write; // update the start position of new word
                    count++;
                }
            } else { // read a non-space character
                array[write++] = array[read];
                count = 0;
            }
        }
        if (count == 0) { // no trailing space condition
            reverse(array, start, --write);
        } else { // with trailing space condition
            write = write - 2;
        }
    // step 2
        reverse(array, 0, write);
        if (write + 1 <= 0) {
            return new String();
        }
        return new String(Arrays.copyOf(array, write));
    }

    private void reverse(char[] array, int start, int end) {
        while (start < end)  {
            char temp = array[start];
            array[start++] =  array[end];
            array[end--] = temp;
        }
    }

    public static void main(String[] args) {
        reverseWords2 object = new reverseWords2();
        String res = object.reverseWords(" AC BC CC ");
        System.out.println(res);
        String res2 = object.reverseWords("  ");
        System.out.println(res2);
    }
}
