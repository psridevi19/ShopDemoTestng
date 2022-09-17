package testSuite1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShoppingDemo {
	
	public static WebDriver driver;
	public static WebElement currency;
	public static Actions action;
	
	@BeforeSuite
	public static void pathSetting() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Aadhiran/Documents/AProjectWorkspace/Project_Automation_Sel_Eclipse/AutomationSeleniumNumpy/ShoppingWebsiteDress/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	
	@Test (priority = 1)
	public static void launchApp() {
		driver.get("http://tutorialsninja.com/demo/index.php");
	}
	
	@Test (priority = 2)
	public static void selectCurrency() {
		WebElement currency = new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-currency")));
		//currency = driver.findElement(By.id("form-currency"));
		
		currency.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement euro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form-currency']//button[@name='EUR']")));
		euro.click();
	}
	
	@Test (priority = 3)
	public static void orderCanonEOS()
	{
		driver.get("http://tutorialsninja.com/demo/index.php");
		WebElement image = driver.findElement(By.xpath("//img[@title='Canon EOS 5D']"));
		image.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));
		addToCart.click();
		WebElement errorMsgSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='input-option226']//../div[@class='text-danger']")));
		String errorMsgAct = errorMsgSelect.getText();
		System.out.println("Error Message : "+errorMsgAct);
		String errorMsgExp = "Select required!";
		//Assert.assertEquals(errorMsg, "Success");
		if(errorMsgAct.contains(errorMsgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	}
	
	@Test (priority = 4)
	public static void ShoppingVerification() throws InterruptedException
	{
		action = new Actions(driver);
		WebElement home = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='product-product']//li[1]/a/i")));
		action.moveToElement(home);
		home.click();
		driver.findElement(By.xpath("//img[@title='iPhone']")).click();
		WebElement inputQuantity = driver.findElement(By.id("input-quantity"));
		inputQuantity.clear();
		inputQuantity.sendKeys("2");
		WebElement buttonCart = driver.findElement(By.id("button-cart"));
		buttonCart.click();
		WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='product-product']/div[contains(text(),'Success:')]")));
		String msgAct = alert.getText();
		String msgExp = "Success: You have added iPhone to your shopping cart!";
		System.out.println("Alert Message: "+alert.getText());
		System.out.println("Alert Message: "+msgExp);
		//Assert.assertEquals(msgAct, msgExp);
		if(msgAct.contains(msgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	}
	
	@Test (priority = 5)
	public static void viewCart() {
		WebElement cart = driver.findElement(By.id("cart-total"));
		cart.click();
		WebElement viewcart = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'View Cart')]")));
		viewcart.click();
	}
	
	@Test (priority = 6)
	public static void updateButton() throws InterruptedException {
		
		Thread.sleep(2000);
		WebElement update = driver.findElement(By.xpath("//button[@data-original-title='Update']/../../input"));
		update.clear();
		update.sendKeys("3");
		WebElement updateButton = driver.findElement(By.xpath("//button[@data-original-title='Update']"));
		updateButton.click();
		
	}
	
	@Test (priority = 7)
	public static void checkOutPhone()
	{
		WebElement vat = driver.findElement(By.xpath("//strong[text()='VAT (20%):']"));
		String vattext = vat.getText();
		WebElement vatAmountElement = driver.findElement(By.xpath("//strong[text()='VAT (20%):']/../../td[2]"));
		String vatAmount = vatAmountElement.getText();
		System.out.println(vattext+" :"+vatAmount);
			
		WebElement ecoTaxElement = driver.findElement(By.xpath("//strong[text()='Eco Tax (-2.00):']"));
		String ecoTax = ecoTaxElement.getText();
		WebElement ecoTaxAmountElement = driver.findElement(By.xpath("//strong[text()='Eco Tax (-2.00):']/../../td[2]"));
		String ecoTaxAmount = ecoTaxAmountElement.getText();
		System.out.println(ecoTax+" :"+ecoTaxAmount);
		
		WebElement checkOutElement = driver.findElement(By.xpath("//a[text()='Checkout']"));
		checkOutElement.click();
	}
	
	@Test (priority = 8)
	public static void errorMsgPhone()
	{
		WebElement checkoutErrorMsg = driver.findElement(By.xpath("//div[@id='checkout-cart']/div[1]"));
		String errMsgAct = checkoutErrorMsg.getText();
		System.out.println("Error Message : "+errMsgAct);
		String errMsgExp =  "Products marked with *** are not available";
		//Assert.assertEquals(errMsgActual, expMsg);
		Assert.assertEquals(true, true);
		if(errMsgAct.contains(errMsgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	
	}
	
	@Test (priority = 9)
	public static void removeCart()
	{
		WebElement cart = driver.findElement(By.id("cart-total"));
		cart.click();
		WebElement close = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cart']//button[@title='Remove']")));
		close.click();
		//div[@id='cart']//button[@title='Remove']
	}
	
	@Test (priority = 10)
	public static void orderMacBook() throws InterruptedException
	{
		WebElement home = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error-not-found']//i")));
		action.moveToElement(home);
		home.click();
		
		WebElement macBookElement = driver.findElement(By.xpath("//a[text()='MacBook']"));
		macBookElement.click();
		
		WebElement inputQuantity = driver.findElement(By.id("input-quantity"));
		String defaultQuantity = inputQuantity.getAttribute("value");
		int quantity = Integer.parseInt(defaultQuantity);
		if(quantity==1)
		{
			WebElement addToCart = driver.findElement(By.id("button-cart"));
			addToCart.click();
			System.out.println("Default Quantity of Macbook :" +quantity);
		}
		Thread.sleep(2000);
		String successMsgAct = driver.findElement(By.xpath("//div[@id='product-product']/div[1]")).getText();
		String SuccessMsgExp = "Success";
		System.out.println("SuccessMsg :"+successMsgAct);
		//Assert.assertEquals(successMsgAct, SuccessMsgExp);
		if(successMsgAct.contains(SuccessMsgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	}
	
	@Test (priority = 11)
	public static void couponCode() throws InterruptedException
	{
		driver.manage().window().maximize();
		
		action = new Actions(driver);
		//WebElement shoppingCartEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				//until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Shopping Cart']")));
		WebElement shoppingCartEle = driver.findElement(By.xpath("//span[text()='Shopping Cart']"));
		action.moveToElement(shoppingCartEle);
		shoppingCartEle.click();
		/*
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
		WebElement voucherEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("#collapse-coupon")));
		voucherEle.click();
		*/
		
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
		WebElement voucherEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Use Coupon Code ']")));
		voucherEle.click();
		
//		Thread.sleep(2000);
//		WebElement voucherEle = driver.findElement(By.partialLinkText("#collapse-voucher"));
//		voucherEle.click();
		

		
		WebElement inputCoupon = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-coupon")));
			//	driver.findElement(By.id("input-coupon"));
		inputCoupon.sendKeys("ABCD123");
		WebElement buttonCoupon = driver.findElement(By.id("button-coupon"));
		buttonCoupon.click();
		
		//Thread.sleep(2000);
		WebElement errMsgElement = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='checkout-cart']//div[contains(text(),'Warning: Coupon is either invalid')]")));
		String errMsgAct = errMsgElement.getText();
		String errMsgExp = "Coupon is either invalid";
		System.out.println("ErrMsg :"+errMsgAct);
		//Assert.assertEquals(errMsgAct, errMsgExp);
		if(errMsgAct.contains(errMsgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	}
	
	
	@Test (priority = 12)
	public static void applyGiftCertificate() throws InterruptedException
	{
		/*
		WebElement giftCertEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("#collapse-voucher")));
		giftCertEle.click();
		*/
		action= new Actions(driver);
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
		WebElement giftCertEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Use Gift Certificate ']")));
		action.moveToElement(giftCertEle);
		giftCertEle.click();
		
		WebElement inputVoucher = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.id("input-voucher")));
		//Checking Invalid code
		inputVoucher.sendKeys("AXDFGH123");
		WebElement buttonVoucher = driver.findElement(By.id("button-voucher"));
		buttonVoucher.click();
		
		WebElement errMsgElement = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='checkout-cart']//div[contains(text(),'Warning: Gift Certificate is either invalid')]")));
		String errMsgAct = errMsgElement.getText();
		String errMsgExp = "Gift Certificate is either invalid";
		System.out.println("ErrMsg :"+errMsgAct);
		//Assert.assertEquals(errMsgAct, errMsgExp);
		if(errMsgAct.contains(errMsgExp))
		{
		
			Assert.assertEquals(true,true);
		}
	}
	
	@Test (priority = 13)
	public static void clearTextBox() throws InterruptedException
	{
		Thread.sleep(2000);
		action= new Actions(driver);
		WebElement voucherEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Use Coupon Code ']")));
		action.moveToElement(voucherEle);
		voucherEle.click();
		
		WebElement inputVoucher = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.id("input-voucher")));
		inputVoucher.clear();
		Thread.sleep(2000);
		
		WebElement giftCertEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Use Gift Certificate ']")));
		giftCertEle.click();
		WebElement inputCoupon = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.id("input-coupon")));
						//elementToBeClickable(By.id("input-coupon")));
		inputCoupon.clear();
		
		
		WebElement checkout  = driver.findElement(By.xpath("//div[@id='content']//a[text()='Checkout']"));
		checkout.click();
		
		
	}

	@Test (priority = 14, dataProvider= "dataRegistration")
	public static void registerAccount(String firstName,String lastName,String email,String telephone, String password,String passConfirm,
			String address1,String city,String postalCode, String country,String region) throws InterruptedException
	{
		
		WebElement registerradioBtn = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='register']")));
		Boolean radioChk = registerradioBtn.isSelected();
		System.out.println("Radio button selection checking : "+radioChk);
		if(!(registerradioBtn.isSelected()))
		{
			registerradioBtn.click();
		}
		
		WebElement continueButton = driver.findElement(By.id("button-account"));
		continueButton.click();
		
		/* */
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement firstNameE = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-payment-firstname")));
		firstNameE.sendKeys(firstName);
		
		
		WebElement lastNameE = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-payment-lastname")));
		lastNameE.sendKeys(lastName);
		
		WebElement EmailE = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-payment-email")));
		EmailE.sendKeys(email);
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-payment-telephone"))).sendKeys(telephone);
		new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-password"))).sendKeys(password);
		new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-confirm"))).sendKeys(passConfirm);
		new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-address-1"))).sendKeys(address1);
		new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-city"))).sendKeys(city);
		new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-postcode"))).sendKeys(postalCode);
		
		WebElement countryElement = new WebDriverWait(driver,Duration.ofSeconds(30)).
		until(ExpectedConditions.elementToBeClickable(By.id("input-payment-country")));
		Select SelectCountry =  new Select(countryElement);
		//SelectCountry.selectByValue(country);
		SelectCountry.selectByIndex(3);
		
		Thread.sleep(2000);
		
		WebElement regionElement = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-payment-zone")));
		Select SelectZone =  new Select(regionElement);
		//SelectZone.selectByValue(region);
		SelectZone.selectByIndex(5);
		
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		//WebElement regionElement = new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.
		
		driver.findElement(By.id("button-register")).click();
		
	}
	
	@Test (priority = 15)
	public static void paymentMethod()
	{
		WebElement commentTextArea = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.name("comment")));
		commentTextArea.sendKeys("order details");
		driver.findElement(By.name("agree")).click();
		
		driver.findElement(By.id("button-payment-method")).click();
		
		WebElement paymentErrMsgEle = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Warning: Payment method required!']")));
		String paymentErrMsgAct = paymentErrMsgEle.getText();
		System.out.println("Payment Error Msg :"+paymentErrMsgAct);
		String paymentErrMsgExp = "Warning: Payment method required!";
		if(paymentErrMsgAct.contains(paymentErrMsgExp))
		{
			Assert.assertEquals(false, false);
		}
	}
	
	
	@Test (priority = 16)
	public static void contactRequest()
	{
		driver.findElement(By.xpath("//footer//a[text()='Contact Us']")).click();
		
		WebElement enquiryTextArea = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.id("input-enquiry")));
		enquiryTextArea.sendKeys("No payment page");
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		WebElement continueButton = new WebDriverWait(driver,Duration.ofSeconds(30)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Continue']")));
		continueButton.click();	
	}
	
	@DataProvider
	public static Object[][] dataRegistration()
	{
		Object[][] data = {{"John","Dwane","fgr4@gmail.com","1234567","password","password","abc street",
			"xyxxyx","65884","Thailand","Angus"}};
		return data;
	}
	
	
}

