package org.opencb.oskar.spark.variant.analysis;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.ClassRule;
import org.junit.Test;
import org.opencb.oskar.analysis.variant.FisherExactTest;
import org.opencb.oskar.analysis.variant.FisherTestResult;
import org.opencb.oskar.spark.OskarSparkTestUtils;
import org.opencb.oskar.spark.commons.OskarException;

import java.io.IOException;

public class TdtTransformerTest {
    @ClassRule
    public static OskarSparkTestUtils sparkTest = new OskarSparkTestUtils();

    @Test
    public void testTdtTransformer() throws IOException, OskarException {
        Dataset<Row> df = sparkTest.getVariantsDataset();

        new TdtTransformer().setStudyId("hgvauser@platinum:illumina_platinum")
                .setPhenotype("KK")
                .transform(df)
//                .where("code != 0").show();
//                .where(col("code").notEqual(0))
                .show();
    }

}