/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lct.dictionary.beans.DAWG;
import org.lct.dictionary.tools.impl.DictonaryGeneratorImpl;

import java.io.File;
import java.io.IOException;

/**
 * Created by sgourio on 12/04/15.
 */
public class MainDirectoryGenerator {

    public static void main(String... args){
        DictionaryGenerator dictionaryGenerator = new DictonaryGeneratorImpl();
        File txtFile = new File("/home/sgourio/workspace/scrabble/lct-play2/conf/sowpod.txt");
        DAWG dawg = dictionaryGenerator.createFromFile(txtFile, true);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("/home/sgourio/workspace/scrabble/dictionary/src/main/resources/reverse-sowpod.json"), dawg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
