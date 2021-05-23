package com.solactive.price.tracker.model;



import java.io.Serializable;
import java.util.Objects;

public class Tick implements Comparable<Tick>, Serializable {


    private static final long serialVersionUID = 1L;
    private Long timestamp;
    private String instrument;
    private Double price;


    public Tick(long timestamp, String instrument, double price) {
        this.timestamp = timestamp;
        this.instrument = instrument;
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tick that = (Tick) o;
        return timestamp == that.timestamp && Double.compare(that.price, price) == 0 && instrument.equals(that.instrument);
    }

    @Override
    public int compareTo(Tick otherTick) {
        return this.getTimestamp().compareTo(otherTick.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, instrument, price);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
