package ru.performancetool.analysis.clusters;

import ru.performancetool.analysis.data.DataPoint;
import ru.performancetool.analysis.data.PurifiedData;
import ru.performancetool.analysis.data.ResultData;
import ru.performancetool.analysis.store.ClusterStorage;

import java.util.List;

public class ShortSearchStrategy extends Strategy {
    public ShortSearchStrategy(ClusterStorage clusterStorage) {
        super(clusterStorage);
    }

    @Override
    public ResultData run(List<DataPoint> points, PurifiedData purifiedData) {
        return null;
    }
}
