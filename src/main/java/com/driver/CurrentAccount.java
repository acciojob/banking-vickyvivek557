package com.driver;

import java.util.HashMap;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }else {
            setName(name);
            setMinBalance(5000);
            setBalance(balance);
            this.tradeLicenseId = tradeLicenseId;
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    private HashMap<Character, Integer> freqMap = new HashMap<>();
    public void validateLicenseId() throws Exception {
        boolean isVaid = isIdValid(this.tradeLicenseId);
        if(isVaid) return;

        /*now check if it can be validated or not.
        * if the frequency of any char is greater than the half of the len of the id
        * then id cannot be validated, throw exception*/

        char[] idArray = tradeLicenseId.toCharArray();
        for(char ch : idArray){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        int idLength = this.tradeLicenseId.length();
        for(char ch : freqMap.keySet()){
            if(freqMap.get(ch) > idLength){
                throw new Exception("Valid License can not be generated");
            }
        }

        //now it can be generated hence generate it
        StringBuilder validId = new StringBuilder();
        char lastCharAdded = '0';
        for(int i = 0; i < idLength; i++){
            char charToBeAdded = charToBeAdded(lastCharAdded);
            validId.append(charToBeAdded);
            lastCharAdded = charToBeAdded;
        }
        this.tradeLicenseId = validId.toString();
    }

    private char charToBeAdded(char lastAddedChar){
        int maxFreq = 0;
        char maxFreqChar = '0';
        for(char ch : freqMap.keySet()){
            if(ch != lastAddedChar){
                int curFreq = freqMap.get(ch);
                if(curFreq > maxFreq){
                    maxFreq = curFreq;
                    maxFreqChar = ch;
                }
            }
        }
        freqMap.put(maxFreqChar, maxFreq-1);
        if(freqMap.get(maxFreqChar) <= 0){
            freqMap.remove(maxFreqChar);
        }
        return maxFreqChar;
    }

    private boolean isIdValid(String id){
        char[] idArray = id.toCharArray();
        for(int i = 0; i< idArray.length - 1; i++){
            if(idArray[i] == idArray[i+1]){
                return false;
            }
        }
        return true;
    }

}
