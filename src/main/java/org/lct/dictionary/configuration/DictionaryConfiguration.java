/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.configuration;

import org.lct.dictionary.services.DictionaryService;
import org.lct.dictionary.services.impl.DictionaryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sgourio on 12/04/15.
 */
@Configuration
public class DictionaryConfiguration {

    @Bean
    public DictionaryService dictionaryService(){
        return new DictionaryServiceImpl();
    }
}
