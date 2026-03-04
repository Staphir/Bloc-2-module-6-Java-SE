package test.com.visiplus.pret_a_la_consommation.business;

import main.com.visiplus.pret_a_la_consommation.business.Mensualite;
import main.com.visiplus.pret_a_la_consommation.business.Pret;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MensualiteUnitTest {

    @Test
    void shouldCreateMensualiteWithAllFields() {
        Long id = 10L;
        LocalDate datePrelevement = LocalDate.of(2025, 1, 15);
        double partInteretsRembourses = 45.5;
        double partCapitalRembourse = 204.5;
        Long compteur = 1L;
        Pret pret = Fixtures.aPret().build();

        Mensualite mensualite = Fixtures.MensualiteBuilder.builder()
            .id(id)
            .datePrelevement(datePrelevement)
            .partInteretsRembourses(partInteretsRembourses)
            .partCapitalRembourse(partCapitalRembourse)
            .compteur(compteur)
            .pret(pret)
            .build()
            .build();

        assertInstanceOf(Mensualite.class, mensualite);
        assertEquals(id, mensualite.getId());
        assertEquals(datePrelevement, mensualite.getDatePrelevement());
        assertEquals(partInteretsRembourses, mensualite.getPartInteretsRembourses(), 0.0001);
        assertEquals(partCapitalRembourse, mensualite.getPartCapitalRembourse(), 0.0001);
        assertEquals(compteur, mensualite.getCompteur());
        assertEquals(pret, mensualite.getPret());
    }

    @Test
    void shouldCreateMensualiteWithNullId() {
        Mensualite mensualite = Fixtures.MensualiteBuilder.builder()
            .id(null)
            .build()
            .build();

        assertNull(mensualite.getId());
    }

    @Test
    void shouldCreateMensualiteWithNullDatePrelevement() {
        Mensualite mensualite = Fixtures.MensualiteBuilder.builder()
            .datePrelevement(null)
            .build()
            .build();

        assertNull(mensualite.getDatePrelevement());
    }

    @Test
    void shouldCreateMensualiteWithNullCompteur() {
        Mensualite mensualite = Fixtures.MensualiteBuilder.builder()
            .compteur(null)
            .build()
            .build();

        assertNull(mensualite.getCompteur());
    }

    @Test
    void shouldCreateMensualiteWithNullPret() {
        Mensualite mensualite = Fixtures.aMensualite().build();

        assertNull(mensualite.getPret());
    }

}
