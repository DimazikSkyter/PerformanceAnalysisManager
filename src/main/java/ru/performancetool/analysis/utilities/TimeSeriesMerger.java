package ru.performancetool.analysis.utilities;

import lombok.val;

public class TimeSeriesMerger {

    /**
     * Await sorted massive
     * */
    public static long[] merge(long[] first, long[] second) {
        int firstIndex = 0, secondIndex = 0, tmpMassiveIndex = 0;
        int temporarySize = first.length + second.length;
        long[] tmpMassive = new long[temporarySize];
        while(firstIndex < first.length && secondIndex < second.length) {
            val compare = first[firstIndex] <= second[secondIndex];
            tmpMassive[tmpMassiveIndex++] = compare ? first[firstIndex] : second[secondIndex];
            if (compare) firstIndex++;
            else secondIndex++;
        }
        if(firstIndex == first.length) {
            for(;secondIndex < second.length; secondIndex++) {
                tmpMassive[tmpMassiveIndex] = second[secondIndex];
            }
        } else {
            for(;firstIndex < first.length; firstIndex++) {
                tmpMassive[tmpMassiveIndex] = first[firstIndex];
            }
        }
        long[] mergedMassive = new long[tmpMassiveIndex];
        for(int i=0; i<tmpMassiveIndex; i++) {
            mergedMassive[i] = tmpMassive[i];
        }
        return mergedMassive;
    }
}
