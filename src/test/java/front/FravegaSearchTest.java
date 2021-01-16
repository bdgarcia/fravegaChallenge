package front;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import front.page_objects.HomePage;

import java.util.List;

public class FravegaSearchTest extends TestBase {

    @Test
    public void testFravegaSearch() throws Exception{
        driver.get("https://www.fravega.com/");

        HomePage homePage = new HomePage(driver);
        homePage.searchItem("Heladera");
        homePage.filterByHeladeras();
        String firstbrand = homePage.filterByFirstBrand();
        List<WebElement> currentElements = homePage.getCurrentResults();
        currentElements.forEach((element) -> {
            Assert.assertTrue(element.findElement(By.cssSelector("h4[class*=PieceTitle]")).getText().contains(firstbrand));
        });
        Assert.assertTrue(currentElements.size() == homePage.getResultsCount());
        Assert.assertTrue(driver.findElement(By.cssSelector("ul[class*=breadcrumbstyled__List]")).getText().contains("Heladeras"));
    }
}


