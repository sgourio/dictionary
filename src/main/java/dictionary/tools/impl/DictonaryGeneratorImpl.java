/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package dictionary.tools.impl;

import dictionary.beans.DAWG;
import dictionary.tools.DictionaryGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by sgourio on 12/04/15.
 */
public class DictonaryGeneratorImpl implements DictionaryGenerator{

    @Override
    public DAWG createFromFile(File txtFile) {
        DAWG dawg = new DAWG('_');
        File file = txtFile;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String mot = scanner.nextLine();
                addWordDAWG(dawg, mot + "|");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dawg;
    }

    /**
     * Ajoute un mot dans le DAWG
     * @param dawg
     * @param word
     */
    private void addWordDAWG(DAWG dawg, String word ){
        if( word.length() > 0 ){
            char c = word.charAt(0);
            String subWord = word.substring(1);
            Map<Character, DAWG> children = dawg.getChildren();
            if( children == null ){
                children = new TreeMap<Character, DAWG>();
                dawg.setChildren( children );
            }
            DAWG child = children.get(c);
            if( child == null ){
                child = new DAWG(c);
                children.put(c, child);
            }
            addWordDAWG(child, subWord);
        }
    }
}
