/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Comparator;

/**
 *
 * @author Training
 */
public class MyComparator implements Comparator{

   
    public int compare(Object v1, Object v2) {
        return (Integer)v2 - (Integer)v1;
    }

  
    
}
