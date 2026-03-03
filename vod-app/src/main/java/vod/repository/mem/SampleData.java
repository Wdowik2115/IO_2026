package vod.repository.mem;

import vod.model.Director;
import vod.model.Movie;
import vod.model.Theatre;
import java.util.ArrayList;
import java.util.List;

class SampleData {
    static List<Theatre> theatres = new ArrayList<>();
    static List<Director> directors = new ArrayList<>();
    static List<Movie> movies = new ArrayList<>();

    static {
        Director dejmek = new Director(1, "Kazimierz", "Dejmek");
        Director jarocki = new Director(2, "Jerzy", "Jarocki");
        Director warlikowski = new Director(3, "Krzysztof", "Warlikowski");
        Director lupa = new Director(4, "Krystian", "Lupa");

        Movie dziady = new Movie(1, "Dziady", "https://example.com/dziady.jpg", dejmek, (float) 4.8);
        Movie rewizor = new Movie(2, "Rewizor", "https://example.com/rewizor.jpg", dejmek, (float) 4.5);
        Movie hamlet = new Movie(3, "Hamlet", "https://example.com/hamlet.jpg", jarocki, (float) 4.7);
        Movie makbet = new Movie(4, "Makbet", "https://example.com/makbet.jpg", jarocki, (float) 4.3);
        Movie tramwaj = new Movie(5, "Tramwaj zwany pożądaniem", "https://example.com/tramwaj.jpg", warlikowski, (float) 4.6);
        Movie krum = new Movie(6, "Krum", "https://example.com/krum.jpg", warlikowski, (float) 4.2);
        Movie lunatycy = new Movie(7, "Lunatycy", "https://example.com/lunatycy.jpg", lupa, (float) 4.9);
        Movie miasteczko = new Movie(8, "Miasteczko", "https://example.com/miasteczko.jpg", lupa, (float) 4.4);

        bind(dziady, dejmek);
        bind(rewizor, dejmek);
        bind(hamlet, jarocki);
        bind(makbet, jarocki);
        bind(tramwaj, warlikowski);
        bind(krum, warlikowski);
        bind(lunatycy, lupa);
        bind(miasteczko, lupa);

        Theatre narodowy = new Theatre(1, "Teatr Narodowy", "https://teatrnarodowy.pl/logo.png");
        Theatre wybrzeze = new Theatre(2, "Teatr Wybrzeże", "https://teatrwybrzeze.pl/logo.png");
        Theatre stary = new Theatre(3, "Teatr Stary", "https://teatrstary.pl/logo.png");
        Theatre wspolczesny = new Theatre(4, "Teatr Współczesny", "https://wspolczesny.pl/logo.png");

        bind(narodowy, dziady);
        bind(narodowy, rewizor);
        bind(stary, dziady);
        bind(stary, hamlet);
        bind(wybrzeze, tramwaj);
        bind(wybrzeze, krum);
        bind(wspolczesny, makbet);
        bind(wspolczesny, lunatycy);

        movies.add(dziady); movies.add(rewizor); movies.add(hamlet); movies.add(makbet);
        movies.add(tramwaj); movies.add(krum); movies.add(lunatycy); movies.add(miasteczko);

        directors.add(dejmek); directors.add(jarocki); directors.add(warlikowski); directors.add(lupa);

        theatres.add(narodowy); theatres.add(wybrzeze); theatres.add(stary); theatres.add(wspolczesny);
    }

    private static void bind(Theatre t, Movie m) {
        t.addMovie(m);
        m.addTheatre(t);
    }

    private static void bind(Movie m, Director d) {
        d.addMovie(m);
        m.setDirector(d);
    }
}
