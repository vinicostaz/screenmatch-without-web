package br.com.screenMatchWSpring.main;

import br.com.screenMatchWSpring.model.EpisodeData;
import br.com.screenMatchWSpring.model.SerieData;
import br.com.screenMatchWSpring.model.SeasonData;
import br.com.screenMatchWSpring.service.ApiUsage;
import br.com.screenMatchWSpring.service.ConvertData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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


    }
}
