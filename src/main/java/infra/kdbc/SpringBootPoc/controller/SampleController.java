package infra.kdbc.SpringBootPoc.controller;

import infra.kdbc.SpringBootPoc.Domain.EventVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello(){
        log.info("lomb test log test");
        return "HelloWorld";
    }

    @GetMapping("/events/{id}")
    public EventVO getEvent(@PathVariable Integer id){
        EventVO eventVO = new EventVO();
        eventVO.setId(id);
        return eventVO;
    }
}
