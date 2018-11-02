package org.opencb.oskar.spark.variant.udf;

import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import scala.collection.mutable.WrappedArray;
import scala.runtime.AbstractFunction2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created on 04/09/18.
 *
 * Returns an array with the MIN and the MAX value of the given ProteinSubstitutionScore
 * Empty array if not found
 *
 * @author Jacobo Coll &lt;jacobo167@gmail.com&gt;
 */
public class ProteinSubstitutionScoreFunction extends AbstractFunction2<GenericRowWithSchema, String, List<Double>>
        implements UDF2<GenericRowWithSchema, String, List<Double>> {

    @Override
    public List<Double> call(GenericRowWithSchema annotation, String source) {
        Double max = Double.MIN_VALUE;
        Double min = Double.MAX_VALUE;
        WrappedArray<GenericRowWithSchema> consequenceTypes = annotation.getAs("consequenceTypes");
        for (int i = 0; i < consequenceTypes.size(); i++) {
            GenericRowWithSchema consequenceType = consequenceTypes.apply(i);
            GenericRowWithSchema proteinVariantAnnotation = consequenceType.getAs("proteinVariantAnnotation");
            if (proteinVariantAnnotation != null) {
                WrappedArray<GenericRowWithSchema> substitutionScores = proteinVariantAnnotation.getAs("substitutionScores");
                if (substitutionScores != null) {
                    for (int i1 = 0; i1 < substitutionScores.size(); i1++) {
                        GenericRowWithSchema substitutionScore = substitutionScores.apply(i1);
                        if (substitutionScore.<String>getAs("source").equals(source)) {
                            Double score = substitutionScore.getAs("score");
                            if (score > max) {
                                max = score;
                            }
                            if (score < min) {
                                min = score;
                            }
                        }
                    }
                }
            }
        }

        if (max == Double.MIN_VALUE) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(min, max);
        }
    }

    @Override
    public List<Double> apply(GenericRowWithSchema annotation, String proteinSubstitutionScore) {
        return call(annotation, proteinSubstitutionScore);
    }
}
