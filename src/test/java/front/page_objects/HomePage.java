package front.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "input[class*=InputBar__SearchInput]" )
    private WebElement searchBox;

    @FindBy(css = "form[class*=InputBar__InputWrapper] button" )
    private WebElement searchButton;

    @FindBy(xpath = "//h4[@name=\"subcategoryAggregation\"][contains(text(),'Heladeras')]" )
    private WebElement filterHeladeras;

    @FindBy(css = "li[name=brandAggregation]" )
    private List<WebElement> brands;

    public void searchItem(String item){
        searchBox.sendKeys(item);
        searchButton.click();
    }

    public void filterByHeladeras(){
        filterHeladeras.click();
    }

    public String filterByFirstBrand(){
        brands.get(0).click();
        String firstBrand = brands.get(0).findElement(By.cssSelector("label")).getText();
        String[] _arr = firstBrand.split("\\s");
        return _arr[0];
    }

    public List<WebElement> getCurrentResults() {
       return driver.findElements(By.cssSelector("div[class*=ProductCard__Card]"));
    }

    public Integer getResultsCount() {
        return driver.findElements(By.cssSelector("div[class*=ProductCard__Card]")).size();
    }
}
