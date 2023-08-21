package com.driver;

public class Main {
    public static void main(String[] args) throws Exception {
        CurrentAccount vk = new CurrentAccount("vivek",20000000.00, "VLLUR");
        vk.validateLicenseId();
        vk.setBalance(1000);
        System.out.println(vk.getBalance());

    }
}