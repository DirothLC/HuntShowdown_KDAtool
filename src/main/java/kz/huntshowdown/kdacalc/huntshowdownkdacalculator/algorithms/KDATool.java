package kz.huntshowdown.kdacalc.huntshowdownkdacalculator.algorithms;

public class KDATool {

    public double kdaCalculate(int kills, int assists, int deaths) {
        if (deaths == 0) {
            return kills + assists;
        } else {
            double result = (double) (kills + assists) / deaths;
            return Math.round(result * 100.0) / 100.0;
        }
    }

    public int diff(int kills, int deaths){
        return kills - deaths;
    }

    public int desiredKDA(int kills, int assists, int deaths,  double desiredKDA) {
        double kda = (double) (kills + assists) / deaths;
        double missingKills=0;
        if (desiredKDA < kda) {
           missingKills =   deathsToLowerKDA(kills, assists, deaths, desiredKDA);

        } else {
            missingKills = desiredKDA * deaths - kills - assists;
        }
            return (int) Math.ceil(Math.max(0, missingKills));

    }


    public int deathsToLowerKDA(int kills, int assists,int deaths, double desiredKDA){
        double requiredDeaths = (kills + assists) / desiredKDA;
        double result= requiredDeaths - deaths;
        return (int) Math.ceil(Math.max(0, result));
    }

}
