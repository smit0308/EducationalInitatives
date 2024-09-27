import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature, float humidity);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setWeather(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }
}

class PhoneApp implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Phone App: Weather Updated - Temperature: " + temperature + ", Humidity: " + humidity);
    }
}

class WebDashboard implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Web Dashboard: Weather Updated - Temperature: " + temperature + ", Humidity: " + humidity);
    }
}

public class observerpattern {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        Observer phoneApp = new PhoneApp();
        Observer webDashboard = new WebDashboard();

        station.addObserver(phoneApp);
        station.addObserver(webDashboard);

        station.setWeather(25.5f, 65.0f); 
    }
}
