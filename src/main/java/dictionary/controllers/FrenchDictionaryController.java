/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package dictionary.controllers;

import dictionary.beans.Dictionary;
import dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sgourio on 12/04/15.
 */
@RestController
@RequestMapping("/fr")
public class FrenchDictionaryController {


    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/exists")
    public boolean exists(@RequestParam(value="word", defaultValue="World") String word) {
        return dictionaryService.exist(word, Dictionary.french);
    }
}
