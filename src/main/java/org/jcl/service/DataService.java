package org.jcl.service;/**
 * Created by admin on 2018/8/30.
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jichenglu
 * @create 2018-08-30 16:28
 **/
@Service
public class DataService {

//    public Map<String,String> map =new ConcurrentHashMap<String, String>();

//    public List<String> list= Collections.synchronizedList(new ArrayList<String>());

    public List<String> list =new CopyOnWriteArrayList<String>();

    @KafkaListener(topics = "hello")
    public void listen (ConsumerRecord<String, String> record) {
        list.add(record.value());
    }


    /**
     * 每30秒读取一次数据
     */
    @Scheduled(cron = "0/30 * * * * *")
    public void pushData(){
        if (list.size()>0){
            System.out.println("=========="+list.size());
            list.clear();
        }
    }


}
