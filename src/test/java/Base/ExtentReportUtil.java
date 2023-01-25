package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * Created by Karthik on 21/09/2019.
 */


public class ExtentReportUtil extends BaseUtil {
    String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    String fileName = reportLocation + dateName+"_PRISM.html";


    public void ExtentReport() {
        //First is to create Extent Reports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        extent.attachReporter(spark);

    }

    public void ExtentReportScreenshot() throws IOException {

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) Driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/"+ dateName+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		byte[] image=IOUtils.toByteArray(new FileInputStream(destination)); 
		String imagecode="data:image/png;base64,"+Base64.getEncoder().encodeToString(image);
		scenarioDef.addScreenCaptureFromBase64String(imagecode);
    }


    public void FlushReport(){
        extent.flush();
    }

}
