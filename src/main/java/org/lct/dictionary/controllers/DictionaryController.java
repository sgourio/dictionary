/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.controllers;

import org.lct.dictionary.beans.Dictionary;
import org.lct.dictionary.services.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by sgourio on 12/04/15.
 */
@RestController
@RequestMapping("/{lang}")
public class DictionaryController {
    private static Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/exists")
    public boolean exists(@PathVariable("lang") String lang, @RequestParam(value="word", defaultValue="World") String word) {
        boolean answer = dictionaryService.exist(word, Dictionary.getByLang(lang));
        if( logger.isDebugEnabled() ) {
            logger.debug("Ask for \"" + word + "\" : " + answer);
        }
        return answer;
    }

    @RequestMapping("/anagrams")
    public Set<String> anagrams(@PathVariable("lang") String lang, @RequestParam(value="word", defaultValue="World") String word){
        return dictionaryService.findAnagrams(word, Dictionary.getByLang(lang));
    }
}
