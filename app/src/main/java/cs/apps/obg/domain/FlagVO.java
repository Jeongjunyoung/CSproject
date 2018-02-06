package cs.apps.obg.domain;

/**
 * Created by d1jun on 2018-01-24.
 */

public class FlagVO {
    private int _id;
    private String country;
    private String continent;
    private String capital;
    private int resId;
    private int continentNum;
    public FlagVO(){}

    public FlagVO(int _id, String country, String continent, String capital, int resId, int continentNum) {
        this._id = _id;
        this.country = country;
        this.continent = continent;
        this.capital = capital;
        this.resId = resId;
        this.continentNum = continentNum;
    }

    public int get_id() {
        return _id;
    }
    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getContinentNum() {
        return continentNum;
    }
    public void setContinentNum(int continentNum) {
        this.continentNum = continentNum;
    }
}
