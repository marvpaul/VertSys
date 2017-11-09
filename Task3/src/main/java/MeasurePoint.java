import java.io.Serializable;
import java.util.Date;

public class MeasurePoint implements Serializable{
    Date _timeStamp;
    private float _temperature;

    public MeasurePoint(Date _timeStamp, float _temperature) {
        this._timeStamp = _timeStamp;
        this._temperature = _temperature;
    }

    public Date get_timeStamp() {
        return _timeStamp;
    }

    public void set_timeStamp(Date _timeStamp) {
        this._timeStamp = _timeStamp;
    }

    public float get_temperature() {
        return _temperature;
    }

    public void set_temperature(float _temperature) {
        this._temperature = _temperature;
    }
}
