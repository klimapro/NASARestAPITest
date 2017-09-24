package Controller;

import Models.Document;
import Models.Photos;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CuriosityController {

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity";
    private static final String EARTH_DATE = "/photos?earth_date=2015-5-30";
    private static final String SOL_DATE = "/photos?sol=1000";
    private static final String API_KEY = "&api_key=xBEmFwpvUHMdsL2iw9UNCTaPBHtfJyllBI3elnD4";
    private static final String CAMERA_NAME = "&camera=";
    private static final String[] CAMERAS_LIST = {"FHAZ", "NAVCAM", "MAST", "CHEMCAM", "MAHLI", "MARDI", "RHAZ"};
    private static final Integer NUMBER_ONE = 1;

    private RestTemplate restTemplate;

    public CuriosityController() {
        restTemplate = new RestTemplate();
    }

    public List<Photos> getListOfPhotosByEarthDate(int number) {
        return restTemplate.getForEntity(BASE_URL + EARTH_DATE + API_KEY, Document.class).getBody().getPhotos().stream().limit(number).collect(Collectors.toList());
    }

    public List<Photos> getListOfPhotosBySolDate(int number) {
        return restTemplate.getForEntity(BASE_URL + SOL_DATE + API_KEY, Document.class).getBody().getPhotos().stream().limit(number).collect(Collectors.toList());
    }

    public List<Photos> getListOfPhotosMadeByCamera(String cameraName) {
        return restTemplate.getForEntity(BASE_URL + SOL_DATE + API_KEY + CAMERA_NAME + cameraName, Document.class).getBody().getPhotos().stream().collect(Collectors.toList());
    }

    public int getMaxQuantityOfPhotosMadeByCamera() {
        return Arrays.stream(getQuantityOfAllPhotosMadeByCamera()).max().getAsInt() + NUMBER_ONE;
    }

    public int getMinQuantityOfPhotosMadeByCamera() {
        return Arrays.stream(getQuantityOfAllPhotosMadeByCamera()).min().getAsInt() + NUMBER_ONE;
    }

    public int[] getQuantityOfAllPhotosMadeByCamera() {
        int[] quantity = new int[CAMERAS_LIST.length];
        for (int i = 0; i < CAMERAS_LIST.length; i++) {
            quantity[i] = getListOfPhotosMadeByCamera(CAMERAS_LIST[i]).size();
        }
        return quantity;
    }
}
