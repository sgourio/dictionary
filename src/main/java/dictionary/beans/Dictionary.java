/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package dictionary.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by sgourio on 12/04/15.
 */
public enum Dictionary {

    french("french.json"),
    english("sowpod.json");

    private final Logger logger = LoggerFactory.getLogger(Dictionary.class);
    private DAWG dawg;

    Dictionary(String fileName) {
        try {
            ClassPathResource cpr = new ClassPathResource(fileName);
            File jsonFile = cpr.getFile();
            ObjectMapper mapper = new ObjectMapper();
            dawg = mapper.readValue(jsonFile, DAWG.class);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public DAWG getDawg() {
        return dawg;
    }
}
