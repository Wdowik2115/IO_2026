package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.model.Theatre;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find Theatres!");

        ApplicationContext context = new AnnotationConfigApplicationContext("vod");

        // Pobierz serwis z kontekstu
        TheatreService service = context.getBean(TheatreService.class);

        List<Theatre> Theatres = service.getAllTheatres();
        System.out.println(Theatres.size() + " Theatres found:");
        Theatres.forEach(System.out::println);

        // Demonstracja singleton scope
        TheatreService service2 = context.getBean(TheatreService.class);
        System.out.println("Same instance? " + (service == service2));

        // Pobierz beana z klasy konfiguracyjnej
        String foo = context.getBean("foo", String.class);
        System.out.println("Config bean: " + foo);
    }
}
