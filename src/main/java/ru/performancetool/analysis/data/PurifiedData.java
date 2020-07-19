package ru.performancetool.analysis.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PurifiedData implements Serializable {

    private Map<String, PurifiedDataElement> resultDataElementMap;
    @Getter
    @Setter
    private boolean isSLACompleted;

    public PurifiedData() {
    }

    public PurifiedDataElement filterElement(Predicate predicate) {
        return null;
    }


    public List<PurifiedDataElement> getResultDataElementsList() {
        return new ArrayList<>(resultDataElementMap.values());
    }

}
