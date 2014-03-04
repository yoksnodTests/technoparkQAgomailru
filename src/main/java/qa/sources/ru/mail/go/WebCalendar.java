package qa.sources.ru.mail.go;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WebCalendar {

	private static final String CURRENT_MONTH_DAY_CSS_SELECTOR = ".calendar__currentday .calendar__day-content";
	private static final String CURRENT_YEAR_CSS_SELECTOR = "span.calendar__year-current";
	private static final String CURRENT_YEAR_DAYS_COUNT_CSS_SELECTOR = ".calendar__day:not(.calendar__day_other-month)";
	private static final String SHOW_DROP_DOWN_VIEW_ID = "calExpand";
	private static final String DISMISS_DROP_DOWN_VIEW_ID = "calCloseButton";
	private static final String YEAR_LIST_ITEM_TAG = "li";
	private static final String CALENDAR_CONTAINER_ID = "calBlock";
	private static final String MONTH_COUNT_CSS_SELECTOR = "table.calendar__month";

	private final WebDriver driver;

	public WebCalendar(WebDriver driver) {
		this.driver = driver;
	}

	public int getCurrentMonthDay() {
		WebElement currentDay = driver.findElement(By
				.cssSelector(CURRENT_MONTH_DAY_CSS_SELECTOR));
		return Integer.valueOf(currentDay.getText());
	}

	public int getCurrentYear() {
		WebElement currentYear = driver.findElement(By
				.cssSelector(CURRENT_YEAR_CSS_SELECTOR));
		return Integer.valueOf(currentYear.getText());
	}

	public int getCurrentYearDaysCount() {
		return driver.findElements(
				By.cssSelector(CURRENT_YEAR_DAYS_COUNT_CSS_SELECTOR)).size();
	}

	public int getPreviousYear() {
		WebElement currentYear = driver.findElement(By
				.cssSelector(CURRENT_YEAR_CSS_SELECTOR));
		currentYear.click();
		List<WebElement> items = currentYear.findElements(By
				.tagName(YEAR_LIST_ITEM_TAG));
		WebElement prevYear = items.get(2);
		return Integer.valueOf(prevYear.getText());
	}

	public int getMonthCount() {
		return driver.findElements(By.cssSelector(MONTH_COUNT_CSS_SELECTOR))
				.size();
	}

	public Dimension getViewSize() {
		return driver.findElement(By.id(CALENDAR_CONTAINER_ID)).getSize();
	}

	public void showDropDownContainer() {
		driver.findElement(By.id(SHOW_DROP_DOWN_VIEW_ID)).click();
	}

	public void dismissDropDownContainer() {
		driver.findElement(By.id(DISMISS_DROP_DOWN_VIEW_ID)).click();
	}
}
