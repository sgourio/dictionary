/*
 * Dictionary REST module 2015.
 * Written by Sylvain Gourio
 * sylvain.gourio@gmail.com
 */

package org.lct.dictionary.tools;

import org.lct.dictionary.beans.DAWG;

import java.io.File;

/**
 * Created by sgourio on 12/04/15.
 */
public interface DictionaryGenerator {

    public DAWG createFromFile(File txtFile, boolean reverse);
}
