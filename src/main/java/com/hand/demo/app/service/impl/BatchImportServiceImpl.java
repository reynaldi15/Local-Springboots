//package com.hand.demo.app.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hand.demo.domain.entity.User;
//import com.hand.demo.domain.repository.UserRepository;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.hzero.boot.imported.app.service.BatchImportHandler;
//import org.hzero.boot.imported.infra.validator.annotation.ImportService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//@ImportService(templateCode = "DEMO-CLIENT-48209")
//
//public class BatchImportServiceImpl extends BatchImportHandler {
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public Boolean doImport(List<String> data) {
//        List<User> userList = new ArrayList<>();
//        if(data== null || data.isEmpty()){
//            return Boolean.FALSE;
//        }
//        AtomicBoolean flag= new AtomicBoolean(true);
//        data.forEach(user->{
//            try{
//                userList.add(objectMapper.readValue(user, User.class));
//            }catch (JsonParseException e){
//                flag.set(false);
//            } catch (JsonMappingException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        userRepository.batchInsertSelective(userList);
//        return flag.get();
//    }
//
//
//}
