package main.com.visiplus.pret_a_la_consommation.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Pret {

    private Long id;
    private double montantDemande;
    private double tauxAnnuel;
    private LocalDateTime dateSouscription;
    private LocalDate dateEffet;
    private String observations;
    private Long compteur = 0L;
    private List<Mensualite> mensualites;
    private Client client;

}
