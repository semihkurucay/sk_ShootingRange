/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.tc_check;

/**
 *
 * @author semih
 */
public class Check {
    private int[] numbers;
    private int[] tc;
    
    public void setTC(char[] _tc){
         tc = new int[_tc.length];
        
        for(int i = 0; i < _tc.length; i++){
            tc[i] = Character.getNumericValue(_tc[i]);
        }
    }
    
    public boolean getCheck(){
        if(checkMethod_0() && checkMethod_1() && checkMethod_2() && checkMethod_3() && checkMethod_4()){
            return true;
        } else {
            return false;
        }
    }
    
    private void convertToArrays(int calculus){
        String number = String.valueOf(calculus);
        char[] numbers = number.toCharArray();
        
        this.numbers = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            this.numbers[i] =  Character.getNumericValue(numbers[i]);
        }
    }
    
    private boolean checkMethod_0(){
        if(tc.length == 11){
            return true;
        }
        
        return false;
    }
    
    private boolean checkMethod_1(){
        int num1 = 0, num2 = 0;
        
        for(int i = 0; i < 9; i++){
            if(i == 0){
                num1 += tc[i];
            } else if(i % 2 == 0){
                num1 += tc[i];
            } else {
                num2 += tc[i];
            }
        }
        
        int calculus = (num1 * 7) + (num2 * 9);
        convertToArrays(calculus);
        
        if(tc[9] == numbers[numbers.length - 1]){
            return true;
        }
        
        return false;
    }
    
    private boolean checkMethod_2(){
        int num1 = 0, num2 = 0;
        
        for(int i = 0; i < 9; i++){
            if(i == 0){
                num1 += tc[i];
            } else if(i % 2 == 0){
                num1 += tc[i];
            } else {
                num2 += tc[i];
            }
        }
        
        int calculus = (num1 * 7) - num2;
        convertToArrays(calculus);
        
        if(tc[9] == numbers[numbers.length - 1]){
            return true;
        }
        
        return false;
    }
    
    private boolean checkMethod_3(){
        int num1 = 0;
        
        for(int i = 0; i < 9; i++){
            if(i == 0){
                num1 += tc[i];
            } else if(i % 2 == 0){
                num1 += tc[i];
            }
        }
        
        int calculus = num1 * 8;
        convertToArrays(calculus);
        
        if(tc[10] == numbers[numbers.length - 1]){
            return true;
        }
        
        return false;
    }
    
    private boolean checkMethod_4(){
        int num1 = 0;
        
        for(int i = 0; i < 10; i++){
            num1 += tc[i];
        }
        
        convertToArrays(num1);
        
        if(tc[10] == numbers[numbers.length - 1]){
            return true;
        }
        
        return false;
    }
}