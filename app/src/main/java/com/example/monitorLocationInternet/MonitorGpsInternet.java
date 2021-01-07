package com.example.monitorLocationInternet;

public class MonitorGpsInternet {
    int _empId;
    String _monitorName;
    String _monitorStatus;
    String _dateTime;

    public MonitorGpsInternet() {
    }

    public String get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(String _dateTime) {
        this._dateTime = _dateTime;
    }

    public MonitorGpsInternet(int _empId, String _monitorName, String _monitorStatus, String _dateTime) {
        this._empId = _empId;
        this._monitorName = _monitorName;
        this._monitorStatus = _monitorStatus;
        this._dateTime = _dateTime;
    }

    public MonitorGpsInternet(String _monitorName, String _monitorStatus, String _dateTime) {
        this._monitorName = _monitorName;
        this._monitorStatus = _monitorStatus;
        this._dateTime = _dateTime;
    }

    public int get_empId() {
        return _empId;
    }

    public void set_empId(int _empId) {
        this._empId = _empId;
    }

    public String get_monitorName() {
        return _monitorName;
    }

    public void set_monitorName(String _monitorName) {
        this._monitorName = _monitorName;
    }

    public String get_monitorStatus() {
        return _monitorStatus;
    }

    public void set_monitorStatus(String _monitorStatus) {
        this._monitorStatus = _monitorStatus;
    }
}
