#!/bin/bash

export PYSPARK_DRIVER_PYTHON=jupyter
export PYSPARK_DRIVER_PYTHON_OPTS='lab'

 export SPARK_CONF_DIR='../conf'

if [[ -z "$SPARK_HOME" ]]; then
  echo "No SPARK_HOME variable found"
else
  $SPARK_HOME/bin/pyspark
fi
