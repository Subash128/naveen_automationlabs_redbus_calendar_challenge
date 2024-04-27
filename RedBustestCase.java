package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedBustestCase {

	private static ChromeDriver driver;

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();

		option.addArguments("--disable-notifications");
		// option.addArguments("headless");
		driver = new ChromeDriver(option);

		driver.manage().window().maximize();

		driver.get("https://www.redbus.in/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//div[@class='labelCalendarContainer']")).click();

		List<String> weekenddays = getWeekendDates("Apr", "2025");

		System.out.println(weekenddays);

		driver.quit();

	}

	static List<String> getWeekendDates(String month, String year) {

		try {
			getmonth(month, year);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		List<String> day = new ArrayList<String>();

		List<WebElement> days = driver
				.findElements(By.xpath("//span[@class='DayTiles__CalendarDaysSpan-sc-1xum02u-1 bwoYtA']"));

		for (WebElement webElement : days) {
			String weekends = webElement.getText();
			day.add(weekends);
		}
		return day;
	}

	static void getmonth(String month, String year) throws InterruptedException {

		String actualmonth = null;

		WebElement Calcontent = driver
				.findElement(By.xpath("(//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[2]"));

		WebElement nxt = driver.findElement(By.xpath("(//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[3]"));

		actualmonth = Calcontent.getText();

		System.out.println(actualmonth);

		String[] mon = actualmonth.split(" ");

		String exp = mon[1].substring(0, 4);

		while (!((mon[0].contains(month)) && (exp.contains(year)))) {

			nxt.click();
			Thread.sleep(1000);
			actualmonth = Calcontent.getText();
			mon = actualmonth.split(" ");
			exp = mon[1].substring(0, 4);

			System.out.println(actualmonth);
		}

	}

}
