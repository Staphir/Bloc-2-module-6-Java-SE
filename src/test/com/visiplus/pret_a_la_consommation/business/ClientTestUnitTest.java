package test.com.visiplus.pret_a_la_consommation.business;

import main.com.visiplus.pret_a_la_consommation.business.Client;
import main.com.visiplus.pret_a_la_consommation.business.Pret;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTestUnitTest {

    @Test
    void shouldCreateClientWithAllFields() {
        Long id = 20L;
        String nom = "Martin";
        String prenom = "Alice";
        Long compteur = 2L;
        List<Pret> prets = new ArrayList<>();
        prets.add(Fixtures.aPret().build());

        Client client = Fixtures.ClientBuilder.builder()
            .id(id)
            .nom(nom)
            .prenom(prenom)
            .compteur(compteur)
            .prets(prets)
            .build()
            .build();

        assertInstanceOf(Client.class, client);
        assertEquals(id, client.getId());
        assertEquals(nom, client.getNom());
        assertEquals(prenom, client.getPrenom());
        assertEquals(compteur, client.getCompteur());
        assertEquals(prets, client.getPrets());
    }

    @Test
    void shouldCreateClientWithNullId() {
        Client client = Fixtures.ClientBuilder.builder()
            .id(null)
            .build()
            .build();

        assertNull(client.getId());
    }

    @Test
    void shouldCreateClientWithNullNom() {
        Client client = Fixtures.ClientBuilder.builder()
            .nom(null)
            .build()
            .build();

        assertNull(client.getNom());
    }

    @Test
    void shouldCreateClientWithNullPrenom() {
        Client client = Fixtures.ClientBuilder.builder()
            .prenom(null)
            .build()
            .build();

        assertNull(client.getPrenom());
    }

    @Test
    void shouldCreateClientWithNullCompteur() {
        Client client = Fixtures.ClientBuilder.builder()
            .compteur(null)
            .build()
            .build();

        assertNull(client.getCompteur());
    }

    @Test
    void shouldCreateClientWithNullPrets() {
        Client client = Fixtures.ClientBuilder.builder()
            .prets(null)
            .build()
            .build();

        assertNull(client.getPrets());
    }

}
