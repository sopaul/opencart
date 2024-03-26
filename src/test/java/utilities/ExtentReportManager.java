package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//For email
/*
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
*/

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
		// Below 3 classes are provided by ExtentReport
		public ExtentSparkReporter sparkReporter; // This class is responsible for the UI of the report(look & feel design)
		public ExtentReports extent; // Provide common info to the report
		public ExtentTest test; // For creating actual test in the report by updating the status and other information
		
		String repName;
		
		public void onStart(ITestContext testContext) // This method will create empty report/UI report
		{
			// to create a time stamp, 2 classes is used 'SimpleDateFormat' & 'Date'
				/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				  Date dt=new Date();
				  String currentdatetimestamp=df.format(dt);
				 */
			
			// since the above code has 3 steps , this can be achieved in 1 step as mentioned below
			
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
			
			repName="Test-Report-"+timeStamp+".html";
			sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName); //'repName'Specify the location of the report along with the name
			/* Purpose of 'repName' : If the name of the report is hard coded, every time the program is run the report 
			 * is replaced with new report and hence cannot maintain the history of report which is mandatory to track 
			 * the traceability of the test performed. So for that purpose report is added with datetime stamp to maintain history.
			 */
			
			sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of report
			sparkReporter.config().setReportName("Opencart Functional Testing"); //name of the report
			sparkReporter.config().setTheme(Theme.STANDARD);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environemnt", "QA");
			
			//The below code takes parameter values of 'os' and 'browser' info from TestNG suite .xml file
			String os=testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", os);
			
			String browser=testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			
			//The below code take groups info and displays in the report
			List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty())
			{
				extent.setSystemInfo("Groups", includedGroups.toString());
			}
		
		}
		
		public void onTestSuccess(ITestResult result)
		{
			test = extent.createTest(result.getTestClass().getName()); // This will display new testcase entry in the report
			test.assignCategory(result.getMethod().getGroups()); // To display groups in report
			test.log(Status.PASS,result.getName()+"got sucessfully executed");
		}

		public void onTestFailure(ITestResult result) 
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL,result.getName()+" got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			try {
				String imgPath = new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
		
		public void onTestSkipped(ITestResult result) 
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.SKIP, result.getName()+" got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}

		public void onFinish(ITestContext testContext) 
		{
			
			extent.flush();// This is a mandatory method for report to be generated.
			
			//The below code opens up the report automatically when the execution is done . This is optional
			String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//The below code sends report as email to the team as soon as its generated 
			
			//To send email with attachment
			//sendEmail(sender email,sender password(encrypted),recipient email);
			//sendEmail("abc@gmail.com","xyz123xyz","xyz@gmail.com");

			/*
			 try 
			 { 
				 URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			 
			 // Create the email message 
			 ImageHtmlEmail email = new ImageHtmlEmail();
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.googlemail.com"); 
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("ENTER THE EMAIL ID","password")); 
			 email.setSSLOnConnect(true);
			 email.setFrom("ENTER THE 'FROM' EMAIL ID"); //Sender
			 email.setSubject("Test Results");
			 email.setMsg("Please find Attached Report....");
			 email.addTo("ENTER THE 'TO' EMAIL ID"); //Receiver 
			 email.attach(url, "extent report", "please check report..."); 
			 email.send(); // send the email 
			 }
			 catch(Exception e) 
			 { 
				 e.printStackTrace(); 
			 }
			 */
		}
		/*
		//User defined method for sending email..
		public void sendEmail(String senderEmail,String senderPassword,String recipientEmail)
		{
			// SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");

	        // Create a Session object
	        Session session = Session.getInstance(properties, new Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            // Create a MimeMessage object
	            Message message = new MimeMessage(session);

	            // Set the sender and recipient addresses
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

	            // Set the subject
	            message.setSubject("Test Report with attachment");

	            // Create a MimeMultipart object
	            Multipart multipart = new MimeMultipart();

	            // Attach the file
	            String filePath = ".\\reports\\"+repName;
	            String fileName = repName;

	            MimeBodyPart attachmentPart = new MimeBodyPart();
	            attachmentPart.attachFile(filePath);
	            attachmentPart.setFileName(fileName);

	            // Create a MimeBodyPart for the text content
	            MimeBodyPart textPart = new MimeBodyPart();
	            textPart.setText("Please find the attached file.");

	            // Add the parts to the multipart
	            multipart.addBodyPart(textPart);
	            multipart.addBodyPart(attachmentPart);

	            // Set the content of the message
	            message.setContent(multipart);

	            // Send the message
	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	            
		}


			*/


}
