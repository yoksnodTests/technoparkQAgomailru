package qa.sources.ru.mail.go;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarCreator {

	private static final String SEARCH_VIEW_ID = "q";
	private static final String SEARCH_BTN_CLASS = "go-form__submit";
	private static final String SEARCH_WORD_CP1251 = "календарь";

	private static CalendarCreator creator;

	private CalendarCreator() {
	}

	public static CalendarCreator getInstance() {
		if (creator == null) {
			creator = new CalendarCreator();
		}

		return creator;
	}

	public WebCalendar getCalendar(WebDriver driver) {
		preparePage(driver);
		return new WebCalendar(driver);
	}

	private void preparePage(WebDriver driver) {
		WebElement searchView = driver.findElement(By.id(SEARCH_VIEW_ID));
		searchView.sendKeys(SEARCH_WORD_CP1251);
		WebElement searchBtn = driver.findElement(By
				.className(SEARCH_BTN_CLASS));
		searchBtn.click();
	}

}
