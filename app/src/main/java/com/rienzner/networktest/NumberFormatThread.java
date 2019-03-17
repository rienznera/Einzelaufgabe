package com.rienzner.networktest;

public class NumberFormatThread extends Thread {
    private String matnr;

    public NumberFormatThread(String matnr) {
        this.matnr = matnr;
    }

    @Override
    public void run(){
        String matnr_new="";
        for (int i = 0; i < matnr.length(); i++){
            char c = matnr.charAt(i);
            int number=Integer.parseInt(String.valueOf(c));
            if(checkPrime(number)){
                matnr_new += Integer.toString(number);
            }
        }
        this.matnr = matnr_new;
    }

    private boolean checkPrime(int num){
        boolean flag = true;
        for(int i = 2; i <= num/2; ++i)
        {
            // condition for nonprime number
            if(num % i == 0)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public String getMatnr() {
        return matnr;
    }
}
