package com.raspi.retail.backend.model.dtos;


/**
 * Credit Card validation method will be implemented here
 */
public class CreditCard {

    private String ccNo;

    public CreditCard() {
        this.ccNo = "";
    }
    public CreditCard(String ccNo)  {
        if(validate(ccNo)) this.ccNo = ccNo;
        else System.out.println("Invalid Credit Card!\n" +
                "Please use a valid Credit Card and Try Again.");
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        if(validate(ccNo)) this.ccNo = ccNo;
        else System.out.println("Invalid Credit Card!\n" +
                "Please use a valid Credit Card and Try Again.");
    }

    /**
     * Credit card validation using the Luhn Algorithm.
     *
     * @param creditCardNumber - input credit card number to validate
     * @return boolean - if credit card number is valid or invalid
     */
    private boolean validate(String creditCardNumber){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(creditCardNumber).reverse().toString();

        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return ((s1 + s2) % 10 == 0);
    }

    @Override
    public String toString() {
        return ccNo;
    }
}
