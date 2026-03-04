package test.com.visiplus.pret_a_la_consommation.business;

import main.com.visiplus.pret_a_la_consommation.business.Client;
import main.com.visiplus.pret_a_la_consommation.business.Mensualite;
import main.com.visiplus.pret_a_la_consommation.business.Pret;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PretUnitTest {

    @Test
    void shouldCreatePretWithAllFields() {
        Long id = 30L;
        double montantDemande = 12000.0;
        double montantMensualite = 300.0;
        LocalDateTime dateSouscription = LocalDateTime.of(2025, 2, 10, 14, 30);
        LocalDate dateEffet = LocalDate.of(2025, 3, 1);
        String observations = "RAS";
        Long compteur = 3L;
        List<Mensualite> mensualites = new ArrayList<>();
        mensualites.add(Fixtures.aMensualite().build());
        Client client = Fixtures.aClient().build();

        Pret pret = Fixtures.PretBuilder.builder()
            .id(id)
            .montantDemande(montantDemande)
            .montantMensualite(montantMensualite)
            .dateSouscription(dateSouscription)
            .dateEffet(dateEffet)
            .observations(observations)
            .compteur(compteur)
            .mensualites(mensualites)
            .client(client)
            .build()
            .build();

        assertInstanceOf(Pret.class, pret);
        assertEquals(id, pret.getId());
        assertEquals(montantDemande, pret.getMontantDemande(), 0.0001);
        assertEquals(montantMensualite, pret.getTauxAnnuel(), 0.0001);
        assertEquals(dateSouscription, pret.getDateSouscription());
        assertEquals(dateEffet, pret.getDateEffet());
        assertEquals(observations, pret.getObservations());
        assertEquals(compteur, pret.getCompteur());
        assertEquals(mensualites, pret.getMensualites());
        assertEquals(client, pret.getClient());
    }

    @Test
    void shouldCreatePretWithNullId() {
        Pret pret = Fixtures.PretBuilder.builder()
            .id(null)
            .build()
            .build();

        assertNull(pret.getId());
    }

    @Test
    void shouldCreatePretWithNullDateSouscription() {
        Pret pret = Fixtures.PretBuilder.builder()
            .dateSouscription(null)
            .build()
            .build();

        assertNull(pret.getDateSouscription());
    }

    @Test
    void shouldCreatePretWithNullDateEffet() {
        Pret pret = Fixtures.PretBuilder.builder()
            .dateEffet(null)
            .build()
            .build();

        assertNull(pret.getDateEffet());
    }

    @Test
    void shouldCreatePretWithNullObservations() {
        Pret pret = Fixtures.PretBuilder.builder()
            .observations(null)
            .build()
            .build();

        assertNull(pret.getObservations());
    }

    @Test
    void shouldCreatePretWithNullCompteur() {
        Pret pret = Fixtures.PretBuilder.builder()
            .compteur(null)
            .build()
            .build();

        assertNull(pret.getCompteur());
    }

    @Test
    void shouldCreatePretWithNullMensualites() {
        Pret pret = Fixtures.PretBuilder.builder()
            .mensualites(null)
            .build()
            .build();

        assertNull(pret.getMensualites());
    }

    @Test
    void shouldCreatePretWithNullClient() {
        Pret pret = Fixtures.PretBuilder.builder()
            .client(null)
            .build()
            .build();

        assertNull(pret.getClient());
    }

}
