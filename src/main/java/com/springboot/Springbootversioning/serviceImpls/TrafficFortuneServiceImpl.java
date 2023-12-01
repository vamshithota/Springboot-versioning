package com.springboot.Springbootversioning.serviceImpls;

import com.springboot.Springbootversioning.services.TrafficFortuneService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        // simulate a delay
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }
        return getFortune();
    }

}
