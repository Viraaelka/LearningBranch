package com.sberbank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataTableToArray {

    /*
    Converting Map<> to DataTable -> Sveta's solution
     */
    private DataTable transformMap(Map<String, String> map) {
        List<List<String>> dataAr = new ArrayList<>();
        map.forEach((k, v) -> {
            dataAr.add(Arrays.asList(k, map.get(k)));
        });
        return DataTable.create(dataAr);
    }
}
