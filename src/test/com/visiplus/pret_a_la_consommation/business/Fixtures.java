package test.com.visiplus.pret_a_la_consommation.business;

import lombok.Builder;
import lombok.Getter;
import main.com.visiplus.pret_a_la_consommation.business.Client;
import main.com.visiplus.pret_a_la_consommation.business.Mensualite;
import main.com.visiplus.pret_a_la_consommation.business.Pret;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    @Getter
    @Builder
    public static class ClientBuilder {
        @Builder.Default
        private Long id = 1L;
        @Builder.Default
        private String nom = "Dupont";
        @Builder.Default
        private String prenom = "Jean";
        @Builder.Default
        private Long compteur = 0L;
        @Builder.Default
        private List<Pret> prets = new ArrayList<>();

        public Client build() {
            return new Client(id, nom, prenom, compteur, prets);
        }
    }

    @Getter
    @Builder
    public static class PretBuilder {
        @Builder.Default
        private Long id = 1L;
        @Builder.Default
        private double montantDemande = 10000.0;
        @Builder.Default
        private double montantMensualite = 250.0;
        @Builder.Default
        private LocalDateTime dateSouscription = LocalDateTime.of(2024, 1, 1, 10, 0);
        @Builder.Default
        private LocalDate dateEffet = LocalDate.of(2024, 2, 1);
        @Builder.Default
        private String observations = "";
        @Builder.Default
        private Long compteur = 0L;
        @Builder.Default
        private List<Mensualite> mensualites = new ArrayList<>();
        private Client client;

        public Pret build() {
            return new Pret(id, montantDemande, montantMensualite, dateSouscription, dateEffet, observations, compteur, mensualites, client);
        }
    }

    @Getter
    @Builder
    public static class MensualiteBuilder {
        @Builder.Default
        private Long id = 1L;
        @Builder.Default
        private LocalDate datePrelevement = LocalDate.of(2024, 3, 1);
        @Builder.Default
        private double partInteretsRembourses = 30.0;
        @Builder.Default
        private double partCapitalRembourse = 220.0;
        @Builder.Default
        private Long compteur = 0L;
        private Pret pret;

        public Mensualite build() {
            return new Mensualite(id, datePrelevement, partInteretsRembourses, partCapitalRembourse, compteur, pret);
        }
    }

    public static ClientBuilder aClient() {
        return ClientBuilder.builder().build();
    }

    public static PretBuilder aPret() {
        return PretBuilder.builder().build();
    }

    public static MensualiteBuilder aMensualite() {
        return MensualiteBuilder.builder().build();
    }
}
