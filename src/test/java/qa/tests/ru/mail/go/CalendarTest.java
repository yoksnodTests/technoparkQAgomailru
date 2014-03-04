package qa.tests.ru.mail.go;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import qa.sources.ru.mail.go.CalendarCreator;
import qa.sources.ru.mail.go.WebCalendar;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class CalendarTest {

	private static final int MONTHES_COUNT = 12;
	private static final String CHROME = "chrome";
	private static final String MOZILLA = "firefox";

	private WebDriver driver;

	@Parameters({ "browser", "hub", "url" })
	@BeforeMethod
	public void setUp(String browser, String hub, String url)
			throws MalformedURLException {

		URL hostName = new URL(hub);
		if (browser.equals(CHROME)) {
			driver = new RemoteWebDriver(hostName, DesiredCapabilities.chrome());
		} else if (browser.equals(MOZILLA)) {
			driver = new RemoteWebDriver(hostName,
					DesiredCapabilities.firefox());

		} else {
			throw new IllegalArgumentException();
		}
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test
	public void currentYearTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final int webYear = calendar.getCurrentYear();
		final int nativeYear = Calendar.getInstance().get(Calendar.YEAR);
		Assert.assertEquals(nativeYear, webYear);
	}

	@Test
	public void currentMonthDayTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final int webCurrentDay = calendar.getCurrentMonthDay();
		final int nativeCurrentDay = Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH);
		Assert.assertEquals(nativeCurrentDay, webCurrentDay);
	}

	@Test
	public void previousYearTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final int webPrevYear = calendar.getPreviousYear();
		final int nativePrevYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		Assert.assertEquals(nativePrevYear, webPrevYear);
	}

	@Test
	public void monthCountTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final int webMonthCount = calendar.getMonthCount();
		final int nativeMonthCount = MONTHES_COUNT;
		Assert.assertEquals(nativeMonthCount, webMonthCount);
	}

	@Test
	public void yearDaysCountTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final int daysWeb = calendar.getCurrentYearDaysCount();
		final int daysCurYear = Calendar.getInstance().getActualMaximum(
				Calendar.DAY_OF_YEAR);
		Assert.assertEquals(daysCurYear, daysWeb);
	}

	@Test
	public void dropDownCalendarTest() {
		WebCalendar calendar = CalendarCreator.getInstance()
				.getCalendar(driver);
		final Dimension startDim = calendar.getViewSize();

		calendar.showDropDownContainer();
		final Dimension dropDownDim = calendar.getViewSize();
		Assert.assertTrue(startDim.height < dropDownDim.height);

		calendar.dismissDropDownContainer();
		Dimension endDim = calendar.getViewSize();
		Assert.assertEquals(startDim, endDim);
	}
}
