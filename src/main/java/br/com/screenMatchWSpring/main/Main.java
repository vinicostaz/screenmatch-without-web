package br.com.screenMatchWSpring.main;

import br.com.screenMatchWSpring.model.Episode;
import br.com.screenMatchWSpring.model.EpisodeData;
import br.com.screenMatchWSpring.model.SerieData;
import br.com.screenMatchWSpring.model.SeasonData;
import br.com.screenMatchWSpring.service.ApiUsage;
import br.com.screenMatchWSpring.service.ConvertData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ApiUsage usage = new ApiUsage();
    private ConvertData converter = new ConvertData();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e74a392d";

    public void showMenu() {
        System.out.println("Type the name of the series to search");
        var nameSerie = scanner.nextLine();
        var json = usage.getData(ADDRESS + nameSerie.replace(" " , "+") + API_KEY);
        SerieData data = converter.getData(json, SerieData.class);
        System.out.println(data);

        List<SeasonData> seasons = new ArrayList<>();

		for(int i = 1; i <= data.totalSeasons(); i++) {
			json = usage.getData(ADDRESS + nameSerie.replace(" " , "+") + "&season=" + i + API_KEY);
			SeasonData seasonData = converter.getData(json, SeasonData.class);
			seasons.add(seasonData);
		}
		seasons.forEach(System.out::println);

        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        List<EpisodeData> episodeData = seasons.stream()
                .flatMap(t -> t.episodes().stream())
                .toList();

        System.out.println("\nTop 5 episodes:");

        episodeData.stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::imdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.season(), d))
                ).toList();

        episodes.forEach(System.out::println);

        System.out.println("From what year do you want to see the episodes?");
        var year = scanner.nextInt();
        scanner.nextLine();

        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodes.stream()
                .filter(e -> e.getReleasedData() != null && e.getReleasedData().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Season: " + e.getSeason() +
                                " Episode: " + e.getTitle() +
                                " Released data: " + e.getReleasedData().format(formatter)
                ));

    }
}
