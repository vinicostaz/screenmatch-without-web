package br.com.screenMatchWSpring;

import br.com.screenMatchWSpring.model.SerieData;
import br.com.screenMatchWSpring.service.ApiUsage;
import br.com.screenMatchWSpring.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchWSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchWSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiUsage = new ApiUsage();
		var json = apiUsage.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=e74a392d");
//		System.out.println(json);
//		json = apiUsage.getData("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConvertData converter = new ConvertData();
		SerieData data = converter.getData(json, SerieData.class);
		System.out.println(data);

	}
}
