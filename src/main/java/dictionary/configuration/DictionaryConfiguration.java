/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package dictionary.configuration;

import dictionary.services.DictionaryService;
import dictionary.services.impl.DictionaryServiceImpl;
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
