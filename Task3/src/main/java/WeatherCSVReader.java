import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class WeatherCSVReader {
    public ArrayList<MeasurePoint> getWeatherData(){
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("weather.csv");
        Reader in = new InputStreamReader(inStream);
        //First row is header
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<MeasurePoint> measurePoints = new ArrayList<MeasurePoint>();

        //Read each record and search for the value for a given key
        for (CSVRecord record : records) {
            try{
                String date = record.get("Date");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date measureTime = df.parse(date);
                int hour = Integer.parseInt(record.get("Hour"));
                float temp = parseTemp(record.get("Temp"));
                System.out.println(measureTime);
                measureTime.setTime(measureTime.getTime() + hour * 60 * 60 * 1000);
                measurePoints.add(new MeasurePoint(measureTime, temp));
            }
            catch (Exception e){
                System.out.println("ERROR: Invalid date, please enter a date with format: YYYY-mm-dd");
            }
        }
        return measurePoints;
    }

    private float parseTemp(String temp){
        try{
            return Float.parseFloat(temp);
        } catch (Exception e){
            System.err.println("ERROR: While parsing a temperature");
            return 0;
        }
    }
}
