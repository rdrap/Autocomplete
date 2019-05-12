package spellinghelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SpellingHelper {

    private static int dictionaryLength = 1000;

    public static void main(String args[]) {
        // BUILDING THE TRIE
        String[] words = new String[dictionaryLength];
        TrieST trie = new TrieST();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/Ryan/Desktop/dictionary.txt"));
            // the above file is a list of the 1000 most common english words
            while (br.ready()) {
                String dictInput = br.readLine();
                String[] dict = dictInput.split("\\s");
                int count = 0;
                for (int i = 0; i < dict.length; i++) {
                    if (i == dict.length - 1) {
                        trie.put(dict[i], count);
                        count++;
                    } else {
                        trie.put(dict[i], null);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException Occured!");
        }

        // CHECKING WORDS
        Scanner s = new Scanner(System.in);
        System.out.println("Begin by pressing ENTER and then enter a word by entering one letter per line (enter DONE to escape):");
        int count_2 = 0;
        String str = "";
        while (!s.nextLine().equals("DONE")) {
            str += s.next();
            System.out.println("Entered Text: " + str);
            Queue temp = (Queue) trie.keysWithPrefix(str);
            if (temp.size() == 0) {
                System.out.println("No Suggested Words");
                break;
            } else if (temp.size() <= 5 && temp.size() > 1) {
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("Suggested Word: " + temp.dequeue());
                    if(i == temp.size() - 1) {
                        System.out.println("Suggested Word: " + temp.dequeue() + "\n");
                    }
                }
                continue;
            } else if (temp.size() == 1) {
                System.out.println("Final Suggested Word: " + temp.dequeue() + "\n");
                break;
            } else {
                System.out.println();
                continue;
            }
        }
    }
}

/* 
SAMPLE OUTPUT #1:
Begin by pressing ENTER and then enter a word by entering one letter per line (enter DONE to escape):

a
Entered Text: a

p
Entered Text: ap
Suggested Word: appear
Suggested Word: apple

p
Entered Text: app
Suggested Word: appear
Suggested Word: apple

l
Entered Text: appl
Final Suggested Word: apple

------------------------------------------

SAMPLE OUTPUT #2:
Begin by pressing ENTER and then enter a word by entering one letter per line (enter DONE to escape):

e
Entered Text: e

x
Entered Text: ex

a
Entered Text: exa
Suggested Word: exact
Suggested Word: example

m
Entered Text: exam
Final Suggested Word: example

------------------------------------------

SAMPLE OUTPUT #3:
Begin by pressing ENTER and then enter a word by entering one letter per line (enter DONE to escape):

s
Entered Text: s

c
Entered Text: sc
Suggested Word: scale
Suggested Word: school
Suggested Word: science

h
Entered Text: sch
Final Suggested Word: school
*/
