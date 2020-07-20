package ru.performancetool.analysis.clusters;

import ru.performancetool.analysis.data.DataPoint;
import ru.performancetool.analysis.data.PurifiedData;
import ru.performancetool.analysis.data.ResultData;
import ru.performancetool.analysis.store.ClusterStorage;

import java.util.List;

public abstract class Strategy {

    private ClusterStorage clusterStorage;

    public Strategy(ClusterStorage clusterStorage) {
        this.clusterStorage = clusterStorage;
    }

    public abstract ResultData run(List<DataPoint> points, PurifiedData purifiedData);
}
