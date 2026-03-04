package main.com.visiplus.pret_a_la_consommation.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@AllArgsConstructor
public class Client {

    private Long id;
    private String nom;
    private String prenom;
    private Long compteur = 0L;
    private List<Pret> prets;


    public List<Pret> getPretsBetweenDate(LocalDate dateDebut, LocalDate dateFin) {
        List<Pret> pretsFiltres = new ArrayList<>();
        for (Pret pret : prets) {
            LocalDate dateEffet = pret.getDateEffet();
            boolean inRange = !dateEffet.isBefore(dateDebut) && !dateEffet.isAfter(dateFin);
            if (inRange) {
                pretsFiltres.add(pret);
            }
        }
        return pretsFiltres;
    }

    public List<Pret> getPretsSortedByTauxDesc() {
        List<Pret> pretsTries = new ArrayList<>(prets);
        pretsTries.sort(Comparator.comparingDouble(Pret::getTauxAnnuel).reversed());
        return pretsTries;
    }

    public List<Pret> getPretsSortedByMontantDesc() {
        List<Pret> pretsTries = new ArrayList<>(prets);
        pretsTries.sort(Comparator.comparingDouble(Pret::getMontantDemande).reversed());
        return pretsTries;
    }
}
