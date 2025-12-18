/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author hansa
 */
public class Validering {
    public static boolean isValidEpost(String ePost) {
       return ePost.matches ("^[a-zA-Z0-9._]{3,20}$");  
    }
}
    

    


