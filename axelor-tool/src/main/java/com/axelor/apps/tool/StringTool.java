/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2017 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.tool;

import com.axelor.db.Model;
import com.google.common.base.Joiner;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class StringTool {
	
	private StringTool(){
		
	}
	
	/**
	 * First character lower
	 * 
	 * @param string
	 * @return
	 */
	public static String toFirstLower(String string){
		
		return string.replaceFirst("\\w", Character.toString(string.toLowerCase().charAt(0)));
		
	}
	
	/**
	 * First character upper.
	 * 
	 * @param string
	 * @return
	 */
	public static String toFirstUpper(String string){
		
		return string.replaceFirst("\\w", Character.toString(string.toUpperCase().charAt(0)));
		
	}
	
	/**
	 * Complete string with fixed length.
	 * 
	 * @param string
	 * @return
	 */
	public static String fillStringRight(String s, char fillChar, int size){
		
		String string = s;
		
		if (string.length() < size) { string += fillString(fillChar, size - string.length()); }
		else { string = truncRight(string, size); }
			
		return string;
		
	}
	
	/**
	 * Complete string with fixed length.
	 * 
	 * @param string
	 * @return
	 */
	public static String fillStringLeft(String s, char fillChar, int size){

		String string = s;
		
		if (string.length() < size) { string = fillString(fillChar, size - string.length()) + string; }
		else { string = truncLeft(string, size); }
			
		return string;
		
	}
	
	/**
	 * Truncate string with the first chars at size.
	 * 
	 * @param s
	 * @param size
	 * @return
	 */
	public static String truncRight(String s, int size){

		String string = s;
		
		if (string.length() > size) { string = string.substring(0, size); }
		
		return string;
	}
	
	
	/**
	 * Truncate string with the s length subtract size at s length.
	 * 
	 * @param s
	 * @param size
	 * @return
	 */
	public static String truncLeft(String s, int size){

		String string = s;
		
		if (string.length() > size) { string = string.substring(string.length()-size, string.length()); }
		
		return string;
	}
	
	
	/**
	 * Create string with one char * count.
	 * 
	 * @param fillChar
	 * @param count
	 * @return
	 */
	public static String fillString(char fillChar, int count){  
       
	   // creates a string of 'x' repeating characters  
       char[] chars = new char[count];  
       java.util.Arrays.fill(chars, fillChar);  
       return new String(chars);
       
	}  
	
	
	public static String deleteAccent(String s)  {
		
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		return temp.replaceAll("[^\\p{ASCII}]", "");
		
	}
	
	
	/**
	 * Check if string s contain only digital character
	 * @param s
	 * @return
	 */
	public static boolean isDigital(String s)  {
		
		for(Character c : s.toCharArray())  {
			
			if(!Character.isDigit(c)) { return false; }
		}
		
		return true;
	}
	
	
	/**
	 * Extract a string from right
	 * 
	 * @param string
	 * @param startIndex
	 * @param lenght
	 * @return
	 */
	public static String extractStringFromRight(String string,int startIndex,int lenght){
		String extractString = "";
		
		try{
			
			if(string != null && startIndex >= 0 && lenght >= 0 && string.length()-startIndex+lenght <= string.length()){
				extractString = string.substring(string.length()-startIndex, string.length()-startIndex+lenght);
			}
		}
		catch(Exception ex){return "";}
		
		return extractString;
	}
	
	/**
	 * Fonction permettant de convertir un tableau de bytes en une chaine hexadécimale
	 * Convert to hexadecimal string from bytes table
	 * @param bytes
	 * @return
	 */
	public static String getHexString(byte[] bytes) {
		
		StringBuilder sb = new StringBuilder(bytes.length*2);
		String s = "";
		
		for (byte b : bytes) {
			
			s = String.format("%x", b);
			if(s.length() == 1) { sb.append('0'); }
			sb.append( s );
		}
		
		return sb.toString();
	}
	
	
	/**
	 * Fonction permettant de mettre la première lettre d'une chaine de caractère en majuscule
	 * @param value
	 * 			Une chaine de caractère
	 * @return
	 */
	public static String capitalizeFirstLetter(String s) {
		if (s == null) {
			return null;
		}
		if (s.length() == 0) {
			return s;
		}
		StringBuilder result = new StringBuilder(s);
		result.replace(0, 1, result.substring(0, 1).toUpperCase());
		return result.toString();
	}


	/**
	 * Fonction permettant de recupérer une liste d'ID depuis une collection de n'importe quel objet
	 * @param collection
	 * @return
	 */
	public static String getIdFromCollection(Collection<? extends Model> collection) {
		List<Long> idList = new ArrayList<>();
		for (Model item : collection) {
			idList.add(item.getId());
		}
		return Joiner.on(",").join(idList);
	}
}
