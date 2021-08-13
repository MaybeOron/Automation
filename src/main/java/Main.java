import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user name:");
        String userName = scanner.nextLine();
        System.out.print("Enter your user password:");
        String password = scanner.nextLine();

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.aac.ac.il/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement infoButtons = driver.findElement(By.className("info-btn"));
        if (infoButtons != null) {
            WebElement personalSpace = infoButtons.findElement(By.tagName("a"));
            driver.get(personalSpace.getAttribute("href"));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement userNameInput = driver.findElement(By.id("Ecom_User_ID"));
        if (userNameInput == null) {
            System.out.println("name filed not found");
        }
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
        passwordInput.sendKeys(password);

        driver.findElement(By.id("wp-submit")).click();

        driver.get("https://moodle.aac.ac.il/login/index.php");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> courses = driver.findElements(By.className("course-summaryitem"));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> titles = courses.stream().map(e -> e.findElement(By.tagName("h6")).getText()).collect(Collectors.toList());
        System.out.println(" -- Your courses: ");
        int i = 0;
        for (String title : titles) {
            System.out.println(title + " ." + (i + 1));
            i++;
        }
        System.out.print("Enter course number: ");
        int courseNumInput = scanner.nextInt();
        courses.get(courseNumInput - 1).findElement(By.tagName("a")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.cssSelector(".dropdown-menu .dropdown-item:last-of-type")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("menu-top-header")).findElements(By.tagName("li")).get(1).click();
    }
}
