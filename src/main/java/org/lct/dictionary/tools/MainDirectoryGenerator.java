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
        File txtFile = new File("/Users/sgourio/workspace/lct/words/ods8.txt");
        DAWG dawg = dictionaryGenerator.createFromFile(txtFile, true);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("/Users/sgourio/workspace/lct/dictionary/src/main/resources/reverse-ods8.json"), dawg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
