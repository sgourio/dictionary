/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.services.impl;

import org.lct.dictionary.beans.DAWG;
import org.lct.dictionary.beans.Dictionary;
import org.lct.dictionary.services.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * use directed acyclic word graph
 */
public class DictionaryServiceImpl implements DictionaryService {

    private static Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);


    public boolean exist(String word, Dictionary dictionary){
		if( word == null ){
			return false;
		}
		word = StringUtils.upperCase(word);
        word = StringUtils.stripAccents(word);
		if( !word.endsWith("|") ){
			word = word + "|";
		}
		return existInDAWG(dictionary.getDawg(), word);
	}

	/**
	 * @param dawgNode
	 * @param word
	 * @return true if word is in DAWG tree
	 */
	private boolean existInDAWG(DAWG dawgNode, String word){
		if( word == null || word.isEmpty() ){
			return true;
		}else{
			char c = word.charAt(0);
			DAWG child = dawgNode.getChildren().get(c);
			if( child != null ){
				String subWord = word.substring(1);
				return existInDAWG(child, subWord);
			}
			return false;
		}
	}


	/**
	 * Return possible suffix list
	 * @param wordPrefix
	 * @return
	 */
	public Set<String> findSuffix(String wordPrefix, Dictionary dictionary ){
        Set<String> retour = new HashSet<String>();
		DAWG dawg = findNode(dictionary.getDawg(), wordPrefix);
		if( dawg != null ){
			for( String s : findAllSuffix(dawg)){
				retour.add(wordPrefix + s );
			}
		}
		return retour;
	}

	/**
	 * Find all existing suffix at this node
	 * @param dawgNode
	 * @return
	 */
	private Set<String> findAllSuffix(DAWG dawgNode){
        Set<String> retour = new HashSet<String>();
		if( !dawgNode.isLeaf() ){
			for( DAWG child : dawgNode.getChildren().values()){
                Set<String> suffix = findAllSuffix(child);
				for( String s : suffix){
					retour.add(dawgNode.getLettre() + s);
				}
			}
		}else{
			retour.add("");
		}
		return retour;
	}

	/**
	 * Return the node at the path
	 * @param dawg
	 * @param path
	 * @return
	 */
	private DAWG findNode(DAWG dawg, String path){
		if( path == null || path.isEmpty() ){
			return dawg;
		}
		char c = path.charAt(0);
		DAWG child = dawg.getChild(c);
		if( child != null ){
			String subMot = path.substring(1);
			return findNode(child, subMot);
		}
		return null;
	}

	/**
	 *
	 * @param pattern a string like "A___B_C__D" 
	 * @param draw
     * @param dictionary
	 * @return
	 */
	public Set<String> completePattern(String pattern, String draw, Dictionary dictionary){
		if( draw == null || draw.isEmpty() || pattern == null || pattern.isEmpty()){
			return new HashSet<String>();
		}

		draw = StringUtils.upperCase(draw);
		if( !draw.endsWith("|") ){
			draw = draw + "|";
		}
		pattern = StringUtils.upperCase(pattern);

		List<Character> reg = new ArrayList<Character>();
		for( int i = 0 ; i < draw.length(); i++){
			reg.add(draw.charAt(i));
		}

		return completePattern(dictionary.getDawg(), pattern, reg);
	}

	/**
	 *
	 * @param dawg
	 * @param pattern a string like "A___B_C__D"
	 * @param draw character list
	 * @return
	 */
	private Set<String> completePattern(DAWG dawg, String pattern, List<Character> draw){
		Set<String> wordSet = new HashSet<String>();

		if( dawg.isLeaf() ){
			wordSet.add("");
			return wordSet;
		}

		if( pattern == null || pattern.isEmpty()){
			if( dawg.getChild('|') != null){
				wordSet.add("");
			}
			return wordSet;
		}
		if( draw.isEmpty()){
			char p = pattern.charAt(0);
			if( p == '_'){
				wordSet.add("");
				return wordSet;
			}else{
				DAWG child = dawg.getChild(p);
				if( child != null ){
					String subPattern = pattern.substring(1);
					Set<String> temp = completePattern(child, subPattern, draw);
					for( String s : temp){
						wordSet.add(p + s);
					}
					return wordSet;
				}else{
					return wordSet;
				}
			}
		}

		char p = pattern.charAt(0);
		if( p != '_'){
			DAWG child = dawg.getChild(p);
			if( child != null ){
				String subPattern = pattern.substring(1);
				Set<String> temp = completePattern(child, subPattern, draw);
				for( String s : temp){
					wordSet.add(p + s);
				}
				return wordSet;
			}else{
				return wordSet;
			}
		}else{
			// take another character in draw
			for( int i = 0; i < draw.size(); i++){
				char r = draw.get(0);
				if( r != '?'){
					DAWG child = dawg.getChild(r);
					if( child != null ){
						if( !child.isLeaf() ){
							List<Character> subDraw = draw.subList(1, draw.size());
							String subPattern = pattern.substring(1);
							Set<String> temp = completePattern(child, subPattern, subDraw);
							for( String s : temp){
								wordSet.add(r + s);
							}
						}else{
							wordSet.add("");
						}
					}
				}else{
					List<Character> subDraw = draw.subList(1, draw.size());
					String subPattern = pattern.substring(1);
					for( DAWG child : dawg.getChildren().values()){
						if( !child.isLeaf() ){
							Set<String> temp = completePattern(child, subPattern, subDraw);
							for( String s : temp){
								wordSet.add(child.getLettre() + s);
							}
						}else{
							wordSet.add("");
						}
					}
				}
				Collections.rotate(draw, 1);
			}
			return wordSet;
		}
	}


	public Set<String> findAnagrams(String word, Dictionary dictionary){
        word = StringUtils.upperCase(word);
        word = StringUtils.stripAccents(word);
		String pattern = "";
		for(int i = 0 ; i < word.length() && i < 11; i++){
			pattern +="_";
		
		}
		
		return completePattern(pattern, word, dictionary);
	}
	
}
