package main.com.visiplus.pret_a_la_consommation.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Mensualite {

    private Long id;
    private LocalDate datePrelevement;
    private double partInteretsRembourses;
    private double partCapitalRembourse;
    private Long compteur = 0L;
    private Pret pret;

}
