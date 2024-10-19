package com.springlearnings.dbdemo;

import com.springlearnings.dbdemo.entity.StoreInformation;
import com.springlearnings.dbdemo.repository.StoreInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DbDemoApplication implements CommandLineRunner {

    @Autowired
    StoreInformationRepository storeInformationRepository;
    public static void main(String[] args) {
        SpringApplication.run(DbDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        StoreInformation storeInformation1 = new StoreInformation("NEW MOBILES",
                "OFFER ZONE", "765432345X");
        StoreInformation storeInformation2 = new StoreInformation("LOT MOBILES",
                "ALLU ARJUN", "12345675X");
        StoreInformation storeInformation3 = new StoreInformation("BAJAJ MOBILES",
                "EMI AVAILABLE", "345678890X");
        StoreInformation storeInformation4 = new StoreInformation("BIG C MOBILES",
                "LATEST MOBILES AT DISCOUNT", "6543345732X");
        List<StoreInformation> storeInformationList = new ArrayList<>();
        storeInformationList.add(storeInformation1);
        storeInformationList.add(storeInformation2);
        storeInformationList.add(storeInformation3);
        storeInformationList.add(storeInformation4);
        storeInformationRepository.saveAll(storeInformationList);
        storeInformationRepository.findByStoreName("NEW MOBILES").forEach(
                val -> printStoreInfo(val)
        );
        storeInformationRepository.findByStoreDetail("EMI AVAILABLE").forEach(
                val -> printStoreInfo(val)
        );
        storeInformationRepository.findByStorePhoneNumber("6543345732X").forEach(
                val -> printStoreInfo(val)
        );
        storeInformationRepository.findById(2).ifPresent(
                val -> printStoreInfo(val)
        );
        System.out.println("No. Of Records in DB : " + storeInformationRepository.count());
    }

    private void printStoreInfo(StoreInformation storeInformation){
        System.out.println(" Store ID: "+ storeInformation.getStoreId()+
                "\n Store Name: "+ storeInformation.getStoreName() +
                "\n Store Detail: "+ storeInformation.getStoreDetail()+
                "\n Store PhoneNumber: "+ storeInformation.getStorePhoneNumber()
        );
    }
}
