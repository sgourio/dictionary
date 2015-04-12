/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package dictionary.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.TreeMap;

/**
 * DAWG is a representation of dictionary
 * DAWG stand for directed acyclic word graph
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"traited", "leaf"})
public class DAWG {

	private char lettre;
	private Map<Character, DAWG> children;
	private int profondeur = 0;
	private boolean traited = false;
	
	public DAWG() {
	}
	
	public DAWG(char lettre) {
		this.lettre = lettre;
	}


    @JsonProperty("t")
	public char getLettre() {
		return lettre;
	}

    @JsonProperty("t")
    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

	public boolean isLeaf(){
		return this.children == null;
	}

    @JsonProperty("p")
	public int getProfondeur() {
		return profondeur;
	}

    @JsonProperty("p")
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

    @JsonProperty("c")
    public void setChildren(Map<Character, DAWG> children) {
		this.children = children;
	}

    @JsonProperty("c")
	public Map<Character, DAWG> getChildren() {
		return children;
	}

	public DAWG getChild(Character c){
		return children != null ? children.get(c) : null;
	}

	public void addChild(DAWG child){
		if( this.children == null ){
			this.children = new TreeMap<Character, DAWG>();
		}
		this.children.put(child.getLettre(), child);
	}

	public boolean isTraited() {
		return traited;
	}

	public void setTraited(boolean traited) {
		this.traited = traited;
	}

	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof DAWG){
			DAWG dawg = (DAWG) obj;
			
			if(dawg.lettre == this.lettre && dawg.getProfondeur() == this.profondeur){
				if( dawg.getProfondeur() == 0){
					return true;
				}
				if( this.getChildren().size() != dawg.getChildren().size()){
					return false;
				}
				for( DAWG child : this.getChildren().values() ){
					DAWG otherChild = dawg.getChildren().get(child.lettre);
					if( otherChild == null){
						return false;
					}else{
						if( !child.equals(otherChild) ){
							return false;
						}
					}
				}
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Lettre : " + this.lettre +" ; profondeur : " + this.profondeur;
	}
	
	
//	public static Set<String> anagrame(DAWG dawg, List<Character> lettres){
//		Set<String> retour = new HashSet<String>();
//		if( dawg.getProfondeur() == 0){
//			retour.add("");
//		}else{
//			int size = lettres.size();
//			if(size == 1){
//				if( dawg.getChildren().get(lettres.get(0)) != null){
//					retour.add("");
//				}
//			}else{
//				for( int i = 0; i < size; i++){
//					Character lettre = lettres.get(0);
//					if( lettre == '?'){
//						List<Character> sub = lettres.subList(1, size);
//						for( DAWG child : dawg.getChildren().values()){
//							for( String s : anagrame(child, sub)){
//								if( !child.isLeaf() ){
//									retour.add(child.lettre + s);
//								}else{
//									retour.add("");
//								}
//							}
//						}
//					}else{
//						DAWG child = dawg.getChildren().get(lettre);
//						if( child != null){
//							List<Character> sub = lettres.subList(1, size);
//							for( String s : anagrame(child, sub)){
//								if( !child.isLeaf() ){
//									retour.add(child.lettre + s);
//								}else{
//									retour.add("");
//								}
//							}
//						}
//					}
//					Collections.rotate(lettres, 1);
//
//				}
//			}
//		}
//		return retour;
//	}
//
//
//	public static Set<String> anagrame(DAWG dawg, String begining, List<Character> lettres){
//		Set<String> retour = new HashSet<String>();
//		for( int i = 0 ; i < begining.length(); i++){
//			char c = begining.charAt(i);
//			dawg = dawg.getChildren().get(c);
//			if( dawg == null ){
//				return retour;
//			}
//		}
//
//		String debut = begining;
//
//		for( String s : anagrame(dawg, lettres)){
//			retour.add(debut + s);
//		}
//		return retour;
//
//	}
}
