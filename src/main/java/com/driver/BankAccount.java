package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount() {
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(digits <= 0 || sum < 0 || sum > digits * 9){
            throw new Exception("Account Number can not be generated");
        }
        int[] accNoArr = new int[digits];
        int sumOfAllDigits = 0;
        boolean isAccNoGenerated = false;

        for(int i = digits-1; i >= 0; i--){
            if(sum == 0) break;
            while(accNoArr[i] <= 9){
                accNoArr[i] += 1;
                sumOfAllDigits++;
                if(sumOfAllDigits == sum){
                    isAccNoGenerated = true;
                    break;
                }
            }
            if(isAccNoGenerated) break;
        }

        StringBuilder accountNo = new StringBuilder();
        for(int i = 0; i < digits; i++){
            accountNo.append(accNoArr[i]);
        }
        return accountNo.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        if(amount > 0){
            this.balance += amount;
        }
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        double currBalance = this.balance - amount;
        if(amount > 0 && currBalance >= this.minBalance){
            this.balance -= amount;
        }else{
            throw new Exception("Insufficient Balance");
        }
    }

}