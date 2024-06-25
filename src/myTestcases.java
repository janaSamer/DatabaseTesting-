import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class myTestcases {
	
	Connection con ;
	
	Statement stmt;
	
	ResultSet rs;
	
	WebDriver driver =new ChromeDriver();
	
	String url="https://magento.softwaretestingboard.com/customer/account/create/";
	
	
	
@BeforeTest
public void setUp() throws SQLException {
	
	 con =DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "jooj");
	
}


//**************///
@Test (priority=1)
public void addData() throws SQLException {
	 
	String Query="INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,country) "+"values(77,'ABC','JANA','samer','123-45678','amman','amman','jordan')";
	
	stmt =con.createStatement();
	
	int insertedRow=stmt.executeUpdate(Query);
	
	Assert.assertEquals(insertedRow>0, true);
	
}



//**************///
@Test (priority=2)
public void updatData() throws SQLException {
	
	String Query="update customers set customerName='automationGroup'  where customerNumber = 77";
	
	stmt=con.createStatement();
	
	int updatedRow=stmt.executeUpdate(Query);
	
	Assert.assertEquals(updatedRow>0, true);
	
	
}


//**************///
@Test(priority = 3)
public void getData() throws SQLException {
	

	stmt = con.createStatement();
	
	rs = stmt.executeQuery("select * from customers where customerNumber=77");

	int customerNumber;
	String customerName = null;
	while (rs.next()) {
		customerNumber = rs.getInt("customerNumber");

		customerName = rs.getNString("customerName");

	}

	driver.get(url);

	driver.findElement(By.id("firstname")).sendKeys(customerName);

}


//**************///
@Test(priority=4)
public void deleteData() throws SQLException {
	
	String Query="delete from customers where customerNumber=77";
	stmt=con.createStatement();	
	
	int deletedRow=stmt.executeUpdate(Query);
	
	Assert.assertEquals(deletedRow>0, true);

	
}

}

