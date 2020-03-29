package in.mcoeproject.covid_19india;


public class StateData {

    String state;
    String confirmed;
    String active;
    String recovered;
    String deaths;
    String lastupdatedtime;

    public StateData(String state, String confirmed, String active, String recovered, String deaths, String lastupdatedtime) {
        this.state = state;
        this.confirmed = confirmed;
        this.active = active;
        this.recovered = recovered;
        this.deaths = deaths;
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getStateName() {
        return state;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getActive() {
        return active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }


}
