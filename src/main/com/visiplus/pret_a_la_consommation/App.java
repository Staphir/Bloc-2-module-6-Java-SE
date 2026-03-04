package main.com.visiplus.pret_a_la_consommation;

import main.com.visiplus.pret_a_la_consommation.business.Client;
import main.com.visiplus.pret_a_la_consommation.business.Mensualite;
import main.com.visiplus.pret_a_la_consommation.business.Pret;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();

        Client client1 = new Client(1L, "Dupont", "Jean", 0L, new ArrayList<>());
        client1.getPrets().add(createPret(101L, 12000.0, 4.2, 48L, "Pret auto", client1, 1001L, LocalDate.of(2025, 1, 5)));
        client1.getPrets().add(createPret(102L, 6000.0, 5.1, 36L, "Pret travaux", client1, 2001L, LocalDate.of(2025, 2, 5)));
        clients.add(client1);

        Client client2 = new Client(2L, "Martin", "Alice", 0L, new ArrayList<>());
        client2.getPrets().add(createPret(201L, 8000.0, 4.8, 36L, "Pret moto", client2, 3001L, LocalDate.of(2025, 1, 10)));
        client2.getPrets().add(createPret(202L, 3000.0, 6.3, 24L, "Pret personnel", client2, 4001L, LocalDate.of(2025, 3, 10)));
        client2.getPrets().add(createPret(203L, 15000.0, 3.9, 60L, "Pret renovation", client2, 5001L, LocalDate.of(2025, 4, 10)));
        clients.add(client2);

        Client client3 = new Client(3L, "Bernard", "Luc", 0L, new ArrayList<>());
        client3.getPrets().add(createPret(301L, 5000.0, 5.6, 30L, "Pret voyage", client3, 6001L, LocalDate.of(2025, 2, 15)));
        client3.getPrets().add(createPret(302L, 2000.0, 6.9, 18L, "Pret equipement", client3, 7001L, LocalDate.of(2025, 5, 15)));
        clients.add(client3);

        Client client4 = new Client(4L, "Petit", "Sophie", 0L, new ArrayList<>());
        client4.getPrets().add(createPret(401L, 11000.0, 4.1, 48L, "Pret auto electrique", client4, 8001L, LocalDate.of(2025, 1, 20)));
        client4.getPrets().add(createPret(402L, 4500.0, 5.4, 30L, "Pret etudes", client4, 9001L, LocalDate.of(2025, 3, 20)));
        client4.getPrets().add(createPret(403L, 7000.0, 4.9, 36L, "Pret amenagement", client4, 10001L, LocalDate.of(2025, 6, 20)));
        clients.add(client4);

        Client client5 = new Client(5L, "Leroy", "Nina", 0L, new ArrayList<>());
        client5.getPrets().add(createPret(501L, 9000.0, 4.6, 42L, "Pret famille", client5, 11001L, LocalDate.of(2025, 2, 25)));
        client5.getPrets().add(createPret(502L, 3500.0, 6.0, 24L, "Pret informatique", client5, 12001L, LocalDate.of(2025, 4, 25)));
        clients.add(client5);

        runMenu(clients);
    }

    private static void runMenu(List<Client> clients) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenue sur prêt à la consommation");
            System.out.println("1. Voir tous les prêts triées par montant (du plus élevé au plus petit)");
            System.out.println("2. Voir tous les prêts triées par taux (du plus élevé au plus petit)");
            System.out.println("3. Voir la liste des prêts qui débutent entre deux dates données");
            System.out.println("4. Ajouter un prêt");
            System.out.println("5. Quitter");
            System.out.println("Faîtes votre choix :");

            int choix = lireInt(scanner);
            if (choix == 5) {
                System.out.println("Fermeture de l'application.");
                return;
            }
            if (choix < 1 || choix > 5) {
                System.out.println("Choix invalide.");
                continue;
            }

            Client client = demanderClient(clients, scanner);
            if (client == null) {
                System.out.println("Client introuvable.");
                continue;
            }

            if (choix == 1) {
                afficherPrets(client.getPretsSortedByMontantDesc());
            } else if (choix == 2) {
                afficherPrets(client.getPretsSortedByTauxDesc());
            } else if (choix == 3) {
                System.out.println("Veuillez saisir la date de début au format MM/yyyy :");
                YearMonth debut = lireYearMonth(scanner);
                System.out.println("Veuillez saisir la date de fin au format MM/yyyy :");
                YearMonth fin = lireYearMonth(scanner);

                List<Pret> prets = client.getPretsBetweenDate(debut.atDay(1), fin.atEndOfMonth());
                afficherPrets(prets);
            } else {
                ajouterPret(client, clients, scanner);
            }
        }
    }

    private static Client demanderClient(List<Client> clients, Scanner scanner) {
        System.out.println("Lister des Clients");
        for (Client client : clients) {
            System.out.println(client.getId() + ". Client " + client.getId() + " (" + client.getNom() + " " + client.getPrenom() + ")");
        }
        System.out.println("Veuillez saisir l'id du client concerné :");

        long idClient = lireLong(scanner);
        for (Client client : clients) {
            if (client.getId() == idClient) {
                return client;
            }
        }
        return null;
    }

    private static void afficherPrets(List<Pret> prets) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        if (prets.isEmpty()) {
            System.out.println("Aucun prêt trouvé.");
            return;
        }

        for (Pret pret : prets) {
            System.out.println(
                "id : " + pret.getId() +
                    ", client : " + pret.getClient().getPrenom() + " " + pret.getClient().getNom().toUpperCase() +
                    ", montant emprunté : " + numberFormat.format(pret.getMontantDemande()) +
                    ", taux annuel : " + numberFormat.format(pret.getTauxAnnuel()) + " %" +
                    ", date d'effet : " + pret.getDateEffet().format(DateTimeFormatter.ofPattern("MM/yyyy"))
            );
        }
    }

    private static void ajouterPret(Client client, List<Client> clients, Scanner scanner) {
        System.out.println("Veuillez saisir le montant demandé :");
        double montantDemande = lireDouble(scanner);

        System.out.println("Veuillez saisir le taux annuel :");
        double tauxAnnuel = lireDouble(scanner);

        System.out.println("Veuillez saisir la durée en mois :");
        long nombreMensualites = lireLong(scanner);

        System.out.println("Veuillez saisir la date d'effet au format MM/yyyy :");
        YearMonth dateEffetSaisie = lireYearMonth(scanner);

        Long pretId = prochainPretId(clients);
        Long mensualiteStartId = prochaineMensualiteId(clients);

        Pret nouveauPret = createPret(
            pretId,
            montantDemande,
            tauxAnnuel,
            nombreMensualites,
            "Pret saisi en console",
            client,
            mensualiteStartId,
            dateEffetSaisie.atDay(1)
        );

        client.getPrets().add(nouveauPret);
        afficherDetailsPret(nouveauPret);
    }

    private static void afficherDetailsPret(Pret pret) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        double tauxMensuel = (pret.getTauxAnnuel() / 100.0) / 12.0;
        long n = pret.getMensualites().size();
        double montantMensualite = calculerMontantMensualite(pret.getMontantDemande(), tauxMensuel, n);

        System.out.println("Voici les détails du prêt :");
        System.out.println(
            "id : " + pret.getId() +
                ", client : " + pret.getClient().getPrenom() + " " + pret.getClient().getNom().toUpperCase() +
                ", montant emprunté : " + numberFormat.format(pret.getMontantDemande()) +
                ", mensualité : " + numberFormat.format(montantMensualite)
        );
        System.out.println("Date Capital remboursé Part des intérêts");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        double capitalCumule = 0.0;
        for (Mensualite mensualite : pret.getMensualites()) {
            capitalCumule += mensualite.getPartCapitalRembourse();
            System.out.println(
                mensualite.getDatePrelevement().format(formatter) + " " +
                    numberFormat.format(round2(capitalCumule)) + " " +
                    numberFormat.format(mensualite.getPartInteretsRembourses())
            );
        }
    }

    private static Pret createPret(Long pretId, double montantDemande, double tauxAnnuel, Long nombreMensualites, String observation,
                                   Client client, Long mensualiteStartId, LocalDate premierPrelevement) {
        List<Mensualite> mensualites = new ArrayList<>();
        Pret pret = new Pret(
            pretId,
            montantDemande,
            tauxAnnuel,
            LocalDateTime.now(),
            premierPrelevement,
            observation,
            0L,
            mensualites,
            client
        );

        double tauxMensuel = (tauxAnnuel / 100.0) / 12.0;
        double mensualiteConstante = calculerMontantMensualite(montantDemande, tauxMensuel, nombreMensualites);
        double capitalRestant = montantDemande;

        for (int i = 0; i < nombreMensualites; i++) {
            double interets = capitalRestant * tauxMensuel;
            double capital = mensualiteConstante - interets;
            if (i == nombreMensualites - 1) {
                capital = capitalRestant;
            }
            capitalRestant -= capital;

            mensualites.add(
                new Mensualite(
                    mensualiteStartId + i,
                    premierPrelevement.plusMonths(i),
                    round2(interets),
                    round2(capital),
                    nombreMensualites,
                    pret
                )
            );
        }

        return pret;
    }

    private static double calculerMontantMensualite(double capital, double tauxMensuel, Long nombreMensualites) {
        if (tauxMensuel == 0) {
            return capital / nombreMensualites;
        }
        return capital * tauxMensuel / (1 - Math.pow(1 + tauxMensuel, -nombreMensualites));
    }

    private static Long prochainPretId(List<Client> clients) {
        long max = 0L;
        for (Client client : clients) {
            for (Pret pret : client.getPrets()) {
                if (pret.getId() > max) {
                    max = pret.getId();
                }
            }
        }
        return max + 1;
    }

    private static Long prochaineMensualiteId(List<Client> clients) {
        long max = 0L;
        for (Client client : clients) {
            for (Pret pret : client.getPrets()) {
                for (Mensualite mensualite : pret.getMensualites()) {
                    if (mensualite.getId() > max) {
                        max = mensualite.getId();
                    }
                }
            }
        }
        return max + 1;
    }

    private static int lireInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez saisir un nombre entier.");
            }
        }
    }

    private static long lireLong(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez saisir un nombre entier.");
            }
        }
    }

    private static double lireDouble(Scanner scanner) {
        while (true) {
            try {
                String value = scanner.nextLine().trim().replace(',', '.');
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez saisir un nombre décimal.");
            }
        }
    }

    private static YearMonth lireYearMonth(Scanner scanner) {
        while (true) {
            try {
                return YearMonth.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Format invalide, veuillez saisir la date au format MM/yyyy.");
            }
        }
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

