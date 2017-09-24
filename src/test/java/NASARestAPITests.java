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

    /**
     * This test should compare first 10 images and metadata made by "Curiosity" on 1000 Mars sol
     * and same 10 Mars photos made by "Curiosity" on earth date that is equals 1000 Mars sol.
     *
     */
    @Test
    public void isMetaDataTheSameForEarthDateAndSol() {
        List<Photos> listOfPhotosByEarthDate = curiosityController.getListOfPhotosByEarthDate(NUMBER_TEN);
        List<Photos> listOfPhotosBySolDate = curiosityController.getListOfPhotosBySolDate(NUMBER_TEN);
        Assert.assertThat("List differ", listOfPhotosByEarthDate, is(listOfPhotosBySolDate));
    }

    /**
     * Additional task
     * This test should verify that each separate camera made 10 times more images than any other camera,
     * if so, then test should fail.
     *
     */
    @Test
    public void verifyIfAnyCameraMadeMoreImages() {
        int max = curiosityController.getMaxQuantityOfPhotosMadeByCamera();
        int min = curiosityController.getMinQuantityOfPhotosMadeByCamera();
        Assert.assertTrue("Camera made 10 times more images than other", max / min < NUMBER_TEN);
    }
}
