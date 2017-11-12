import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherCSVReader {

    /**
     * Parse weather data from CSV, store in a list and return all the weather data
     * @return a list with all given MeasurePoints
     */
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
                measurePoints.add(parseMeasurePoint(record));
            }
            catch (Exception e){
                System.out.println("ERROR: Sth went wrong while parsing weather data from CSV");
            }
        }
        return measurePoints;
    }

    /**
     * Parse a single MeasurePoint from the CSVRecords
     * @param record a single record from CSV
     * @return the MeasurePoint which represents the record
     * @throws ParseException In case sth went totally wrong here
     */
    private MeasurePoint parseMeasurePoint(CSVRecord record) throws ParseException {
        String date = record.get("Date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date measureTime = df.parse(date);
        int hour = Integer.parseInt(record.get("Hour"));
        float temp = parseTemp(record.get("Temp"));
        System.out.println(measureTime);
        measureTime.setTime(measureTime.getTime() + hour * 60 * 60 * 1000);
        return new MeasurePoint(measureTime, temp);
    }

    /**
     * Parse the Temperature
     * @param temp temp as a string
     * @return temp as a float or 0 in case parsing failed
     */
    private float parseTemp(String temp){
        try{
            return Float.parseFloat(temp);
        } catch (Exception e){
            System.err.println("ERROR: While parsing a temperature");
            return 0;
        }
    }
}
