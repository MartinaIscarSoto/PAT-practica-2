package edu.comillas.icai.gitt.pat.spring.practica2.service;

import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TareasProgramadas {
    //private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedRate = 300000)
    public void ritmoFijo() {
        //logger.info("Me ejecuto cada 5 minutos");
    }

    @Scheduled(cron = "0 * * * * *")
    public void expresionCron() {
        //logger.info("Me ejecuto cuando empieza un nuevo minuto");
    }
}