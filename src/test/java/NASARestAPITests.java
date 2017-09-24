import Controller.CuriosityController;
import Models.Photos;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import java.util.*;

public class NASARestAPITests {

    private static final int NUMBER_TEN = 10;

    private CuriosityController curiosityController;

    @Before
    public void setUp() {
        curiosityController = new CuriosityController();
    }

    @Test
    public void isMetaDataTheSameForEarthDateAndSol() {
        List<Photos> listOfPhotosByEarthDate = curiosityController.getListOfPhotosByEarthDate(NUMBER_TEN);
        List<Photos> listOfPhotosBySolDate = curiosityController.getListOfPhotosBySolDate(NUMBER_TEN);
        Assert.assertThat("List differ", listOfPhotosByEarthDate, is(listOfPhotosBySolDate));
    }

    @Test
    public void verifyIfAnyCameraMadeMoreImages() {
        int max = curiosityController.getMaxQuantityOfPhotosMadeByCamera();
        int min = curiosityController.getMinQuantityOfPhotosMadeByCamera();
        Assert.assertTrue("Camera made 10 times more images than other", max / min < NUMBER_TEN);
    }
}
