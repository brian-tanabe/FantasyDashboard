package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/15/2014.
 */
@Entity
@Table(name = "NUMBER_FIRE_CURRENT_WEEK_PROJECTIONS", schema = "PUBLIC", catalog = "FANTASYDATABASE")
public class NumberFireCurrentWeekProjectionsEntity {
    private int numberFireId;
    private int ranking;
    private double firePoints;

    @Id
    @Column(name = "NUMBER_FIRE_ID")
    public int getNumberFireId() {
        return numberFireId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    @Basic
    @Column(name = "RANKING")
    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Basic
    @Column(name = "FIRE_POINTS")
    public double getFirePoints() {
        return firePoints;
    }

    public void setFirePoints(double firePoints) {
        this.firePoints = firePoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberFireCurrentWeekProjectionsEntity that = (NumberFireCurrentWeekProjectionsEntity) o;

        if (Double.compare(that.firePoints, firePoints) != 0) return false;
        if (numberFireId != that.numberFireId) return false;
        if (ranking != that.ranking) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numberFireId;
        result = 31 * result + ranking;
        temp = Double.doubleToLongBits(firePoints);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
