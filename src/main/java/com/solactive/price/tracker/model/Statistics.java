package com.solactive.price.tracker.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {


    private Double min;
    private Double max;
    private Double avg;
    private Long count;

    public Statistics() {
        this.min=0.0;
        this.max=0.0;
        this.avg=0.0;
        this.count=0L;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;

    }

    public Double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;

    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;

    }

    public Long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;

    }

    public static  Statistics createStatistics(Stream<Tick> tickStream) {
        Statistics stat = new Statistics();;
        final List<Double> amountsLastMinute = tickStream.map(Tick::getPrice).collect(Collectors.toList());
        final Long count = amountsLastMinute.stream().count();
        stat.setCount(count);
        if (count > 0) {

            stat.setAvg(amountsLastMinute.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
            stat.setMax(amountsLastMinute.stream().max(Double::compareTo).get());
            stat.setMin(amountsLastMinute.stream().min(Double::compareTo).get());

        }
        return stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Statistics that = (Statistics) o;
        return Double.compare(that.min, min) == 0 && Double.compare(that.max, max) == 0 && Double.compare(that.avg, avg) == 0 && count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), min, max, avg, count);
    }
}
