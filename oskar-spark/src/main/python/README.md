# Overview
Pyoskar is an open-source project that aims to implement a framework for genomic scale _big data_ analysis of hundreds of terabytes or even petabytes. All these can be queried through a command line interface or be used as a library.

Pyoskar constitutes the big data analysis component of [OpenCB](http://www.opencb.org/) initiative.

### Documentation
You can find Pyoskar documentation and tutorials at: http://docs.opencb.org/display/oskar/oskar-spark/src/main/python.

### Issues Tracking
You can report bugs or request new features at [GitHub issue tracking](https://github.com/opencb/oskar/issues).

### Release Notes and Roadmap
Releases notes are available at [GitHub releases](https://github.com/opencb/oskar/releases).

Roadmap is available at [GitHub milestones](https://github.com/opencb/oskar/milestones). You can report bugs or request new features at [GitHub issue tracking](https://github.com/opencb/oskar/issues).

### Versioning
Pyoskar is versioned following the rules from [Semantic versioning](http://semver.org/).

### Maintainers
We recommend to contact Pyoskar developers by writing to OpenCB mailing list opencb@googlegroups.com. Current main developers and maintainers are:
* Ignacio Medina (im411@cam.ac.uk) (_Founder and Project Leader_)
* Jacobo Coll (jacobo.coll-moragon@genomicsengland.co.uk)
* Joaquin Tarraga (jt645@cam.ac.uk)
* Sergio Roldan (sergioroldan96@gmail.com)

##### Contributing
Pyoskar is an open-source and collaborative project. We appreciate any help and feedback from users, you can contribute in many different ways such as simple bug reporting and feature request. Dependending on your skills you are more than welcome to develop client tools, new features or even fixing bugs.



### Developers
In order to ease the development, developers need to create three symbolic links:

1. PyOskar needs access to the Java JAR files where code is actually implemented. Those files are stored in _oskar/oskar-spark/target_ and we need to load them whenever we start a Spark session. As we specified in _spark-defaults.conf_, this link will be created inside _oskar/oskar-spark/src/main/python_, and it will receive the _libs_ name.

```bash
cd oskar-spark/src/main/python
ln -s ../../../target libs [from: python to: target]
```

2. The notebooks need access to the PyOskar API. This is achieved by creating another link inside _notebooks_ file that points to _oskar/oskar-spark/src/main/python_ file. It receives the name of _pyoskar_.

```bash
cd oskar-spark/src/main/python/notebooks
ln -s ../pyoskar pyoskar [from: notebooks to: python]
```

3. This last link is optional can be set as the user please. It points to the parquet file. We will need to use it whenever we want to load this file into a DataFrame through the _load_ PyOskar function. 

```bash
cd oskar-spark/src/main/python/notebooks
ln -s PATH_TO_DATA data [from: notebooks to: PATH_TO_DATA]
```
