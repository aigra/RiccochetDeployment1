package edu.brown.cs.student.SeleniumTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class UITest {

  WebDriver myDriver;

  @BeforeAll
  static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setupTest() {
    this.myDriver = new ChromeDriver();
  }

  @AfterEach
  void teardown() {
    this.myDriver.quit();
  }

  @Test
  public void usernameTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement usernameInput = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("user")));
    assertNotEquals(usernameInput, null);
  }

  @Test
  public void codeTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement codeInput = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("code")));
    assertNotEquals(codeInput, null);
  }

  @Test
  public void hostTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement hostButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("host")));
    assertNotEquals(hostButton, null);
  }

  @Test
  public void joinTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement joinButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("join")));
    assertNotEquals(joinButton, null);
  }

  @Test
  public void lobbyTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement hostButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("host")));
    WebElement userInput = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("user")));
    userInput.click();
    userInput.sendKeys("name");
    hostButton.click();
    WebElement lobbyList = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("lobbyList")));
    assertTrue(lobbyList.getText().contains("name"));
  }

  @Test
  public void leaveButtonTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement hostButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("host")));
    WebElement userInput = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("user")));
    userInput.click();
    userInput.sendKeys("name");
    hostButton.click();
    WebElement leaveButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("leave")));
    assertNotEquals(leaveButton, null);
  }

  @Test
  public void startButtonTest() {
    this.myDriver.get("http://localhost:3000/");
    WebElement hostButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("host")));
    WebElement userInput = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("user")));
    userInput.click();
    userInput.sendKeys("name");
    hostButton.click();
    WebElement startButton = new WebDriverWait(this.myDriver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("start")));
    assertNotEquals(startButton, null);
  }

}
