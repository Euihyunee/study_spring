package spring3_4_5.boot.service;

import org.springframework.stereotype.Service;

@Service
public class AOPService {

    public String test(String query){
        return query;
    }
    public String test(){
        return "파라미터 없음";
    }
}
