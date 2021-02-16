package ru.performancetool.analysis.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PurifiedData implements Serializable {

    private Map<String, PurifiedDataElement> resultDataElementMap;
    @NonNull
    private long[] timeSeries;
    @Getter
    @Setter
    private boolean isSLACompleted;

    public PurifiedData(long[] timeSeries, Map<String, PurifiedDataElement> resultDataElementMap) {
        this.timeSeries = timeSeries;
        this.resultDataElementMap = resultDataElementMap;
    }

    public void addPurifiedElement() {

    }

    public PurifiedDataElement filterElement(Predicate predicate) {
        return null;
    }


    public List<PurifiedDataElement> getResultDataElementsList() {
        return new ArrayList<>(resultDataElementMap.values());
    }

}
