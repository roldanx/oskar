package org.opencb.oskar.spark.variant.analysis;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.ClassRule;
import org.junit.Test;
import org.opencb.oskar.spark.OskarSparkTestUtils;
import org.opencb.oskar.spark.commons.OskarException;

import java.io.IOException;

import static org.junit.Assert.*;

public class HardyWeinbergTransformerTest {
    @ClassRule
    public static OskarSparkTestUtils sparkTest = new OskarSparkTestUtils();

    @Test
    public void testHWE() throws IOException, OskarException {
        Dataset<Row> df = sparkTest.getVariantsDataset();

        new HardyWeinbergTransformer().setStudyId("hgvauser@platinum:illumina_platinum")
                .transform(df)
//                .where("code != 0").show();
//                .where(col("code").notEqual(0))
                .show();
    }
}