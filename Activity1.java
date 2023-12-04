import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.util.Scanner;

public class Activity1 {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final List<String> favoriteCities = new ArrayList<>();

    // according to weatherAPI website, they use endpoints like this:
    // https://api.openweathermap.org/data/3.0/weather?q=cityExample&appid=ApiKey
    public String getWeatherDetails(String city) {
        String link = "http://api.openweathermap.org/data/3.0/weather?q=" + city + "&appid=ApiKey";
        // generate the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retriving response";
        }
    }

    public String addACityToFavorites(String city) {
        // make sure list is still less than 3
        if (favoriteCities.size() < 3) {
            if (favoriteCities.contains(city)) {
                return city + " is already in favorites.";

            } else {
                favoriteCities.add(city);
                return city + " added " + city + " to favorites.";
            }
        } else {
            return "Cant add any more cities to favorite.";
        }
    }

    public String listFavoriteCities() {
        if (favoriteCities.isEmpty()) {
            return "Favorite list is empty.";
        }
        String result = "";
        for (String city : favoriteCities) {
            result += city + ": " + getWeatherDetails(city);
        }
        return result;
    }

    // function that take 2 cities and replace the old one with a new one
    public String updateCityinFavorites(String oldCity, String newCity) {
        if (favoriteCities.contains(oldCity)) {
            favoriteCities.remove(oldCity);
            if (favoriteCities.contains(newCity)) {
                return newCity + " is already in favorites.";
            } else {
                favoriteCities.add(newCity);
                return oldCity + " is replaced with " + newCity + " in favorites.";
            }
        } else {
            return oldCity + " is not in favorites.";
        }
    }

    public static void main(String[] args) {
        Activity1 activity = new Activity1();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Weather Application");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Get weather details of a city");
            System.out.println("2. Add a city to favorites");
            System.out.println("3. List favorite cities with details");
            System.out.println("4. Update favorite city");
            System.out.println("5. End program");
            System.out.print("Choose a number of the options: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.println(activity.getWeatherDetails(city));
                    break;
                case "2":
                    System.out.print("Enter city to add: ");
                    String cityToAdd = scanner.nextLine();
                    System.out.println(activity.addACityToFavorites(cityToAdd));
                    break;
                case "3":
                    System.out.println(activity.listFavoriteCities());
                    break;
                case "4":
                    System.out.print("Enter city to remove: ");
                    String oldCity = scanner.nextLine();
                    System.out.print("Now enter city to add: ");
                    String newCity = scanner.nextLine();
                    System.out.println(activity.updateCityinFavorites(oldCity, newCity));
                    break;
                case "5":
                    System.out.println("Program ended");
                    scanner.close();
                    return;
                default:
                    System.out.println("Please choose one of the options.");
            }
        }
    }

}
