/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.services;

import org.lct.dictionary.beans.Dictionary;

import java.util.Set;

/**
 * Created by sgourio on 12/04/15.
 */
public interface DictionaryService {

    /**
     * Test if word is in the dictionary
     * @param word
     * @param dictionary
     * @return true if word exist in dictionary
     */
    public boolean exist(String word, Dictionary dictionary);

    /**
     * Find all possible anagrams for this word
     * @param word
     * @param dictionary
     * @return
     */
    public Set<String> findAnagrams(String word, Dictionary dictionary);


    /**
     * Find possible suffix of this word
     * @param wordPrefix
     * @param dictionary
     * @return
     */
    public Set<String> findSuffix(String wordPrefix, Dictionary dictionary );


    /**
     * Find possible words with this pattern
     * @param pattern
     * @param draw
     * @param dictionary
     * @return
     */
    public Set<String> completePattern(String pattern, String draw, Dictionary dictionary);

}
