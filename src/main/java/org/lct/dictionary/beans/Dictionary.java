/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by sgourio on 12/04/15.
 */
public enum Dictionary {

    french("ods7.json","reverse-ods7.json"),
    english("sowpod.json", "reverse-sowpod.json");

    private final Logger logger = LoggerFactory.getLogger(Dictionary.class);
    private DAWG dawg;
    private DAWG reverseDawg;

    Dictionary(String fileName, String reverseFileName) {
        try {
            URL cpr = this.getClass().getClassLoader().getResource(fileName);
            ObjectMapper mapper = new ObjectMapper();
            dawg = mapper.readValue(cpr, DAWG.class);

            cpr = this.getClass().getClassLoader().getResource(reverseFileName);
            reverseDawg = mapper.readValue(cpr, DAWG.class);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public DAWG getDawg() {
        return dawg;
    }
    public DAWG getReverseDawg() {
        return reverseDawg;
    }

    public static Dictionary getByLang(String lang){
        if( "en".equals(lang) ) {
            return english;
        }else{
            return french;
        }
    }
}
